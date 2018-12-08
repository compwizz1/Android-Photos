package com.example.photosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        setContentView(R.layout.album);

        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        move = findViewById(R.id.move);
        display = findViewById(R.id.display);

        user = (User) getIntent().getSerializableExtra("extra_user");
        album = (Album) getIntent().getSerializableExtra("extra_album");
        photos = album.getPhotos();

    }

    public void Add(View view){
        Intent intent = new Intent(this, AddPhoto.class);
        intent.putExtra("extra_user", user);
        intent.putExtra("extra_album",album);
        startActivity(intent);
    }
}
