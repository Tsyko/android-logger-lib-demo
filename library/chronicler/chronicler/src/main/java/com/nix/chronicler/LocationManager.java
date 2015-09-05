package com.nix.chronicler;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

/**
 * Location manager.
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
        this.location = location;
    }

    public synchronized Location getLocation() {
        return location;
    }

    @Override
    public void onConnected(final Bundle bundle) {
        setLocation(LocationServices.FusedLocationApi.getLastLocation(client));
        LocationServices.FusedLocationApi.requestLocationUpdates(
                client, buildLocationRequest(), this);
    }

    @Override
    public void onConnectionSuspended(final int i) {
    }

    @Override
    public void onLocationChanged(final Location location) {
        setLocation(location);
    }
}
