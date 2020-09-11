package com.example.lenovo.rodienew.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.adapter.BidAd;
import com.example.lenovo.rodienew.adapter.MyfavList;
import com.example.lenovo.rodienew.model.Bidpojo;
import com.example.lenovo.rodienew.model.MyservicePojo;
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
public class Myservicedetail  extends Fragment {
    TextView tvname,tvdisc,tvtoadd,tvtocity,tvtostate,tvtocountry,tvfromadd,tvfcity,tvstate,tvfcountry,tvtime,tvdate;
    Button btn;
    String stocity,stostate,stocountry,sfcity,sfstate,sfcountry;
    MyservicePojo u;
    String bidurl,bidresult;

    String acbidurl,acbidresult;
    ArrayList<Bidpojo> listbid;


    JsonHelper js;
    String url;
    String urlcity,urlstate,urlcountry,urlfcity,urlfstate,urlfcountry;
    String result;
    ListView lv;

    String retocity,retostate,retoco,refcity,refstate,refco;
    Button btnbid,btncancel;
    Button btngetbid;
    @SuppressLint("ValidFragment")
    public Myservicedetail(MyservicePojo u){

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

        bidurl = ipaddress.ip+"allbid.php?id="+u.getId();
        Log.d("url is ",bidurl);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.add_req_detail,null);

        tvname=(TextView)v.findViewById(R.id.txtname);
        lv = (ListView)v.findViewById(R.id.lv);
        btngetbid = (Button) v.findViewById(R.id.getbid);

        btnbid = (Button)v.findViewById(R.id.btnbid);
        btncancel = (Button)v.findViewById(R.id.btncan);

        btnbid.setVisibility(View.GONE);

        new getbids().execute();

        btngetbid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bidlist f = new Bidlist(u.getId());
                FragmentTransaction ft=getFragmentManager().beginTransaction();

                ft.replace(R.id.content_main_user,f);
                ft.commit();
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

        lv.setVisibility(View.GONE);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bidpojo b = (Bidpojo)listbid.get(position);

                acbidurl = ipaddress.ip+"acceptbd.php?id="+b.getId();
                new acceptbid().execute();

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
