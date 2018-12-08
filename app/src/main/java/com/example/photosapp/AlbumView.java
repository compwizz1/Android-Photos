package com.example.photosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class AlbumView extends AppCompatActivity {

    User user;
    Album album;
    List<Photo> photos;

    ListView listview;

    Button add, remove, slideshow, display, move;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album);

        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        move = findViewById(R.id.move);
        display = findViewById(R.id.display);
        listview = findViewById(R.id.listview);

        user = (User) getIntent().getSerializableExtra("extra_user");
        album = (Album) getIntent().getSerializableExtra("extra_album");
        photos = album.getPhotos();

       CustomAdapter adapter = new CustomAdapter(this, photos);
        listview.setAdapter(adapter);
        if(!photos.isEmpty()) {
            listview.setSelection(0);
            index = 0;
        }
        listview.setOnItemClickListener((p, V, pos, id) -> SelectPhoto(pos));


    }

    public void SelectPhoto(int position)
    {
        index = position;
    }

    public void Add(View view){
        Intent intent = new Intent(this, AddPhoto.class);
        intent.putExtra("extra_user", user);
        intent.putExtra("extra_album",album);
        startActivity(intent);
    }

    public void Remove(View view)
    {

    }

    public void Display(View view)
    {

    }
    public void Move(View view)
    {

    }

}
