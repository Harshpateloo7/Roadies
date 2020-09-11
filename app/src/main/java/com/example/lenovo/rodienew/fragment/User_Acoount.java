package com.example.lenovo.rodienew.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.rodienew.R;

import org.w3c.dom.Text;

import static android.content.Context.MODE_WORLD_READABLE;

/**
 * Created by ADMIN on 24-02-2017.
 */

@SuppressLint("ValidFragment")
public class User_Acoount extends Fragment {

    TextView viewhistory,name,email,myrequest,changepass;
    Button editprofile;
    ImageView img;
    SharedPreferences sp;
    String sname,semail;
    Context context;

    @SuppressLint("ValidFragment")
    public  User_Acoount(Context context)
    {
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.user_account,null);

        img=(ImageView)v.findViewById(R.id.imguser);
        editprofile=(Button) v.findViewById(R.id.btneditprofile);
        viewhistory=(TextView) v.findViewById(R.id.txtviewhistory);
        name=(TextView) v.findViewById(R.id.txtuname);
        email=(TextView) v.findViewById(R.id.txtuemail);
        myrequest=(TextView) v.findViewById(R.id.txtmyrequest);
        changepass=(TextView)v.findViewById(R.id.txtchangepass);

        sp=getActivity().getSharedPreferences("pref",MODE_WORLD_READABLE);
        sname=sp.getString("name","");
        semail=sp.getString("email","");
        name.setText(sname);
        email.setText(semail);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f=new Fragment();
                User_Profile h=new User_Profile(context);
                FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.addToBackStack(f.getClass().getName());
                ft.replace(R.id.content_main_user,h);
                ft.commit();

            }
        });

        viewhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f=new Fragment();
                User_ChangePassword h=new User_ChangePassword(context);
                FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.addToBackStack(f.getClass().getName());
                ft.replace(R.id.content_main_user,h);
                ft.commit();

            }
        });

        return v;
    }

}
