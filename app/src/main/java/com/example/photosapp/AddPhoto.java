package com.example.photosapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import static android.telephony.MbmsDownloadSession.RESULT_CANCELLED;

public class AddPhoto extends AppCompatActivity {

    Button add, cancel;
    User user;
    Album album;
    public static final int GALLERY_REQUEST = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_photo);

        add = findViewById(R.id.add);
        cancel = findViewById(R.id.cancel);

        user = (User) getIntent().getSerializableExtra("extra_user");
        album = (Album) getIntent().getSerializableExtra("extra_album");
    }

    public void Add(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }
    public void Cancel(View view){
        setResult(RESULT_CANCELLED);
        finish();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case GALLERY_REQUEST:
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        user.getAlbumFromName(album.getName()).addPhoto(new Photo(bitmap));
                        //carImage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }
        }
        Intent intent = new Intent();
        intent.putExtra("extra_user", user);
        intent.putExtra("extra_album", user.getAlbumFromName(album.getName()));
        setResult(RESULT_OK, intent);
        finish();

    }

}
