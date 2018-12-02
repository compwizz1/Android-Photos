package com.example.photosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.List;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    ListView listview;
    User user = new User();

    List<Album> albumList = user.getAlbumList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listview = findViewById(R.id.listview);
        listview.setAdapter(
                new ArrayAdapter<Album>(this, R.layout.activity_main, albumList));

        // show movie for possible edit when tapped
        listview.setOnItemClickListener((p, V, pos, id) -> showAlbum(pos));
    }

    public void showAlbum(int index)
    {

    }
}
