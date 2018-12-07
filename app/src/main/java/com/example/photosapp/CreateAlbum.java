package com.example.photosapp;

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
         user = new User();
        AlbumName = findViewById(R.id.name);

        Bundle bundle = getIntent().getExtras();
        ACTION = bundle.getInt("ACTION");
        if (ACTION == 2) {
            AlbumName.setText(bundle.getString(Album_Name));
        }
        album = user.getAlbumFromName(bundle.getString(Album_Name));



    }

    public void Confirm(View view)
    {
        if(ACTION ==1) {
            if (user.hasAlbumName(AlbumName.getText().toString())) {
                error.setText("Error. Album name already Exists");
                return;
            } else {
                user.addAlbum(AlbumName.getText().toString());
                setResult(RESULT_OK);
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
                setResult(RESULT_OK);
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
