package com.example.lenovo.rodienew.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.activity.MainActivity;
import com.example.lenovo.rodienew.activity.MainUser;
import com.example.lenovo.rodienew.activity.ureg;
import com.example.lenovo.rodienew.adapter.cityadapter;
import com.example.lenovo.rodienew.adapter.countryadapter;
import com.example.lenovo.rodienew.adapter.stateadapter;
import com.example.lenovo.rodienew.model.citypojo;
import com.example.lenovo.rodienew.model.countrypojo;
import com.example.lenovo.rodienew.model.statepojo;
import com.example.lenovo.rodienew.util.JsonHelper;
import com.example.lenovo.rodienew.util.ipaddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Admin on 3/9/2017.
 */

@SuppressLint("ValidFragment")
public class User_addreq extends Fragment{

    EditText edttitle, edtdisc , edtadd, edtadd1 , edtdate,edttime ;
    Spinner spncity , spnstate , spncon , spncity1, spnstate1, spncon1;
    Button btnsub, btncan, btnimg;

    String url, result, urlcity, urlcon, urlstate;
    String stitle, sdisc, sadd , sadd1, scity, scity1, sstate,sstate1, scon, scon1, sdate, stime,sid;
    JsonHelper js;
    Context context;
    SharedPreferences sp1;

    ArrayList<citypojo> lstcity;
    ArrayList<countrypojo> lstcon;
    ArrayList<statepojo> lststate;

    private int mYear, mMonth, mDay, mHour, mMinute;

    @SuppressLint("ValidFragment")
    public User_addreq(Context context){

        this.context=context;


    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.user_addreq,null);

     //   edttitle=(EditText)v.findViewById(R.id.edttitle);
        edtdisc=(EditText)v.findViewById(R.id.edtdis);
        edtadd=(EditText)v.findViewById(R.id.edtaddress);
        edtadd1=(EditText)v.findViewById(R.id.edtaddress1);
        edtdate=(EditText)v.findViewById(R.id.edtdate);
        edttime=(EditText)v.findViewById(R.id.edttime);
        btnsub=(Button) v.findViewById(R.id.btnsub);
        btncan=(Button) v.findViewById(R.id.btncan);
        btnimg=(Button)v.findViewById(R.id.btnimg);
        spncity=(Spinner) v.findViewById(R.id.spncity);
        spncity1=(Spinner) v.findViewById(R.id.spncity1);
        spnstate=(Spinner) v.findViewById(R.id.spnstate);
        spnstate1=(Spinner) v.findViewById(R.id.spnstate1);
        spncon=(Spinner) v.findViewById(R.id.spncountry);
        spncon1=(Spinner) v.findViewById(R.id.spncountry1);

         sp1 = context.getSharedPreferences("pref",context.MODE_WORLD_READABLE);

        sid=sp1.getString("id","");

        urlcity= ipaddress.ip +"city.php";
        urlstate= ipaddress.ip +"state.php";
        urlcon= ipaddress.ip +"country.php";
        new User_addreq.selectcity().execute();
        new User_addreq.selectstate().execute();
        new User_addreq.selectcon().execute();

        spncity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               citypojo c=(citypojo)lstcity.get(position);
                scity=c.getId();
             //   Toast.makeText(context, scity, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spncity1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                citypojo c=(citypojo)lstcity.get(position);
                scity1=c.getId();
                scity1=scity1.replace(" ","%20");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                statepojo c=(statepojo)lststate.get(position);
                sstate=c.getId();
              //  Toast.makeText(context, sstate, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnstate1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                statepojo c=(statepojo)lststate.get(position);
                sstate1=c.getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spncon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                countrypojo c=(countrypojo)lstcon.get(position);
                scon=c.getId();
          //      Toast.makeText(context, scon, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spncon1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                countrypojo c=(countrypojo)lstcon.get(position);
                scon1=c.getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        edtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                final Calendar c = Calendar.getInstance();
                                mYear = c.get(Calendar.YEAR);
                                mMonth = c.get(Calendar.MONTH);
                                mDay = c.get(Calendar.DAY_OF_MONTH);
                                sdate=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                edtdate.setText(sdate);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        edttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                stime=hourOfDay + ":" + minute;
                                edttime.setText(stime);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                stitle=edttitle.getText().toString();
                sdisc=edtdisc.getText().toString();
                sadd=edtadd.getText().toString();
                sadd1=edtadd1.getText().toString();
                sdate=edtdate.getText().toString();
                stime=edttime.getText().toString();
                sdisc=sdisc.replace(" ","%20");
                sadd=sadd.replace(" ","%20");
                sadd1=sadd1.replace(" ","%20");


                url = ipaddress.ip + "addreq.php?uid="+sid+"&desc="+sdisc+"&tcity="+scity+"&tstate="+sstate+"&tcon="+scon+"&tadd="+sadd+"&fcity="+scity1+"&fstate="+sstate1+"&fcon="+scon1+"&fadd="+sadd1+"&date="+sdate+"&time="+stime;

                new insert().execute();
            }

        });

        btncan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User_addreq h=new User_addreq(getActivity());
                FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main_user,h);
            }
        });
        return v;


    }
    class insert extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... params) {


            js = new JsonHelper();
            result = js.getdata(url);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context, "ADD Request Successsfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, MainUser.class);
            startActivity(i);


        }
    }

    class selectcity extends AsyncTask<Void,Void,Void>{


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
                    c.setId(jo1.getString("cid"));
                    lstcity.add(c);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void avoid){
            super.onPostExecute(avoid);
            cityadapter c=new cityadapter(context,lstcity);
            spncity.setAdapter(c);
            spncity1.setAdapter(c);
        }
    }

    class selectstate extends AsyncTask<Void,Void,Void>{


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
                    c.setId(jo1.getString("sid"));
                    lststate.add(c);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            stateadapter c = new stateadapter(context, lststate);
            spnstate.setAdapter(c);
            spnstate1.setAdapter(c);
        }
    }
    class selectcon extends AsyncTask<Void,Void,Void>{


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
                    c.setId(jo1.getString("coid"));
                    lstcon.add(c);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            countryadapter c = new countryadapter(context, lstcon);
            spncon.setAdapter(c);
            spncon1.setAdapter(c);

        }
    }
}
