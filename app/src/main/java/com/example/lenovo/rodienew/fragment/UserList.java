package com.example.lenovo.rodienew.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lenovo.rodienew.R;

import java.util.ArrayList;
import com.example.lenovo.rodienew.model.*;
import com.example.lenovo.rodienew.adapter.*;
import com.example.lenovo.rodienew.util.*;
import com.example.lenovo.rodienew.activity.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lenovo on 14-03-2017.
 */

@SuppressLint("ValidFragment")
public class UserList extends Fragment {


    ListView lst;
    String url,result;
    userlistadapter u;
    ArrayList<userlistpojo> list;
    EditText edsearch;
    JsonHelper js;
    Context context;

    @SuppressLint("ValidFragment")
    public UserList(Context context)
    {
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.useallrlist,null);
        edsearch = (EditText)v.findViewById(R.id.edsearch);

        lst=(ListView)v.findViewById(R.id.lstuser);
        url=ipaddress.ip+"userlist.php";
        new getdata().execute();

        edsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                url = ipaddress.ip+"searchuser.php?name="+edsearch.getText().toString();
                new getdata().execute();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                userlistpojo u = (userlistpojo)list.get(position);

                User_list_detail d=new User_list_detail(u);
                FragmentTransaction ft=getFragmentManager().beginTransaction();

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
                    u.setPhone(jo1.getString("phone"));
                    u.setPass(jo1.getString("pass"));
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

            u=new userlistadapter(context,list);
            lst.setAdapter(u);
        }
    }
}
