package com.example.lenovo.rodienew.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.adapter.customspinner;
import com.example.lenovo.rodienew.util.JsonHelper;
import com.example.lenovo.rodienew.util.ipaddress;
import com.example.lenovo.rodienew.model.*;
import com.example.lenovo.rodienew.adapter.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lenovo on 16-02-2017.
 */

public class ureg extends AppCompatActivity {
    EditText edname, edemail, edpass, edno, edadd;
    Button btnsubmit;

    String sedname, sedemail, sedpass, sedno, sedadd,scity,scon,sstate,scat;
    String snamecheck, saddrcheck;
    String url, result, urlcity, urlcon, urlstate;
    Spinner cat, city, state, con;
    JsonHelper js;
    String category[] = {"User", "Service Provider"};

    int logo[] = {R.drawable.userlogo, R.drawable.tralogo};
    ArrayList<String> listcat;
    ArrayList<citypojo> lstcity;
    ArrayList<countrypojo> lstcon;
    ArrayList<statepojo> lststate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ureq);

        edname = (EditText) findViewById(R.id.ename);
        edemail = (EditText) findViewById(R.id.eemail);
        edpass = (EditText) findViewById(R.id.epass);
        edno = (EditText) findViewById(R.id.eno);
        edadd = (EditText) findViewById(R.id.eadd);
        btnsubmit = (Button) findViewById(R.id.btnreg);
        cat = (Spinner) findViewById(R.id.spncat);
        city = (Spinner) findViewById(R.id.spncity);
        state = (Spinner) findViewById(R.id.spnstate);
        con = (Spinner) findViewById(R.id.spncon);
        urlcon = ipaddress.ip + "country.php";
        urlcity = ipaddress.ip + "city.php";
        urlstate = ipaddress.ip + "state.php";

        listcat=new ArrayList<>();
        listcat.add("User");
        listcat.add("Service Provider");

        customspinner c = new customspinner(ureg.this, logo, listcat);
        cat.setAdapter(c);
        new selectcity().execute();
        new selectstate().execute();
        new selectcon().execute();

        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scat=listcat.get(position);
           //     Toast.makeText(ureg.this,scat, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                scity=lstcity.get(position).getId().toString();
                scity=scity.replace(" ","%20");
          //      Toast.makeText(ureg.this, scity, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sstate=lststate.get(position).getName().toString();
                sstate=sstate.replace(" ","%20");
           //     Toast.makeText(ureg.this, sstate, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        con.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scon=lstcon.get(position).getName().toString();
                scon=scon.replace(" ","%20");
             //   Toast.makeText(ureg.this, scon, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sedname = edname.getText().toString();
                sedemail = edemail.getText().toString();
                sedpass = edpass.getText().toString();
                sedno = edno.getText().toString();
                sedadd = edadd.getText().toString();
                snamecheck = sedname.replace(" ", "%20");
                saddrcheck = sedadd.replace(" ", "%20");
                if (sedname.equals("")) {
                    edname.setError("Insert Your Name");
                    edname.requestFocus();
                } else if (sedemail.equals("")) {
                    edemail.setError("Insert your Email");
                    edemail.requestFocus();
                } else if (sedpass.equals("")) {
                    edpass.setError("Insert Your Password");
                    edpass.requestFocus();
                } else if (sedno.equals("")) {
                    edno.setError("Insert Your Phoneno");
                    edno.requestFocus();
                } else if (sedadd.equals("")) {
                    edadd.setError("Insert Your Address");
                    edadd.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(sedemail).matches()) {
                    edemail.setError("please enter valid email");
                    edemail.requestFocus();
                } else if (!Patterns.PHONE.matcher(sedno).matches()) {
                    edno.setError("enter valid number");
                    edno.requestFocus();
                } else if (sedno.length() < 10 || sedno.length() > 10) {
                    edno.setError("enter valid number");
                    edno.requestFocus();
                } else {
                    if(scat=="User") {
                        url = ipaddress.ip + "usreg.php?name=" + snamecheck + "&email=" + sedemail + "&pass=" + sedpass + "&phone=" + sedno + "&city=" + scity + "&state=" + sstate + "&country=" + scon + "&add=" + saddrcheck;
                    }
                    else {
                        url = ipaddress.ip + "transreg.php?name=" + snamecheck + "&email=" + sedemail + "&pass=" + sedpass + "&phone=" + sedno + "&city=" + scity + "&state=" + sstate + "&country=" + scon + "&add=" + saddrcheck;
                    }
                        new inserdata().execute();

                }
            }
        });
    }

    class inserdata extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            js = new JsonHelper();
            result = js.getdata(url);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(ureg.this, result, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ureg.this, MainActivity.class);
            startActivity(i);
        }
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
                        c.setId(jo1.getString("cid"));
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
                cityadapter c = new cityadapter(ureg.this, lstcity);
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
                stateadapter c = new stateadapter(ureg.this, lststate);
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
                        c.setId(jo1.getString("coid"));
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
                countryadapter c = new countryadapter(ureg.this, lstcon);
                con.setAdapter(c);

            }
        }
    }
