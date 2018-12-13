package com.example.photosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class PhotoTags extends AppCompatActivity {

    private Album album;
    private User user;
    private Photo photo;

    Button addTag, remove, back;

    Spinner spinner;

    ListView listview;

    List<Tag> tagList;

    EditText tagValue;

    int photoIndex;

    int tagIndex;

    TextView error;

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_tags);

        addTag = findViewById(R.id.addTag);
        back = findViewById(R.id.back);
        remove = findViewById(R.id.remove);
        spinner = findViewById(R.id.spinner);
        error = findViewById(R.id.error);
        listview = findViewById(R.id.listview);
        tagValue = findViewById(R.id.tagValue);

        user = (User) getIntent().getSerializableExtra("extra_user");
        photo = (Photo) getIntent().getSerializableExtra("extra_photo");
        album = (Album) getIntent().getSerializableExtra("extra_album");
        photoIndex = (Integer) getIntent().getIntExtra("extra_index", 0);

        album = user.getAlbumFromName(album.getName());
        photo = album.getPhotos().get(photoIndex);
        tagList = photo.getTags();

        adapter = new ArrayAdapter<Tag>(this, R.layout.album_text, tagList);
        listview.setAdapter(adapter);

        if(!tagList.isEmpty()) {
            listview.setSelection(0);
            tagIndex = 0;
        }
        listview.setOnItemClickListener((p, V, pos, id) -> SelectTag(pos));

        ArrayAdapter<String> spinadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, user.getTagTypes());
        spinner.setAdapter(spinadapter);
        spinner.setSelection(0);


    }

    public void SelectTag(int position)
    {
        tagIndex = position;
    }

    public void AddTag(View view)
    {
        String value = tagValue.getText().toString();
        if(value == null)
        {
            error.setText("Error. Please enter a value for the tag");
            return;
        }
        String tagType = spinner.getSelectedItem().toString();
        boolean added = photo.addTag(new Tag(tagType, value));
        if(!added) {
            error.setText("Tag already added, try another one");
            return;
        }
        try {
            User.writeUser(user, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tagList = photo.getTags();
        adapter.notifyDataSetChanged(); //used to update tag list
        return;
    }

    public void Remove(View view)
    {
        if(tagList.isEmpty())
        {
            error.setText("List of tags is empty.");
            return;

        }
        photo.removeTag(photo.getTags().get(tagIndex));
        try {
            User.writeUser(user, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tagList = photo.getTags();
        adapter.notifyDataSetChanged();
        return;


    }
    public void onBackPressed(){

    }

    public void Back(View view)
    {
        Intent intent = new Intent();
        intent.putExtra("extra_user", user);
        intent.putExtra("extra_album", album);
        intent.putExtra("extra_index", photoIndex);
        setResult(RESULT_OK, intent);
        finish();

    }
}