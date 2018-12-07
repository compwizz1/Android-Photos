package com.example.photosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class CreateAlbum extends AppCompatActivity {

    private EditText AlbumName;
    private Album album;
    private User user;

    TextView error;

    public static final String Album_Name = "albumName";
    public int ACTION;
    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_album);

        Bundle bundle = getIntent().getExtras();
        ACTION = bundle.getInt("ACTION");

        user = (User) getIntent().getSerializableExtra("extra_user");
        if (ACTION == 2) {
            album = (Album) getIntent().getSerializableExtra("extra_album");
            AlbumName.setText(bundle.getString(Album_Name));

        }


    }

    public void Confirm(View view)
    {
        String newName = AlbumName.getText().toString();
        if(newName == null || newName.length()<1)
        {
            error.setText("Error, Please enter a name");
            return;
        }
        if(ACTION ==1) {
            if (user.hasAlbumName(AlbumName.getText().toString())) {
                error.setText("Error. Album name already Exists");
                return;
            } else {
                user.addAlbum(AlbumName.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("user", user);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
        else if(ACTION ==2)
        {
            if (user.hasAlbumName(AlbumName.getText().toString())) {
                error.setText("Error. Album name already Exists");
                return;
            }
            else {
                album.rename(AlbumName.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("user", user);
                setResult(RESULT_OK, intent);
                finish();
            }


        }


    }

    public void Cancel(View view)
    {
        setResult(RESULT_CANCELED);
        finish();
    }
}
