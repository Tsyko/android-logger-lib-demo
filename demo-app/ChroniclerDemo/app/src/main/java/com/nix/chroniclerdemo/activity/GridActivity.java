package com.nix.chroniclerdemo.activity;

import com.nix.chroniclerdemo.R;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GridActivity extends AbstractDemoActivity {

    @InjectView(R.id.gridview)
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ButterKnife.inject(this);

        String[] projection = {MediaStore.MediaColumns._ID};
        final Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);

        gridView.setAdapter(new ImageAdapter(this, cursor));

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
            Uri imageURI = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Long.toString(id));
//            Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").fit().centerInside().into(holder.imageView);
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
