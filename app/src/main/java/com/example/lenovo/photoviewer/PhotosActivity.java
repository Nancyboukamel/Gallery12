package com.example.lenovo.photoviewer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import com.example.lenovo.photoviewer.adapter.GridViewAdapter;
import java.util.ArrayList;

public class PhotosActivity extends AppCompatActivity{
    int int_position;
    private GridView gridView;
   GridViewAdapter adapter;
    ArrayList<String> selectedStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folder_gallery);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        gridView = (GridView)findViewById(R.id.myGrid);
         int_position = getIntent().getIntExtra("value", 0);
         adapter = new GridViewAdapter(this,Gallery.al_images,int_position);
        gridView.setAdapter(adapter);

    }

    }



