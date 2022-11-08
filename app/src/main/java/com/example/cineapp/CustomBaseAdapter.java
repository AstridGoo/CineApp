package com.example.cineapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<Film> films;
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, ArrayList<Film> films){
        this.context = ctx;
        this.films = films;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return films.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView txtView = (TextView) view.findViewById(R.id.textListView);
        ImageView movieImg = (ImageView) view.findViewById(R.id.imageIcon);
        txtView.setText(films.get(i).getTitle());
        switch (films.get(i).getPlace()){
            case "Cinéma":
                movieImg.setImageResource(R.drawable.icon_cinema);
                break;
            case "Théâtre":
                movieImg.setImageResource(R.drawable.icon_theatre);
                break;
            case "TV":
                movieImg.setImageResource(R.drawable.icon_tv);
                break;

        }
        return view;
    }
}
