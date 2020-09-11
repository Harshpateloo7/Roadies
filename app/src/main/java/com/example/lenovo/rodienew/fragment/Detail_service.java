package com.example.lenovo.rodienew.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.model.Addreqpojo;
import com.example.lenovo.rodienew.model.userlistpojo;

/**
 * Created by Admin on 3/9/2017.
 */

@SuppressLint("ValidFragment")
public class Detail_service extends Fragment {

    TextView name,ph,title,disc,to_add,f_add,date;
    Spinner t_city,t_state,t_coun,f_city,f_state,f_coun;
     Addreqpojo u;

    @SuppressLint("ValidFragment")
    public Detail_service(Addreqpojo u){

        this.u = u;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.detail_service,null);

        name=(TextView)v.findViewById(R.id.txtuname);
        ph=(TextView)v.findViewById(R.id.txtuph);
        title=(TextView)v.findViewById(R.id.txttitle);
        disc=(TextView)v.findViewById(R.id.txtdisc);
        to_add=(EditText)v.findViewById(R.id.edtaddress);
        f_add=(EditText)v.findViewById(R.id.edtaddress1);
        date=(EditText)v.findViewById(R.id.edtdate);
        t_city=(Spinner)v.findViewById(R.id.spncity);
        f_city=(Spinner)v.findViewById(R.id.spncity1);
        t_state=(Spinner)v.findViewById(R.id.spnstate);
        f_state=(Spinner)v.findViewById(R.id.spnstate1);
        t_coun=(Spinner)v.findViewById(R.id.spncountry);
        f_coun=(Spinner)v.findViewById(R.id.spncountry1);

        name.setText(u.getName());
       /* ph.setText(u.getPh());
        title.setText(u.getTitle());
        disc.setText(u.getDisc());*/
        to_add.setText(u.getTadd());
        date.setText(u.getDate());

        return v;
    }
}
