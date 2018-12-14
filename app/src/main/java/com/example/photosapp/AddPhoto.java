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
        cancel = findViewById(R.id.back);

        user = (User) getIntent().getSerializableExtra("extra_user");
        System.out.println(user);
        album = (Album) getIntent().getSerializableExtra("extra_album");
    }

    public void Add(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        photoPickerIntent.setType("image/*");
        photoPickerIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if(user == null)
            System.out.println("yes");
        else
            System.out.println("no");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }
    public void Cancel(View view){
        setResult(RESULT_CANCELLED);
        finish();

    }
    public void onBackPressed(){

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case GALLERY_REQUEST:
                    Uri selectedImage = data.getData();
                    getContentResolver().takePersistableUriPermission(selectedImage, Intent.FLAG_GRANT_READ_URI_PERMISSION
                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    String uri = selectedImage.toString();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        user.getAlbumFromName(album.getName()).addPhoto(new Photo(uri));

                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }
        }
        System.out.println(user);

        Intent intent = new Intent();
        if(user == null){
            System.out.println("ya don goofed");
        }
        if(album == null){
            System.out.println("ya really dun goofed");
        }
        else{
            System.out.println("ok");
        }
        intent.putExtra("extra_user", user);
        intent.putExtra("extra_album", user.getAlbumFromName(album.getName()));
        setResult(RESULT_OK, intent);
        finish();

    }

}
