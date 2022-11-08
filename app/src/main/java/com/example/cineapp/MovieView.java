package com.example.cineapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MovieView extends AppCompatActivity {

    Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_view);

        film = (Film) getIntent().getSerializableExtra("film");

        TextView title = (TextView) findViewById(R.id.filmTitle);
        title.setText(film.getTitle());

        TextView director = (TextView) findViewById(R.id.filmDirector);
        director.setText(film.getDirector());

        TextView partners = (TextView) findViewById(R.id.textViewAmis);
        partners.setText(film.getPartners());

        TextView place = (TextView) findViewById(R.id.textViewLieu);
        place.setText(film.getPlace());

        TextView date = (TextView) findViewById(R.id.textViewDate);
        date.setText(film.getTitle());

        ImageView image = (ImageView) findViewById(R.id.imageView2);
        switch (film.getPlace()) {
            case "Cinéma":
                image.setImageResource(R.drawable.icon_cinema);
                break;
            case "Théâtre":
                image.setImageResource(R.drawable.icon_theatre);
                break;
            case "TV":
                image.setImageResource(R.drawable.icon_tv);
                break;
        }
    }

    public void deleteFilm(View view){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query monFilm = reference.child("films").orderByChild("title").equalTo(film.getTitle());

        monFilm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot filmSnapshot: snapshot.getChildren()){
                    filmSnapshot.getRef().removeValue();
                }
                Intent intent = new Intent(getApplicationContext(), ListViewSample.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}