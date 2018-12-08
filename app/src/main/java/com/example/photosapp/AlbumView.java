package com.example.photosapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.List;

public class AlbumView extends AppCompatActivity {

    User user;
    Album album;
    List<Photo> photos;

    Button add, remove, slideshow, display, move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_album);

        add = findViewById(R.id.add);
        remove = findViewById(R.id.back);
        move = findViewById(R.id.display);
        display = findViewById(R.id.display);

        user = (User) getIntent().getSerializableExtra("extra_user");
        album = (Album) getIntent().getSerializableExtra("extra_album");
        photos = album.getPhotos();




    }
}
