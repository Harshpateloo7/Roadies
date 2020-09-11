package com.example.lenovo.rodienew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.model.MyservicePojo;
import com.example.lenovo.rodienew.model.userlistpojo;

import java.util.ArrayList;

/**
 * Created by ADMIN on 24-03-2017.
 */

public class MyserviceAdapter extends BaseAdapter {

    Context context;
    ArrayList<MyservicePojo> list;
    LayoutInflater inflater;



    public MyserviceAdapter(Context context, ArrayList<MyservicePojo> list)
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
        View v=inflater.inflate(R.layout.singlereq,null);

        TextView name=(TextView)v.findViewById(R.id.txtname);
        TextView disc=(TextView)v.findViewById(R.id.txtdisc);
        TextView date=(TextView)v.findViewById(R.id.txtdate);
        TextView time=(TextView)v.findViewById(R.id.txttime);


        MyservicePojo u=(MyservicePojo) list.get(position);
        name.setVisibility(View.GONE);

        disc.setText(u.getDisc());
        date.setText(u.getDate());
        time.setText(u.getTime());

        return v;
    }
}
