package com.nix.chronicler;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.List;

/**
 * Created by andriy on 05 September 2015.
 */
class NetworkManager {

    private final Context context;
    private boolean idle = true;

    NetworkManager(final Context context) {
        this.context = context;
    }

    boolean isIdle() {
        return idle;
    }

    boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    void send(final List<Event> events) {
        Log.d("ZZZ", "NetworkManager.send() got " + events.size() + " events to send");
        // TODO: serialize
        // TODO: send

        idle = false;
    }
}
