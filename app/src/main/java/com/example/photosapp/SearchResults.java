package com.example.photosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

class SearchResults extends AppCompatActivity {

    Album resultAlbum;

    List<Photo> photos;

    Button back, display;

    ListView listview;

    TextView error;

    CustomAdapter adapter;

    User user;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        display = findViewById(R.id.display);
        listview = findViewById(R.id.listview);
        error = findViewById(R.id.error);
        back = findViewById(R.id.back);

        user = (User) getIntent().getSerializableExtra("extra_user");
        resultAlbum = (Album) getIntent().getSerializableExtra("extra_album");

        photos = resultAlbum.getPhotos();


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
        setResult(RESULT_OK, intent);
        finish();
    }

    public void SelectPhoto(int position)
    {
        index = position;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (resultCode != RESULT_OK) {
            return;
        }

        user = (User) getIntent().getSerializableExtra("extra_user");
        resultAlbum = (Album) getIntent().getSerializableExtra("extra_album");

        photos = resultAlbum.getPhotos();


        adapter = new CustomAdapter(this, R.layout.photo_pic, photos);
        listview.setAdapter(adapter);

        if(!photos.isEmpty()) {
            listview.setSelection(0);
        }
        listview.setOnItemClickListener((p, V, pos, id) -> SelectPhoto(pos));
    }


}
