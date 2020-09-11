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
import com.example.lenovo.rodienew.adapter.Bid_adapter;
import com.example.lenovo.rodienew.model.Addreqpojo;
import com.example.lenovo.rodienew.model.Bidpojo;
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
public class Single_bid extends Fragment {

    ListView lst;
    String url,result;
    Bid_adapter b;
    ArrayList<Bidpojo> list;
    JsonHelper js;
    Context context;

    @SuppressLint("ValidFragment")
    public Single_bid(Context context)
    {
        this.context=context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.single_bid,null);

        lst=(ListView)v.findViewById(R.id.list1);
        url= ipaddress.ip+"bid_list.php";
        new Single_bid.getdata().execute();

        lst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Bidpojo u = (Bidpojo) list.get(position);

                Detail_bid d=new Detail_bid();
                FragmentTransaction ft=getFragmentManager().beginTransaction();

                ft.replace(R.id.content_service_provider,d);
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
                    Bidpojo u=new Bidpojo();

                    u.setName(jo1.getString("name"));
                    u.setPrice(jo1.getString("price"));
                    u.setDisc(jo1.getString("disc"));
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
            b=new Bid_adapter(context,list);
            lst.setAdapter(b);

        }
    }
}
