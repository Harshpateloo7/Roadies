package com.example.lenovo.rodienew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.model.Bidpojo;

import java.util.ArrayList;

/**
 * Created by lenovo on 29-03-2017.
 */

public class BidAd extends BaseAdapter {

    Context context;
    ArrayList<Bidpojo> list;
    LayoutInflater inflater;

    public BidAd(Context context, ArrayList<Bidpojo> list)
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
        View v=inflater.inflate(R.layout.singlebid,null);

        TextView name=(TextView)v.findViewById(R.id.txtbidprice);
        TextView price=(TextView)v.findViewById(R.id.txtbiddisc);
        TextView disc=(TextView)v.findViewById(R.id.txtbidterms);

        Bidpojo u=(Bidpojo)list.get(position);

        name.setText(u.getPrice());
        price.setText(u.getDisc());
        disc.setText(u.getTerms());


        return v;
    }
}
