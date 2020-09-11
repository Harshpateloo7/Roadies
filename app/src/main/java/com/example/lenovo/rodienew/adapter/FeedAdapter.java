package com.example.lenovo.rodienew.adapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.model.Feedpojo;

import java.util.ArrayList;

/**
 * Created by ADMIN on 24-03-2017.
 */

public class FeedAdapter extends BaseAdapter {

    Context context;
    ArrayList<Feedpojo> list;
    LayoutInflater inflater;
    TextView tvname;

    public  FeedAdapter(Context context, ArrayList<Feedpojo> list){

        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
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


        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.singlefeedbackadmin,parent,false);
        tvname = (TextView)view.findViewById(R.id.txtfeedback);

        Feedpojo f = (Feedpojo)list.get(position);

        tvname.setText(f.getName());

        return view;
    }
}
