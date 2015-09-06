package com.nix.chroniclerdemo.activity;

import com.nix.chronicler.LoggerFactory;
import com.nix.chroniclerdemo.EventType;

import android.app.Activity;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by andriy on 05 September 2015.
 */
abstract class AbstractDemoActivity extends Activity {

    protected static Uri buildImageUri(final long imageId) {
        return Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Long.toString(imageId));
    }

    @Override
    protected void onResume() {
        LoggerFactory.getLogger().log(EventType.SCREEN_OPENED, this.getClass().getSimpleName());
        super.onResume();
    }

    @Override
    protected void onPause() {
        LoggerFactory.getLogger().log(EventType.SCREEN_CLOSED, this.getClass().getSimpleName());
        super.onPause();
    }
}
