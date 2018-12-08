package com.example.photosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import android.widget.ListView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    User user = new User();
    TextView info;
    List<Album> albumList = user.getAlbumList();
    int index;

    private Button create, open, rename, remove, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create = findViewById(R.id.create);
        open = findViewById(R.id.open);
        rename = findViewById(R.id.rename);
        remove = findViewById(R.id.remove);
        search = findViewById(R.id.search);

        info = findViewById(R.id.info);
        //String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
        //        "WebOS","Ubuntu","Windows7","Max OS X"};
        listview = findViewById(R.id.listview);
        albumList.add(new Album("alb111"));
        albumList.add(new Album("album2222"));
        ArrayAdapter adapter = new ArrayAdapter<Album>(this, R.layout.album_text, albumList);
        listview.setAdapter(adapter);
        // show album for possible edit when tapped
        if(!albumList.isEmpty()) {
            listview.setSelection(0);
        }
        listview.setOnItemClickListener((p, V, pos, id) -> SelectAlbum(pos));
    }

    public void SelectAlbum(int position)
    {
        index = position;

    }

    public void Create(View view)
    {

        Intent intent = new Intent(this, CreateAlbum.class);
        intent.putExtra("extra_user", user);
        startActivityForResult(intent, 1);

    }

    public void Open(View view)
    {

    }

    public void Rename(View view)
    {
        Album selected = albumList.get(index);
        Intent intent = new Intent(this, RenameAlbum.class);
        intent.putExtra("extra_user", user);
        intent.putExtra("extra_album", selected);
        startActivityForResult(intent, 1);

    }

    public void Remove(View view)
    {
        Album selected = albumList.get(index);
        Intent intent = new Intent(this, RemoveAlbum.class);
        intent.putExtra("extra_user", user);
        intent.putExtra("extra_album", selected);
        startActivityForResult(intent, 1);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (resultCode != RESULT_OK) {
            return;
        }
        user = (User) intent.getSerializableExtra("extra_user");
        albumList = user.getAlbumList();
        listview = findViewById(R.id.listview);
        ArrayAdapter adapter = new ArrayAdapter<Album>(this, R.layout.album_text, albumList);
        listview.setAdapter(adapter);
        if(!albumList.isEmpty()) {
            listview.setSelection(0);
        }
        listview.setOnItemClickListener((p, V, pos, id) -> SelectAlbum(pos));
    }
}
