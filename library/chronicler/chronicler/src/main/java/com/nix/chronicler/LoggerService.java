package com.nix.chronicler;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Service which processes incoming events.
 */
public class LoggerService extends Service implements NetworkManager.Listener {

    private static final long NETWORK_DELAY = TimeUnit.SECONDS.toMillis(10);
    private static final long RECONNECT_TIMEOUT = TimeUnit.MINUTES.toMillis(15);

    private static final int MESSAGE_SEND_EVENTS = 1;


    static final String EXTRA_TYPE = "extra type";
    static final String EXTRA_COMMENT = "extra comment";

    private LocationManager locationManager;
    private NetworkManager networkManager;
    private EventStorage eventStorage;

    private final Object mutex = new Object();

    private final Handler handler = new ServiceHandler(this);

    @Override
    public void onCreate() {
        super.onCreate();
        locationManager = new LocationManager(this);
        networkManager = new NetworkManager(this, this);
        eventStorage = new InMemoryEventStorage();
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        Event event = new Event(intent.getIntExtra(EXTRA_TYPE, -1),
                intent.getStringExtra(EXTRA_COMMENT),
                locationManager.getLocation(), System.currentTimeMillis());

        synchronized (mutex) {
            eventStorage.addLast(event);
        }

        if (!handler.hasMessages(MESSAGE_SEND_EVENTS)) {
            handler.sendEmptyMessageDelayed(MESSAGE_SEND_EVENTS, NETWORK_DELAY);
        }

        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(final Intent intent) {
        return null; // binding not supported
    }

    private void sendEvents() {
        synchronized (mutex) {
            if (!networkManager.isIdle() || eventStorage.isEmpty()) {
                return;
            }

            if (!networkManager.isOnline() && !handler.hasMessages(MESSAGE_SEND_EVENTS)) {
                // try again later
                handler.sendEmptyMessageDelayed(MESSAGE_SEND_EVENTS, RECONNECT_TIMEOUT);
                return;
            }

            List<Event> events = eventStorage.removeAll();
            networkManager.send(events);
        }
    }

    @Override
    public void onSendSuccess(final List<Event> events) {
    }

    @Override
    public void onSendFailure(final List<Event> events) {
        synchronized (mutex) {
            for (int i = events.size() - 1; i >= 0; i--) {
                eventStorage.addFirst(events.get(i));
            }
        }
    }

    private static class ServiceHandler extends Handler {

        private final WeakReference<LoggerService> reference;

        public ServiceHandler(final LoggerService service) {
            reference = new WeakReference<>(service);
        }

        @Override
        public void handleMessage(final Message msg) {
            final LoggerService service = reference.get();

            if (service != null && msg.what == MESSAGE_SEND_EVENTS) {
                service.sendEvents();
            }
        }
    }
}
