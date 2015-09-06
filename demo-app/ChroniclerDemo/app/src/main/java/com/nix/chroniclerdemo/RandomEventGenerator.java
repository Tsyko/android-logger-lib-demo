package com.nix.chroniclerdemo;

import com.nix.chronicler.LoggerFactory;

import android.os.Handler;
import android.util.TimeUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Generates random events.
 */
class RandomEventGenerator {

    private static final int MAX_INTERVAL = 30;
    private static final int MAX_SIMULTANEOUS_EVENTS = 10;

    private final Handler handler = new Handler();

    private final Random random = new Random();

    RandomEventGenerator() {
    }

    private void fireRandomEvents() {
        int count = random.nextInt(MAX_SIMULTANEOUS_EVENTS) + 1;
        for (int i = 0; i < count; i++) {
            LoggerFactory.getLogger().log(EventType.RANDOM, "random");
        }
    }

    private class EventProducerRunnable implements Runnable {

        @Override
        public void run() {
            fireRandomEvents();
            handler.postDelayed(new EventProducerRunnable(),
                    TimeUnit.SECONDS.toMillis(random.nextInt(MAX_INTERVAL)));
        }
    }

    void start() {
        handler.postDelayed(new EventProducerRunnable(),
                TimeUnit.SECONDS.toMillis(random.nextInt(MAX_INTERVAL)));
    }

    void stop() {
        handler.removeCallbacksAndMessages(null);
    }
}
