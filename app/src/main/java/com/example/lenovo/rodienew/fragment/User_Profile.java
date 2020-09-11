package com.example.lenovo.rodienew.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.activity.ureg;
import com.example.lenovo.rodienew.adapter.cityadapter;
import com.example.lenovo.rodienew.adapter.countryadapter;
import com.example.lenovo.rodienew.adapter.stateadapter;
import com.example.lenovo.rodienew.model.citypojo;
import com.example.lenovo.rodienew.model.countrypojo;
import com.example.lenovo.rodienew.model.statepojo;
import com.example.lenovo.rodienew.util.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import com.example.lenovo.rodienew.util.*;
import com.example.lenovo.rodienew.model.*;
import com.example.lenovo.rodienew.adapter.*;

import static android.content.Context.MODE_WORLD_READABLE;

/**
 * Created by ADMIN on 24-02-2017.
 */

@SuppressLint("ValidFragment")
public class User_Profile extends Fragment {

    EditText name,email,phone,address;
    Spinner city,state,country;
    ArrayList<citypojo> lstcity;
    ArrayList<countrypojo> lstcon;
    ArrayList<statepojo> lststate;
    String url, urlprofile,result, urlcity, urlcon, urlstate,sstate,scity,scon;
    String sname,semail,sphone,sadd,sid;
    String uname,uemail,uphone,uadd,ucity,ustate,ucon;;
    JsonHelper js;
    Context context;
    SharedPreferences sp;
    Button update;
    int ctid,stid,conid;

    @SuppressLint("ValidFragment")
    public  User_Profile(Context context)
    {
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.user_profile,null);
        name=(EditText)v.findViewById(R.id.edtname);
        email=(EditText)v.findViewById(R.id.edtemail);
        phone=(EditText)v.findViewById(R.id.edtphone);
        address=(EditText)v.findViewById(R.id.edtaddress);
        city=(Spinner)v.findViewById(R.id.spncity);
        state=(Spinner)v.findViewById(R.id.spnstate);
        country=(Spinner)v.findViewById(R.id.spncountry);
        update=(Button)v.findViewById(R.id.btnupdate);

        sp=getActivity().getSharedPreferences("pref",MODE_WORLD_READABLE);
        sname=sp.getString("name","");
        semail=sp.getString("email","");
        sphone=sp.getString("phone","");
        sadd=sp.getString("add","");
        scity=sp.getString("city","");
        sstate=sp.getString("state","");
        scon=sp.getString("country","");
        sid=sp.getString("id","");
        email.setText(semail);
        phone.setText(sphone);
        name.setText(sname);
        address.setText(sadd);

        urlprofile=ipaddress.ip + "userprofile.php?id="+sid;
        urlcon = ipaddress.ip + "country.php";
        urlcity = ipaddress.ip + "city.php";
        urlstate = ipaddress.ip + "state.php";

        new selectcity().execute();
        new selectstate().execute();
        new selectcon().execute();
        new userprofile().execute();



        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ucity=lstcity.get(position).getName().toString();
                ucity=ucity.replace(" ","%20");
               //       Toast.makeText(context, ucity, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ustate=lststate.get(position).getName().toString();
                ustate=ustate.replace(" ","%20");
              //  Toast.makeText(context, ustate, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ucon=lstcon.get(position).getName().toString();
                ucon=ucon.replace(" ","%20");
             //   Toast.makeText(context, ucon, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname=name.getText().toString();
                uemail=email.getText().toString();
                uadd=address.getText().toString();
                uphone=phone.getText().toString();

                if ((!uemail.contains("@")) || (!(uemail.contains(".")))){

                    email.setError("email is invalid");

                }else {

                    url = ipaddress.ip + "updateuser.php?name=" + uname + "&email=" + uemail + "&phone=" + uphone + "&city=" + ucity + "&state=" + ustate + "&country=" + ucon + "&add=" + uadd + "&id=" + sid;
                    new update().execute();
                }
            }
        });

        return v;
    }
    class selectcity extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            js = new JsonHelper();
            result = js.getdata(urlcity);
            lstcity=new ArrayList<>();

            try {
                JSONObject jo = new JSONObject(result);
                JSONArray ja = jo.getJSONArray("data");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo1 = ja.getJSONObject(i);
                    citypojo c = new citypojo();
                    c.setName(jo1.getString("cname"));
                    lstcity.add(c);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            cityadapter c = new cityadapter(context, lstcity);
            city.setAdapter(c);

        }
    }

    class selectstate extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            js = new JsonHelper();
            result = js.getdata(urlstate);
            lststate=new ArrayList<>();

            try {
                JSONObject jo = new JSONObject(result);
                JSONArray ja = jo.getJSONArray("data");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo1 = ja.getJSONObject(i);
                    statepojo c = new statepojo();
                    c.setName(jo1.getString("sname"));
                    lststate.add(c);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            stateadapter c = new stateadapter(context, lststate);
            state.setAdapter(c);

        }
    }

    class selectcon extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            js = new JsonHelper();
            result = js.getdata(urlcon);
            lstcon=new ArrayList<>();

            try {
                JSONObject jo = new JSONObject(result);
                JSONArray ja = jo.getJSONArray("data");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo1 = ja.getJSONObject(i);
                    countrypojo c = new countrypojo();
                    c.setName(jo1.getString("coname"));
                    lstcon.add(c);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            countryadapter c = new countryadapter(context, lstcon);
            country.setAdapter(c);

        }
    }

    class update extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            js = new JsonHelper();
            result = js.getdata(url);

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

        }
    }

    class userprofile extends AsyncTask<Void, Void, Void> {
        loginpojo l = new loginpojo();
        @Override
        protected Void doInBackground(Void... params) {
            js = new JsonHelper();
            result = js.getdata(urlprofile);

            try {
                JSONObject jo = new JSONObject(result);
                JSONArray ja = jo.getJSONArray("data");
                    JSONObject jo1 = ja.getJSONObject(0);

                    l.setName(jo1.getString("name"));
                    l.setEmail(jo1.getString("email"));
                    l.setCity(jo1.getString("city"));
                    l.setState(jo1.getString("state"));
                    l.setPhone(jo1.getString("phone"));
                    l.setCountry(jo1.getString("country"));
                    l.setAdd(jo1.getString("addr"));
                    ctid=jo1.getInt("cid");
                    stid=jo1.getInt("sid");
                    conid=jo1.getInt("coid");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            name.setText(l.getName());
            email.setText(l.getEmail());
            phone.setText(l.getPhone());
            address.setText(l.getAdd());
            city.setSelection(ctid);
            state.setSelection(stid);
            country.setSelection(conid);

        }
    }
}
