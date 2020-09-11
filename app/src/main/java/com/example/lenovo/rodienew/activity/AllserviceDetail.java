package com.example.lenovo.rodienew.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.fragment.Allreq;
import com.example.lenovo.rodienew.fragment.MyaddedService;
import com.example.lenovo.rodienew.fragment.Myservicedetail;
import com.example.lenovo.rodienew.fragment.SerPro_list;
import com.example.lenovo.rodienew.model.MyservicePojo;
import com.example.lenovo.rodienew.util.JsonHelper;
import com.example.lenovo.rodienew.util.ipaddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lenovo on 29-03-2017.
 */

@SuppressLint("ValidFragment")
public class AllserviceDetail  extends Fragment {
    TextView tvname,tvdisc,tvtoadd,tvtocity,tvtostate,tvtocountry,tvfromadd,tvfcity,tvstate,tvfcountry,tvtime,tvdate;
    Button btn;
    String stocity,stostate,stocountry,sfcity,sfstate,sfcountry;
    MyservicePojo u;

    EditText edbid,eddisc,edterm;

    String addbidurl;
    JsonHelper js;
    String url;
    String urlcity,urlstate,urlcountry,urlfcity,urlfstate,urlfcountry;
    String result,resultbid;

    String retocity,retostate,retoco,refcity,refstate,refco;
    Button btnbid,btncancel;
    @SuppressLint("ValidFragment")
    public AllserviceDetail(MyservicePojo u){

        this.u = u;

        urlcity = ipaddress.ip+"getcity.php?id="+u.getTocity();
        Log.d("url is ",urlcity);
        urlstate = ipaddress.ip+"getstate.php?id="+u.getTostate();
        Log.d("url is ",urlstate);
        urlcountry = ipaddress.ip+"getcountry.php?id="+u.getTocountry();
        Log.d("url is ",urlcountry);
        urlfcity = ipaddress.ip+"getcity.php?id="+u.getFcity();
        Log.d("url is ",urlfcity);
        urlfstate = ipaddress.ip+"getstate.php?id="+u.getFstate();
        Log.d("url is ",urlfstate);
        urlfcountry = ipaddress.ip+"getcountry.php?id="+u.getFcountry();
        Log.d("url is ",urlfcountry);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.briefreq,container,false);

        edbid = (EditText)v.findViewById(R.id.edbid);
        eddisc = (EditText)v.findViewById(R.id.eddisc);
        edterm = (EditText)v.findViewById(R.id.edterms);

        tvname=(TextView)v.findViewById(R.id.txtname);

        btnbid = (Button)v.findViewById(R.id.btnbid);
        btncancel = (Button)v.findViewById(R.id.btncan);

        SharedPreferences sp = getActivity().getSharedPreferences("pref", Context.MODE_WORLD_READABLE);
        final String tid = sp.getString("id","");



        btnbid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addbidurl = ipaddress.ip+"bid.php?trans_id="+tid+"&price="+edbid.getText().toString()+"&disc="+eddisc.getText().toString()+"&terms="+edterm.getText().toString()+"&req="+u.getId();

                new addbid().execute();

            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyaddedService f = new MyaddedService(getActivity());
                FragmentTransaction ft=getFragmentManager().beginTransaction();

                ft.replace(R.id.content_main_user,f);
                ft.commit();

            }
        });


        //btn = (Button)v.findViewById(R.id.btndelete);


        tvdisc=(TextView)v.findViewById(R.id.txtdisc);
        tvtoadd=(TextView)v.findViewById(R.id.txtadd);
        tvtocity=(TextView)v.findViewById(R.id.txtcity);
        tvtostate=(TextView)v.findViewById(R.id.txtstate);
        tvtocountry=(TextView)v.findViewById(R.id.txtcoun);
        tvfromadd=(TextView)v.findViewById(R.id.txtadd1);
        tvfcity=(TextView)v.findViewById(R.id.txtcity1);
        tvstate=(TextView)v.findViewById(R.id.txtstate1);
        tvfcountry=(TextView)v.findViewById(R.id.txtcoun1);
        tvdate=(TextView)v.findViewById(R.id.txtdate);
        tvtime=(TextView)v.findViewById(R.id.txttime);

       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url = ipaddress.ip+"deleteservicepro.php?id="+u.getId();
                new Serpro_lisi_detail.deleteservice().execute();

            }
        });

