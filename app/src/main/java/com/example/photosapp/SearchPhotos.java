package com.example.photosapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

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

    public void Search(View view)
    {

    }


    public void Cancel(View view)
    {
        setResult(RESULT_CANCELED);
        finish();
    }
}
