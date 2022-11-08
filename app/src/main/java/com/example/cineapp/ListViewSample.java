package com.example.cineapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class ListViewSample extends AppCompatActivity {

    private Button button;
    ListView listView;
    ArrayList<Film> films = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query mesFilms = reference.child("films");

        mesFilms.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Film film = snapshot.getValue(Film.class);
                films.add(film);
                promptList();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        button = (Button) findViewById(R.id.buttonPlus);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddMovie();
            }
        });
    }

    private void promptList(){
        listView = (ListView) findViewById(R.id.customListView);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), films);
        listView.setAdapter(customBaseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(), MovieView.class);
                intent.putExtra("film", films.get(position));
                startActivity(intent);
            }
        });
    }

    public void openAddMovie(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}