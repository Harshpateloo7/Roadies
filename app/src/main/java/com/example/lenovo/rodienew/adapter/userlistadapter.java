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
 * Created by lenovo on 14-03-2017.
 */

public class userlistadapter extends BaseAdapter {

    Context context;
    ArrayList<userlistpojo> list;
    LayoutInflater inflater;

    public userlistadapter(Context context, ArrayList<userlistpojo> list)
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
        View v=inflater.inflate(R.layout.userlistsingle,null);

        TextView id=(TextView)v.findViewById(R.id.txtid);
        TextView name=(TextView)v.findViewById(R.id.txtusername);
        TextView email=(TextView)v.findViewById(R.id.txtuseremail);

        userlistpojo u=(userlistpojo)list.get(position);

        id.setText(u.getId());
        name.setText(u.getName());
        email.setText(u.getEmail());

        return v;
    }
}
