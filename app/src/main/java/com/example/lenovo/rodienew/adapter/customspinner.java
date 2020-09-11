package com.example.lenovo.rodienew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.rodienew.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 01-02-2017.
 */

public class customspinner extends BaseAdapter {

    Context context;
    int logo[];
    ArrayList<String> cat;
    LayoutInflater inflater;

    public customspinner(Context context,int[] logo,ArrayList<String> cat)
    {
        this.context=context;
        this.logo=logo;
        this.cat=cat;
    }

    @Override
    public int getCount() {
        return logo.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.singleloginspinner,parent,false);
        TextView txt=(TextView)v.findViewById(R.id.txtcat);
        ImageView img=(ImageView)v.findViewById(R.id.imglogo);
        txt.setText(cat.get(position));
        img.setImageResource(logo[position]);
        return v;
    }
}
