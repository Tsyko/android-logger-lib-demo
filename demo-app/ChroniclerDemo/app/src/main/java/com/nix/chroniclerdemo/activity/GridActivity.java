package com.nix.chroniclerdemo.activity;

import com.nix.chroniclerdemo.R;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

/**
 * Activity with grid of images.
 */
public class GridActivity extends AbstractDemoActivity {

    @InjectView(R.id.gridview)
    GridView gridView;

    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ButterKnife.inject(this);

        String[] projection = {MediaStore.MediaColumns._ID};
        final Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);

        adapter = new ImageAdapter(this, cursor);
        gridView.setAdapter(adapter);

    }

    @OnItemClick(R.id.gridview)
    void onImageClick(int position) {
        final Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra(ImageActivity.EXTRA_IMAGE_ID, adapter.getItemId(position));
        startActivity(intent);
    }

    private class ImageAdapter extends CursorAdapter {

        public ImageAdapter(final Context context, final Cursor cursor) {
            super(context, cursor, 0);
        }

        @Override
        public View newView(final Context context, final Cursor cursor, final ViewGroup parent) {
            return getLayoutInflater().inflate(R.layout.li_image, parent, false);
        }

        @Override
        public void bindView(final View view, final Context context, final Cursor cursor) {
            ViewHolder holder = new ViewHolder(view);
            int columnIndex = cursor.getColumnIndex(MediaStore.MediaColumns._ID);
            final long id = cursor.getLong(columnIndex);
            Uri imageURI = buildImageUri(id);
            Picasso.with(context).load(imageURI).fit().centerCrop().into(holder.imageView);
        }
    }

    static class ViewHolder {

        @InjectView(R.id.image)
        ImageView imageView;

        ViewHolder(View root) {
            ButterKnife.inject(this, root);
        }
    }
}
