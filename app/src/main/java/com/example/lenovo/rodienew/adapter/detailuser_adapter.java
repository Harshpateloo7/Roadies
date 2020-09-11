package com.example.lenovo.rodienew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.model.userlistpojo;

import java.util.ArrayList;

import static android.R.id.list;

/**
 * Created by lenovo on 14-03-2017.
 */

public class detailuser_adapter extends BaseAdapter {

    Context context;
    ArrayList<userlistpojo> list;
    LayoutInflater inflater;
    public detailuser_adapter(Context context, ArrayList<userlistpojo> list)
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
        View v=inflater.inflate(R.layout.detail_user1,null);

        TextView id=(TextView)v.findViewById(R.id.txtuid);
        TextView name=(TextView)v.findViewById(R.id.txtuname);
        TextView email=(TextView)v.findViewById(R.id.txtuemail);
        TextView pass=(TextView)v.findViewById(R.id.txtupass);
        TextView ph=(TextView)v.findViewById(R.id.txtuph);
        TextView city=(TextView)v.findViewById(R.id.txtucity);
        TextView state=(TextView)v.findViewById(R.id.txtustate);
        TextView country=(TextView)v.findViewById(R.id.txtucountry);
        TextView addr=(TextView)v.findViewById(R.id.txtaddr);

        userlistpojo u=(userlistpojo)list.get(position);

        id.setText(u.getId());
        name.setText(u.getName());
        email.setText(u.getEmail());
        pass.setText(u.getPass());
        ph.setText(u.getPhone());
        city.setText(u.getCity());
        state.setText(u.getState());
        country.setText(u.getCountry());
        addr.setText(u.getAddr());

        return v;

    }
}
