package com.example.photosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.List;
import android.widget.ListView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    User user = new User();

    List<Album> albumList = user.getAlbumList();

    private Button create, open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create = findViewById(R.id.create);
        open = findViewById(R.id.open);


        listview = findViewById(R.id.listview);
        listview.setAdapter(
                new ArrayAdapter<Album>(this, R.layout.activity_main, albumList));

        // show movie for possible edit when tapped
        listview.setOnItemClickListener((p, V, pos, id) -> showAlbum(pos));
    }

    public void showAlbum(int index)
    {
<<<<<<< HEAD
        info.setText(albumList.get(index).toString());
=======

>>>>>>> f1fb3583cc8d940ac232b9d3c0aeab6a8a3d8f8f
    }

    public void Create(View view)
    {
        Intent intent = new Intent(this, CreateAlbum.class);
        startActivityForResult(intent, 1);

    }

    public void Open(View view)
    {

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (resultCode != RESULT_OK) {
            return;
        }

    }
}
