package com.example.cineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private RadioGroup group;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatePicker();
        dateButton = findViewById(R.id.filmDate);
        dateButton.setText(getTodaysDate());

        imageView = findViewById(R.id.imageView2);
        group = findViewById(R.id.filmPlace);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btnCinema:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_cinema));
                        break;
                    case R.id.btnTheatre:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_theatre));
                        break;
                    case R.id.btnTV:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_tv));
                        break;
                }
            }
        });
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month=month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day){
                month = month+1;
                String date = makeDateString(day,month,year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return day +" "+ getMonthFormat(month) +" "+ year;
    }

    private String getMonthFormat(int month) {
        String[] nomMois = { "JAN", "FEV", "MARS", "AVR", "MAI", "JUIN", "JUIL",
                "AOUT", "SEPT", "OCT", "NOV", "DEC" };
        return nomMois[month-1];
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void saveData(View view){

        String title = ((EditText) findViewById(R.id.filmTitle)).getText().toString();
        String director = ((EditText) findViewById(R.id.filmDirector)).getText().toString();
        String partners = ((EditText) findViewById(R.id.filmPartners)).getText().toString();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.filmPlace);
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        String date = ((Button) findViewById(R.id.filmDate)).getText().toString();

        if(!title.equals("") && !director.equals("") && !partners.equals("") && !date.equals("") && radioButton != null) {

            String place = radioButton.getText().toString();

            Film film = new Film(
                    title,
                    director,
                    partners,
                    place,
                    date);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myFilm = database.getReference().child("films").push();
            myFilm.child("film").setValue(film);

            Toast.makeText(MainActivity.this, "Le film a bien été sauvegardé !", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs !", Toast.LENGTH_LONG).show();
        }
    }
}