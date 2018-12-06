package com.example.photosapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class CreateAlbum extends AppCompatActivity {

    private EditText AlbumNames;
    private Album album;
    private User user;

    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_album);
    }

    public void Confirm(View view)
    {
        ss
    }

    public void Cancel(View view)
    {
        setResult(RESULT_CANCELED);
        finish();
    }
}
