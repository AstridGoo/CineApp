package com.example.cineapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    String listMovie[];
    int listImage[];
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, String[] movieList, int[] images){
        this.context = ctx;
        this.listMovie = movieList;
        this.listImage = images;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return listMovie.length;
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
        txtView.setText(listMovie[i]);
        movieImg.setImageResource(listImage[i]);
        return view;
    }
}
