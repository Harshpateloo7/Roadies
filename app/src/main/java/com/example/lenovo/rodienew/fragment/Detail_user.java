package com.example.lenovo.rodienew.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lenovo.rodienew.model.*;
import com.example.lenovo.rodienew.adapter.*;
import com.example.lenovo.rodienew.util.*;
import com.example.lenovo.rodienew.activity.*;

import com.example.lenovo.rodienew.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lenovo on 02-03-2017.
 */

@SuppressLint("ValidFragment")
public class Detail_user extends Fragment {

    Context context;
    int position;
    ListView lst;
    String url,result;
    JsonHelper js;
    ArrayList<userlistpojo>list;
    detailuser_adapter u;

    @SuppressLint("ValidFragment")
    public Detail_user(Context context, int position)
    {
        this.context=context;
        this.position=position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.useallrlist,container,false);
        lst=(ListView)v.findViewById(R.id.lstuser);
        url=ipaddress.ip+"singleuserdetail.php?id="+position;
        new getdata().execute();

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction ft=getFragmentManager().beginTransaction();
                Detail_user d=new Detail_user(context,position);
                ft.replace(R.id.content_main_admin,d);
                ft.commit();
            }
        });


        return v;
    }

    class getdata extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {

            js=new JsonHelper();
            result=js.getdata(url);
            list=new ArrayList<>();

            try {
                JSONObject jo=new JSONObject(result);
                JSONArray ja=jo.getJSONArray("data");

                for(int i=0;i<ja.length();i++)
                {
                    JSONObject jo1=ja.getJSONObject(i);
                    userlistpojo u=new userlistpojo();

                    u.setId(jo1.getString("id"));
                    u.setName(jo1.getString("name"));
                    u.setEmail(jo1.getString("email"));
                    u.setPass(jo1.getString("pass"));
                    u.setPhone(jo1.getString("phone"));
                    u.setCity(jo1.getString("city"));
                    u.setState(jo1.getString("state"));
                    u.setCountry(jo1.getString("country"));
                    u.setAddr(jo1.getString("addr"));

                    list.add(u);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            u=new detailuser_adapter(context,list);
            lst.setAdapter(u);
        }
    }
}
