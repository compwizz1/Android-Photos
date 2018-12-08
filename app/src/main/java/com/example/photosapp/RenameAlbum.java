package com.example.photosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

public class RenameAlbum extends AppCompatActivity {

    private EditText AlbumName;
    private Album album;
    private User user;

    Button confirm, cancel;

    TextView error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rename_album);

        confirm = findViewById(R.id.create);
        cancel = findViewById(R.id.cancel);
        error = findViewById(R.id.error);
        AlbumName = findViewById(R.id.AlbumName);
        user = (User) getIntent().getSerializableExtra("extra_user");
        album = (Album) getIntent().getSerializableExtra("extra_album");
        AlbumName.setText(album.getName(),TextView.BufferType.EDITABLE);

    }

    public void Confirm(View view)
    {
        String newName = AlbumName.getText().toString();
        if(newName == null || newName.length()<1)
        {
            error.setText("Error, Please enter a name");
            return;
        }
        else if (user.hasAlbumName(newName))
        {
                error.setText("Error. Album name already Exists");
                return;
        }
        else
        {
            user.getAlbum(album).rename(newName);
            Intent intent = new Intent();
            intent.putExtra("extra_user", user);
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    public void Cancel(View view)
    {
        setResult(RESULT_CANCELED);
        finish();
    }
}

