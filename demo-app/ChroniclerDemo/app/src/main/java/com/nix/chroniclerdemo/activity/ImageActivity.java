package com.nix.chroniclerdemo.activity;

import com.nix.chroniclerdemo.R;
import com.squareup.picasso.Picasso;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Activity for single image.
 */
public class ImageActivity extends AbstractDemoActivity {

    static final String EXTRA_IMAGE_ID = "image_id";

    @InjectView(R.id.image)
    ImageView imageView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.inject(this);

        final long imageId = getIntent().getLongExtra(EXTRA_IMAGE_ID, -1);
        Uri imageURI = buildImageUri(imageId);
        Picasso.with(this).load(imageURI).fit().centerInside().into(imageView);
    }
}
