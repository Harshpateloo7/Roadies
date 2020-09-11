package com.example.lenovo.rodienew.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.model.userlistpojo;

/**
 * Created by lenovo on 14-03-2017.
 */


public class DemoDetail extends Fragment {


    TextView tvid,tvnam,tvemail;
    userlistpojo u;
    public DemoDetail(userlistpojo u){

        this.u = u;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo,container,false);

         tvid = (TextView)view.findViewById(R.id.txtid);
        tvnam = (TextView)view.findViewById(R.id.txtname);

        tvemail = (TextView)view.findViewById(R.id.txtemail);

        tvid.setText(u.getId());
        tvnam.setText(u.getName());
        tvemail.setText(u.getEmail());

        return view;
    }
}
