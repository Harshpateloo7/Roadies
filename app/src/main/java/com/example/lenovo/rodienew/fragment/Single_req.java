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

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.adapter.Addreq_adapter;
import com.example.lenovo.rodienew.adapter.userlistadapter;
import com.example.lenovo.rodienew.model.Addreqpojo;
import com.example.lenovo.rodienew.model.userlistpojo;
import com.example.lenovo.rodienew.util.JsonHelper;
import com.example.lenovo.rodienew.util.ipaddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Admin on 3/9/2017.
 */

@SuppressLint("ValidFragment")
public class Single_req extends Fragment {

    ListView lst;
    String url,result;
    Addreq_adapter a;
    ArrayList<Addreqpojo> list;
    JsonHelper js;
    Context context;

    @SuppressLint("ValidFragment")
    public Single_req(Context context)
    {
        this.context=context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.list,null);

        lst=(ListView)v.findViewById(R.id.list1);
        url= ipaddress.ip+"addreq_list.php";
        new Single_req.getdata().execute();

        lst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Addreqpojo u = (Addreqpojo)list.get(position);

                Detail_service d=new Detail_service(u);
                FragmentTransaction ft=getFragmentManager().beginTransaction();

                ft.replace(R.id.content_main_user,d);
                ft.commit();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }
    class getdata extends AsyncTask<Void,Void,Void>{


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
                    Addreqpojo u=new Addreqpojo();

                    u.setName(jo1.getString("name"));
                    u.setTcity( jo1.getString("title"));
                    u.setDate(jo1.getString("date"));
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

            a=new Addreq_adapter(context,list);
            lst.setAdapter(a);

        }
    }
}
