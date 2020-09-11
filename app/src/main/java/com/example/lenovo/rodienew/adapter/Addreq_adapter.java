package com.example.lenovo.rodienew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.model.Addreqpojo;
import com.example.lenovo.rodienew.model.*;

import java.util.ArrayList;

/**
 * Created by Admin on 3/15/2017.
 */

public class Addreq_adapter extends BaseAdapter {

    Context context;
    ArrayList<Addreqpojo> list;
    LayoutInflater inflater;

    public Addreq_adapter(Context context, ArrayList<Addreqpojo> list)
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
        View v=inflater.inflate(R.layout.single_req,null);

        TextView tcity=(TextView)v.findViewById(R.id.txttcity);
        TextView name=(TextView)v.findViewById(R.id.txtname);
        TextView fcity=(TextView)v.findViewById(R.id.txtfcity);
        TextView date=(TextView)v.findViewById(R.id.txtdate);

        Addreqpojo u=(Addreqpojo) list.get(position);

        name.setText(u.getName());
        tcity.setText(u.getTcity());
        fcity.setText(u.getFcity());
        date.setText(u.getDate());

        return v;
    }
}
