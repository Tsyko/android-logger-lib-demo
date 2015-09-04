package com.nix.chronicler;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by andriy on 04 September 2015.
 */
class LocationManager {

    private GoogleApiClient client;

    void init(Context context) {
        client = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(final Bundle bundle) {
                        Log.d("ZZZ", "LocationManager.onConnected() ");
                    }

                    @Override
                    public void onConnectionSuspended(final int i) {
                        Log.d("ZZZ", "LocationManager.onConnectionSuspended() ");
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(final ConnectionResult connectionResult) {
                        Log.d("ZZZ", "LocationManager.onConnectionFailed() 1");
                    }
                })
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }

}
