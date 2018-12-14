package com.example.photosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class AlbumView extends AppCompatActivity {

    User user;
    Album album;
    List<Photo> photos;

    ListView listview;

    Button add, remove, display, move, back;

    TextView error;

    int index;

    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album);

        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        move = findViewById(R.id.move);
        display = findViewById(R.id.display);
        listview = findViewById(R.id.listview);
        error = findViewById(R.id.error);
        back = findViewById(R.id.back);

        user = (User) getIntent().getSerializableExtra("extra_user");
        album = (Album) getIntent().getSerializableExtra("extra_album");
        album = user.getAlbumFromName(album.getName());
        photos = album.getPhotos();

        adapter = new CustomAdapter(this, R.layout.photo_pic, photos);
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
        if(photos.isEmpty())
        {
            error.setText("Error: No photos to remove");
            return;
        }
        else if(index < 0 || index >= photos.size()){
            error.setText("Select a photo first");
            return;
        }
        album.removePhoto(photos.get(index));
        try {
            User.writeUser(user, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        photos = album.getPhotos();
        adapter.notifyDataSetChanged();
        listview.setSelection(0);



    }
    public void onBackPressed(){

    }

    public void Display(View view)
    {
        if(photos.isEmpty())
        {
            error.setText("Error: No photos to display");
            return;
        }

        Intent intent = new Intent(this, PhotoDisplay.class);
        intent.putExtra("extra_user", user);
        intent.putExtra("extra_album",album);
        intent.putExtra("extra_photo", photos.get(index));
        intent.putExtra("extra_index", index);
        startActivityForResult(intent, 1);

    }
    public void Move(View view)
    {
        if(photos.isEmpty())
        {
            error.setText("Error: No photos to move/copy");
            return;
        }

        Intent intent = new Intent(this, MoveCopyPhoto.class);
        intent.putExtra("extra_user", user);
        intent.putExtra("extra_album",album);
        intent.putExtra("extra_photo", photos.get(index));
        intent.putExtra("extra_index", index);
        startActivityForResult(intent, 1);

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
        CustomAdapter adapter = new CustomAdapter(this, R.layout.photo_pic, photos);
        listview.setAdapter(adapter);

        if(!photos.isEmpty()) {
            listview.setSelection(0);
        }
//        listview.setOnItemClickListener((p, V, pos, id) -> SelectPhoto(pos));
    }

}

