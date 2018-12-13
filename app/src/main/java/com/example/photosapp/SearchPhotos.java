package com.example.photosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchPhotos extends AppCompatActivity {

    Spinner type1,type2;

    EditText val1, val2;

    Button search, cancel;

    Switch action;

    User user;

    TextView error;

    boolean actionSearch; // True/On means 'AND' False/Off means 'OR'

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        type1 = findViewById(R.id.type1);
        type2 = findViewById(R.id.type2);
        val1 = findViewById(R.id.val1);
        val2 = findViewById(R.id.val2);
        search = findViewById(R.id.search);
        cancel = findViewById(R.id.cancel);
        action = findViewById(R.id.action);
        error = findViewById(R.id.error);

        user = (User) getIntent().getSerializableExtra("extra_user");

        ArrayAdapter<String> spinadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, user.getTagTypes());
        type1.setAdapter(spinadapter);
        type1.setSelection(0);

        type2.setAdapter(spinadapter);
        type2.setSelection(0);

        action.setChecked(false);
        action.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                actionSearch = isChecked;
            }
        });

    }
    public void onBackPressed(){

    }

    public void Search(View view)
    {
        List<Photo> searchPics;
        if(val1.getText().toString().isEmpty())
        {
            error.setText("Please enter a value into the first column");
            return;
        }
        else if (val2.getText().toString().isEmpty())
        {
            searchPics = singleSearch();
        }
        else
        {
            searchPics = doubleSearch();
        }
        if (searchPics!= null && !searchPics.isEmpty())
        {
            Album album = new Album("Search Results");
            album.setPhotosList(searchPics);
            Intent intent = new Intent(this, SearchResults.class);
            intent.putExtra("extra_user", user);
            intent.putExtra("extra_album", album);
            startActivityForResult(intent, 1);

        }
        else {
            error.setText("No matches found. Enter different search criteria");
        }

    }


    public void Cancel(View view)
    {
        setResult(RESULT_CANCELED);
        finish();
    }

    /**
     * Search based on one tag
     *
     * This method searches for a list of photos through out all the albums based on one tag
     * @return results The list of photos matching the search criteria
     */
    public List<Photo> singleSearch()
    {
        Tag search = new Tag(type1.getSelectedItem().toString(), val1.getText().toString());
        List<Photo> results = new ArrayList<Photo>();
        for(Album i: user.getAlbumList())
        {
            for(Photo j: i.getPhotos())
            {
                if(j.searchTags(search))
                {
                    results.add(j);
                }
            }
        }
        return results;


    }

    /**
     * Search based on two Tags
     *
     * This method searches for a list of photos from all the album based on a disjunctive or conjunctive search between two tag types and value
     * @return results The list of photos matching the search criteria
     */
    public List<Photo> doubleSearch()
    {
        Tag search1 = new Tag(type1.getSelectedItem().toString(), val1.getText().toString());
        Boolean first = true;
        Tag search2 = new Tag(type2.getSelectedItem().toString(), val2.getText().toString());
        Boolean second = true;
        List<Photo> results = new ArrayList<Photo>();
        for(Album i: user.getAlbumList())
        {
            for(Photo j: i.getPhotos())
            {
                first = j.searchTags(search1);
                second = j.searchTags(search2);
                if(actionSearch)
                {
                    if(first && second)
                    {
                        results.add(j);
                    }
                }
                else if(!actionSearch)
                {
                    if(first || second)
                    {
                        results.add(j);
                    }
                }

            }
        }
        return results;

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (resultCode != RESULT_OK) {
            return;
        }

        user = (User) getIntent().getSerializableExtra("extra_user");

        ArrayAdapter<String> spinadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, user.getTagTypes());
        type1.setAdapter(spinadapter);
        type1.setSelection(0);

        type2.setAdapter(spinadapter);
        type2.setSelection(0);

        action.setChecked(false);
        action.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                actionSearch = isChecked;
            }
        });

    }
}
