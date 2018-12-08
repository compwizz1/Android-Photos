package com.example.photosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

public class RemoveAlbum extends AppCompatActivity {

    private Album album;
    private User user;

    Button confirm, cancel;

    TextView error, current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_album);

        confirm = findViewById(R.id.create);
        cancel = findViewById(R.id.cancel);
        error = findViewById(R.id.error);
        current = findViewById(R.id.current);
        user = (User) getIntent().getSerializableExtra("extra_user");
        album = (Album) getIntent().getSerializableExtra("extra_album");
        current.setText(album.getName());

    }

    public void Confirm(View view)
    {
            user.removeAlbum(album);
            Intent intent = new Intent();
            intent.putExtra("extra_user", user);
            setResult(RESULT_OK, intent);
            finish();

    }

    public void Cancel(View view)
    {
        setResult(RESULT_CANCELED);
        finish();
    }
}