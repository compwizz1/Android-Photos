package com.example.photosapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Photo> {
    public CustomAdapter(Context context, List<Photo> photos) {
        super(context, 0, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row == null) {
            // inflate row layout and assign to 'row'
        }
        final Photo thisPhoto = getItem(position);
        final ImageView photo = row.findViewById(R.id.photo);
        photo.setImageBitmap(thisPhoto.getPic());

        return row;
    }
}
