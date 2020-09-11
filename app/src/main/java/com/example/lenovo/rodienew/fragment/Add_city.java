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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lenovo.rodienew.R;
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

/**
 * Created by Admin on 3/20/2017.
 */

@SuppressLint("ValidFragment")
public class Add_city extends Fragment {

    EditText edtcity;
    Spinner spnstate;
    Button btnadd,btndel,btncan;

    String url, result,  urlstate;
    JsonHelper js;
    String scity,sstate,sid;

    Context context;

    ArrayList<statepojo> lststate;
    @SuppressLint("ValidFragment")
    public Add_city(Context context){

        this.context=context;


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_city, null);

        edtcity=(EditText)v.findViewById(R.id.edtcity);
        spnstate=(Spinner)v.findViewById(R.id.spnstate);
        btnadd=(Button)v.findViewById(R.id.btnadd);
        btndel=(Button)v.findViewById(R.id.btndelete);

        btncan=(Button)v.findViewById(R.id.btncan);

        urlstate=ipaddress.ip +"state.php";

        new Add_city.selectstate().execute();
        btndel.setVisibility(View.GONE);
        btncan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Add_city f = new Add_city(getActivity());

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main_admin,f);
                ft.commit();
            }
        });


        spnstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                statepojo c=(statepojo)lststate.get(position);
                sstate=lststate.get(position).getId().toString();
               // sstate=sstate.replace(" ","%20");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scity=edtcity.getText().toString();

                url= ipaddress.ip + "addcity.php?city="+scity+"&s_id="+sstate+"";

                new insert().execute();

            }
        });

        return v;
    }
    class insert extends AsyncTask< Void,Void,Void>{


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
        }
    }
}
