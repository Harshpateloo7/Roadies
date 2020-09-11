package com.example.lenovo.rodienew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.model.*;

import java.util.ArrayList;

/**
 * Created by ADMIN on 23-02-2017.
 */

public class stateadapter extends BaseAdapter {

    Context context;
    ArrayList<statepojo> list;
    LayoutInflater inflater;

    public stateadapter(Context context, ArrayList<statepojo> list)
    {
        this.context=context;
        this.list=list;
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

        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.singlecity,null);
        TextView name=(TextView)v.findViewById(R.id.txtname);

        statepojo c=(statepojo)list.get(position);

        name.setText(c.getName());

        return v;
    }
}
