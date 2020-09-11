package com.example.lenovo.rodienew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.model.Bidpojo;
import com.example.lenovo.rodienew.model.*;

import java.util.ArrayList;

/**
 * Created by Admin on 3/16/2017.
 */

public class Bid_adapter extends BaseAdapter{

    Context context;
    ArrayList<Bidpojo> list;
    LayoutInflater inflater;

    public Bid_adapter(Context context, ArrayList<Bidpojo> list)
    {
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return 0;
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
        View v=inflater.inflate(R.layout.single_bid,null);

        TextView name=(TextView)v.findViewById(R.id.txtname);
        TextView price=(TextView)v.findViewById(R.id.txtprice);
        TextView disc=(TextView)v.findViewById(R.id.txtdisc);

        Bidpojo u=(Bidpojo)list.get(position);

        name.setText(u.getName());
        price.setText(u.getPrice());
        disc.setText(u.getDisc());


        return v;
    }
}
