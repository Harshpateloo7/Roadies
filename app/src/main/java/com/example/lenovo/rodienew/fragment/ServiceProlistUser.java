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
import com.example.lenovo.rodienew.adapter.userlistadapter;
import com.example.lenovo.rodienew.model.userlistpojo;
import com.example.lenovo.rodienew.util.JsonHelper;
import com.example.lenovo.rodienew.util.ipaddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ADMIN on 24-03-2017.
 */

@SuppressLint("ValidFragment")
public class ServiceProlistUser extends Fragment {

    ListView lst;
    String url,result;
    userlistadapter u;
    ArrayList<userlistpojo> list;
    JsonHelper js;
    Context context;
    EditText edsearch;


    @SuppressLint("ValidFragment")
    public ServiceProlistUser(Context context)
    {
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.list,null);
        edsearch = (EditText)v.findViewById(R.id.edtserch);
        lst=(ListView)v.findViewById(R.id.list1);
        url= ipaddress.ip+"serpro_list.php";
        new getdata().execute();

        edsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                url = ipaddress.ip+"searchservice.php?name="+edsearch.getText().toString();
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

                ServiceProDetailUser d=new ServiceProDetailUser(u);
                FragmentTransaction ft=getFragmentManager().beginTransaction();

                ft.replace(R.id.content_main_user,d);
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


