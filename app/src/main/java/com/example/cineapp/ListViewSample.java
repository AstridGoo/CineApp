package com.example.cineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListViewSample extends AppCompatActivity {
    String movieList[] = {"Indiana Jones", "Harry Potter", "James Bond", "Toy Story", "L'Avare", "OSS 117"};
    int movieImage[] = {R.drawable.icon_cinema, R.drawable.icon_tv, R.drawable.icon_cinema,
            R.drawable.icon_tv, R.drawable.icon_theatre, R.drawable.icon_tv};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = (ListView) findViewById(R.id.customListView);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), movieList, movieImage);
        listView.setAdapter(customBaseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i("CUSTOM_LIST_VIEW", "Item is clicked @ Position :: " + position);
            }
        });
    }
}