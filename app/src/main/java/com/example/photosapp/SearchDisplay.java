package com.example.photosapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

public class SearchDisplay extends AppCompatActivity {


    private Album album;
    private User user;
    private Photo photo;

    Button next, previous, back;

    int photoIndex;

    TextView error;

    TextView taglist;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_display);

        next = findViewById(R.id.next);
        back = findViewById(R.id.back);
        previous = findViewById(R.id.previous);
        imageView = findViewById(R.id.imageView);
        error = findViewById(R.id.error);
        taglist = findViewById(R.id.taglist);

        user = (User) getIntent().getSerializableExtra("extra_user");
        photo = (Photo) getIntent().getSerializableExtra("extra_photo");
        album = (Album) getIntent().getSerializableExtra("extra_album");
        photoIndex = (Integer) getIntent().getIntExtra("extra_index", 0);
        taglist.setText(photo.printTags());
        try {
            Bitmap b = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photo.getPic());
            imageView.setImageBitmap(b);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

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
            taglist.setText(photo.printTags());
            try {
                Bitmap b = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photo.getPic());
                imageView.setImageBitmap(b);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public void onBackPressed(){

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
            taglist.setText(photo.printTags());
            try {
                Bitmap b = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photo.getPic());
                imageView.setImageBitmap(b);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    public void Back(View view)
    {
        Intent intent = new Intent();
        intent.putExtra("extra_user", user);
        intent.putExtra("extra_album", album);
        setResult(RESULT_OK, intent);
        finish();
    }


}
