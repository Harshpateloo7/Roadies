package com.example.lenovo.rodienew.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.adapter.BidAd;
import com.example.lenovo.rodienew.model.Bidpojo;
import com.example.lenovo.rodienew.util.JsonHelper;
import com.example.lenovo.rodienew.util.ipaddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lenovo on 31-03-2017.
 */

@SuppressLint("ValidFragment")
public class Bidlist extends Fragment {

    ListView lv;
    String bidurl,bidresult,acbidresult,acbidurl;
    ArrayList<Bidpojo> listbid;


    JsonHelper js;
    String id;

    public Bidlist(String id){


        this.id = id;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listall,container,false);

        lv = (ListView)view.findViewById(R.id.lv);

        bidurl = ipaddress.ip+"allbid.php?id="+id;
        new getbids().execute();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Bidpojo b = (Bidpojo)listbid.get(position);
               AlertDialog ad = new AlertDialog.Builder(getActivity()).setTitle("Are you sure?").setMessage("Do you want to aproove?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        acbidurl = ipaddress.ip+"acceptbd.php?id="+b.getId();
                        new acceptbid().execute();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });


        return view;
    }



    class getbids extends AsyncTask<Void,Void,Void> {



        @Override
        protected Void doInBackground(Void... params) {

            js = new JsonHelper();
            bidresult = js.getdata(bidurl);
            Log.d("url is ",bidurl);
            Log.d("result is ",bidresult);

            listbid = new ArrayList<>();
            try {
                JSONObject jo = new JSONObject(bidresult);
                JSONArray ja = jo.getJSONArray("bid");

                for (int i = 0;i<ja.length();i++){

                    JSONObject jo1 = ja.getJSONObject(i);
                    Bidpojo b = new Bidpojo();
                    b.setId(jo1.getString("bid_id"));
                    b.setPrice(jo1.getString("price"));
                    b.setDisc(jo1.getString("disc"));
                    b.setTerms(jo1.getString("terms"));


                    listbid.add(b);


                }



            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            BidAd ad = new BidAd(getActivity(),listbid);
            lv.setAdapter(ad);


        }
    }

    class acceptbid extends AsyncTask<Void,Void,Void> {



        @Override
        protected Void doInBackground(Void... params) {

            js = new JsonHelper();
            acbidresult = js.getdata(acbidurl);





            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getActivity(),acbidresult,Toast.LENGTH_LONG).show();
        }
    }



}
