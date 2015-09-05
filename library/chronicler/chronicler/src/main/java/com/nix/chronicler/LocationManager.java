package com.nix.chronicler;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by andriy on 04 September 2015.
 */
class LocationManager implements GoogleApiClient.ConnectionCallbacks, LocationListener {

    private GoogleApiClient client;
    private Location location;

    LocationManager(Context context) {
        client = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
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

    private LocationRequest buildLocationRequest() {
        LocationRequest request = new LocationRequest();
        request.setInterval(10000);
        request.setFastestInterval(5000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return request;
    }

    private synchronized void setLocation(Location location) {
        Log.d("ZZZ", "LocationManager.setLocation() " + location);
        this.location = location;
    }

    public synchronized Location getLocation() {
        return location;
    }

    @Override
    public void onConnected(final Bundle bundle) {
        Log.d("ZZZ", "LocationManager.onConnected() ");
        setLocation(LocationServices.FusedLocationApi.getLastLocation(client));
        LocationServices.FusedLocationApi.requestLocationUpdates(
                client, buildLocationRequest(), this);
    }

    @Override
    public void onConnectionSuspended(final int i) {
        Log.d("ZZZ", "LocationManager.onConnectionSuspended() ");
    }

    @Override
    public void onLocationChanged(final Location location) {
        Log.d("ZZZ", "LocationManager.onLocationChanged() ");
        setLocation(location);
    }
}
