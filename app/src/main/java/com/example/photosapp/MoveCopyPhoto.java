package com.example.photosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.List;

public class MoveCopyPhoto extends AppCompatActivity {

    private Album album;
    private User user;
    private Photo photo;

    int albumIndex;

    int photoIndex;

    TextView error, actionMessage;

    Button confirm, cancel;

    Switch switchAction;

    ListView listview;

    List<Album> albumList;

    boolean action; //true means move photo and false means copy photo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.move_copy_photo);

        confirm = findViewById(R.id.confirm);
        cancel = findViewById(R.id.cancel);
        switchAction = findViewById(R.id.switchAction);
        listview = findViewById(R.id.listview);
        error = findViewById(R.id.error);
        actionMessage = findViewById(R.id.actionMessage);

        user = (User) getIntent().getSerializableExtra("extra_user");
        photo = (Photo) getIntent().getSerializableExtra("extra_photo");
        album = (Album) getIntent().getSerializableExtra("extra_album");
        photoIndex = (Integer) getIntent().getIntExtra("extra_index", 0);

        album = user.getAlbumFromName(album.getName());
        albumList = user.getAlbumList();


        listview = findViewById(R.id.listview);
        ArrayAdapter adapter = new ArrayAdapter<Album>(this, R.layout.album_text, albumList);
        listview.setAdapter(adapter);

        if(!albumList.isEmpty()) {
            listview.setSelection(0);
            albumIndex = 0;
        }
        adapter.notifyDataSetChanged();;
        listview.setOnItemClickListener((p, V, pos, id) -> SelectAlbum(pos));
        switchAction.setChecked(false);
        actionMessage.setText("Copy Photo to Album");
        switchAction.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                action = isChecked;
                if(action)
                {
                    actionMessage.setText("Move Photo to Album");
                }
                else if(!action)
                {
                    actionMessage.setText("Copy Photo to Album");
                }
            }
        });

    }

     public void SelectAlbum(int position)
     {
         albumIndex = position;
     }

     public void Confirm(View view)
     {
         Album chosen = user.getAlbumList().get(albumIndex);
         if(action)
         {
             chosen.addPhoto(photo);
             album.removePhoto(album.getPhotos().get(photoIndex));
         }
         else if(!action)
         {
             chosen.addPhoto(photo);
         }
         try {
             User.writeUser(user, this);
         } catch (IOException e) {
             e.printStackTrace();
         }
         Cancel(view);
     }

     public void Cancel(View view)
     {
         Intent intent = new Intent();
         intent.putExtra("extra_user", user);
         intent.putExtra("extra_album",album);
         intent.putExtra("extra_photo", photo);
         intent.putExtra("extra_index", photoIndex);
         setResult(RESULT_OK,intent);
         finish();

     }




}
