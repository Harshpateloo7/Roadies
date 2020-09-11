package com.example.lenovo.rodienew.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.model.Bidpojo;

/**
 * Created by Admin on 3/9/2017.
 */

public class Detail_bid extends Fragment {

    TextView name,add,ph,price,disc,terms;
    Bidpojo u;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.detail_bid,null);

        name=(TextView)v.findViewById(R.id.txtname);
       add=(TextView)v.findViewById(R.id.txtadd);
        ph=(TextView)v.findViewById(R.id.txtph);
        price=(TextView)v.findViewById(R.id.txtprice);
        disc=(TextView)v.findViewById(R.id.txtdisc);
        terms=(TextView)v.findViewById(R.id.txtterm);

//        name.setText(u.getName());
//        add.setText(u.getAdd());
//        ph.setText(u.getPh());
//        price.setText(u.getPrice());
//        disc.setText(u.getDisc());
//        terms.setText(u.getTerms());

        return v;
    }
}