*/

        new gettocity().execute();






        tvname.setVisibility(View.GONE);
        tvdisc.setText(u.getDisc());
        tvtoadd.setText(u.getToaddress());



        tvfromadd.setText(u.getFaddress());



        tvdate.setText(u.getDate());
        tvtime.setText(u.getTime());
        return v;
    }

    class deleteservice extends AsyncTask<Void,Void,Void> {



        @Override
        protected Void doInBackground(Void... params) {

            js = new JsonHelper();
            result = js.getdata(url);


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();

            SerPro_list f = new SerPro_list(getActivity());
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_main_admin,f);
            ft.commit();
        }
    }
    class gettocity extends AsyncTask<Void,Void,Void> {



        @Override
        protected Void doInBackground(Void... params) {

            js = new JsonHelper();
            retocity = js.getdata(urlcity);

            try {
                JSONObject jo = new JSONObject(retocity);
                JSONArray ja = jo.getJSONArray("city");
                JSONObject jo1 = ja.getJSONObject(0);
                stocity = jo1.getString("cname");

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
            new gettostate().execute();
            tvtocity.setText(stocity);
        }
    }

    class getfromcity extends AsyncTask<Void,Void,Void> {



        @Override
        protected Void doInBackground(Void... params) {

            js = new JsonHelper();
            refcity = js.getdata(urlfcity);

            try {
                JSONObject jo = new JSONObject(refcity);
                JSONArray ja = jo.getJSONArray("city");
                JSONObject jo1 = ja.getJSONObject(0);
                sfcity = jo1.getString("cname");

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            tvfcity.setText(sfcity);
            new getfstate().execute();
            Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
        }
    }

    class gettostate extends AsyncTask<Void,Void,Void> {



        @Override
        protected Void doInBackground(Void... params) {

            js = new JsonHelper();
            retostate = js.getdata(urlstate);
            Log.d("data is ",retostate);
            try {
                JSONObject jo = new JSONObject(retostate);
                JSONArray ja = jo.getJSONArray("state");
                JSONObject jo1 = ja.getJSONObject(0);
                stostate = jo1.getString("sname");
                Log.d("state is ",stostate);

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
            tvtostate.setText(stostate);
            new gettocountry().execute();
        }
    }

    class getfstate extends AsyncTask<Void,Void,Void> {



        @Override
        protected Void doInBackground(Void... params) {

            js = new JsonHelper();
            refstate = js.getdata(urlfstate);

            try {
                JSONObject jo = new JSONObject(refstate);
                JSONArray ja = jo.getJSONArray("state");
                JSONObject jo1 = ja.getJSONObject(0);
                sfstate = jo1.getString("sname");

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new getfcountry().execute();
            tvstate.setText(sfstate);
            Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
        }
    }

    class gettocountry extends AsyncTask<Void,Void,Void> {



        @Override
        protected Void doInBackground(Void... params) {

            js = new JsonHelper();
            retoco = js.getdata(urlcountry);

            try {
                JSONObject jo = new JSONObject(retoco);
                JSONArray ja = jo.getJSONArray("country");
                JSONObject jo1 = ja.getJSONObject(0);
                stocountry = jo1.getString("coname");

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new getfromcity().execute();
            Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();

            tvtocountry.setText(stocountry);
        }
    }

    class getfcountry extends AsyncTask<Void,Void,Void> {



        @Override
        protected Void doInBackground(Void... params) {

            js = new JsonHelper();
            refco = js.getdata(urlfcountry);

            try {
                JSONObject jo = new JSONObject(refco);
                JSONArray ja = jo.getJSONArray("country");
                JSONObject jo1 = ja.getJSONObject(0);
                sfcountry = jo1.getString("coname");

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tvfcountry.setText(sfcountry);
            Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
        }


    }

    class addbid extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            js = new JsonHelper();

            resultbid = js.getdata(addbidurl);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getActivity(),resultbid,Toast.LENGTH_LONG).show();

            Fragment f1=new Fragment();
            Allreq h=new Allreq(getActivity());
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.addToBackStack(f1.getClass().getName());
            ft.replace(R.id.content_service_provider,h);
            ft.commit();
        }
    }



}
