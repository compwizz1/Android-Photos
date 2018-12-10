package com.example.photosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
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

        CustomAdapter adapter = new CustomAdapter(this, R.layout.photo_pic, photos);
        listview.setAdapter(adapter);

        if(!photos.isEmpty()) {
            listview.setSelection(0);
        }
        listview.setOnItemClickListener((p, V, pos, id) -> SelectPhoto(pos));
    }

    public void Back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("extra_user", user);
        startActivity(intent);
    }

    public void SelectPhoto(int position)
    {
        index = position;
    }

    public void Add(View view){
        Intent intent = new Intent(this, AddPhoto.class);
        intent.putExtra("extra_user", user);
        intent.putExtra("extra_album",album);
        startActivityForResult(intent, 1);
    }

    public void Remove(View view)
    {

    }

    public void Display(View view)
    {
        Intent intent = new Intent(this, PhotoDisplay.class);
        intent.putExtra("extra_user", user);
        intent.putExtra("extra_album",album);
        intent.putExtra("extra_photo", photos.get(index));
        intent.putExtra("extra_index", index);
        startActivityForResult(intent, 1);

    }
    public void Move(View view)
    {

    }
    public void SlideShow(View view){

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (resultCode != RESULT_OK) {
            return;
        }

        user = (User) intent.getSerializableExtra("extra_user");
        album = (Album) intent.getSerializableExtra("extra_album");
        if(album == null){
            System.out.println("F");
        }
        album = user.getAlbumFromName(album.getName());
       try {
            User.writeUser(user, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        photos = album.getPhotos();
        System.out.println(photos.size());

        listview = (ListView) findViewById(R.id.listview);
        ArrayAdapter adapter = new ArrayAdapter<Photo>(this, R.layout.photo_show, photos);
        listview.setAdapter(adapter);

        if(!photos.isEmpty()) {
            listview.setSelection(0);
        }
//        listview.setOnItemClickListener((p, V, pos, id) -> SelectPhoto(pos));
    }

}

