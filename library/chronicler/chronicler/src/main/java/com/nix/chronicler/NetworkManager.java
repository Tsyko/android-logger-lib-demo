package com.nix.chronicler;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Performs the networking.
 */
class NetworkManager {

    private final JsonCreator jsonCreator = new JsonCreator();
    private final Context context;
    private List<Event> events = null;
    private final Listener listener;

    NetworkManager(final Listener listener, final Context context) {
        this.listener = listener;
        this.context = context;
    }

    boolean isIdle() {
        return events == null;
    }

    boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    void send(final List<Event> events) {
        if (events == null) {
            throw new IllegalStateException("Not idle.");
        }

        this.events = events;

        new Thread(new Runnable() {
            @Override
            public void run() {
                send();
            }
        }, "NetworkManagerThread").start();
    }

    private void send() {
        if (events == null) {
            throw new IllegalStateException("Not idle.");
        }

        final String json = jsonCreator.toJson(events).toString();

        try {
            URL url = new URL(LoggerFactory.getServerUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            writeRequestBody(json, connection.getOutputStream());

            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                listener.onSendSuccess(events);
            } else {
                listener.onSendFailure(events);
            }
        } catch (IOException e) {
            listener.onSendFailure(events);
        } finally {
            events = null;
        }
    }

    private void writeRequestBody(final String text, final OutputStream stream) throws IOException {
        stream.write(text.getBytes(Charset.forName("UTF-8")));
        stream.close();
    }

    interface Listener {
        void onSendSuccess(List<Event> events);
        void onSendFailure(List<Event> events);
    }
}
