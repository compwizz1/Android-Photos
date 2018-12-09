package com.example.photosapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PhotoDisplay extends AppCompatActivity {


    private Album album;
    private User user;
    private Photo photo;

    Button next, previous, back;

    int photoIndex;

    TextView error;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_display);

        next = findViewById(R.id.next);
        back = findViewById(R.id.back);
        previous = findViewById(R.id.previous);
        imageView = findViewById(R.id.imageView);

        user = (User) getIntent().getSerializableExtra("extra_user");
        photo = (Photo) getIntent().getSerializableExtra("extra_photo");
        album = (Album) getIntent().getSerializableExtra("extra_album");

        imageView.setImageBitmap(photo.getPic());

    }

    public void Next(View view)
    {
        if(photoIndex+1>=album.getPhotos().size())
        {
            error.setText("No next photo. Last photo in the list");
        }
        else {
            photoIndex++;
            photo = album.getPhotos().get(photoIndex);
            imageView.setImageBitmap(photo.getPic());
        }
    }

    public void Previous(View view)
    {
        if(photoIndex-1<0)
        {
            error.setText("No previous photo. First photo in the list");
        }
        else {
            photoIndex--;
            photo = album.getPhotos().get(photoIndex);
            imageView.setImageBitmap(photo.getPic());
        }
    }

    public void Back(View view)
    {
        setResult(RESULT_OK);
        finish();
    }

}
