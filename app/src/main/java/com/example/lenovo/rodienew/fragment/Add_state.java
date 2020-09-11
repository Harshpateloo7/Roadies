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
import com.example.lenovo.rodienew.activity.ureg;
import com.example.lenovo.rodienew.adapter.countryadapter;
import com.example.lenovo.rodienew.model.statepojo;
import com.example.lenovo.rodienew.util.JsonHelper;
import com.example.lenovo.rodienew.util.ipaddress;
import com.example.lenovo.rodienew.model.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Admin on 3/20/2017.
 */

@SuppressLint("ValidFragment")
public class Add_state extends Fragment {

    EditText edtstate;
    Spinner spncoun;
    Button btnadd,btndel,btncan;

    String url, result, urlcoun;
    JsonHelper js;
    String sstate,scoun,sconid;

    Context context;
    ArrayList<countrypojo> lstcoun;


    @SuppressLint("ValidFragment")
    public Add_state(Context context){

        this.context=context;


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_state, null);

        edtstate=(EditText)v.findViewById(R.id.edtstate);
        btnadd=(Button)v.findViewById(R.id.btnadd);
        btndel=(Button)v.findViewById(R.id.btndelete);
        btncan=(Button)v.findViewById(R.id.btncan);
        spncoun=(Spinner)v.findViewById(R.id.spncoun);

        urlcoun=ipaddress.ip +"country.php";

        new Add_state.selectcon().execute();

        btndel.setVisibility(View.GONE);
        btncan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Add_state f = new Add_state(getActivity());

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main_admin,f);
                ft.commit();
            }
        });


        spncoun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                countrypojo c=(countrypojo) lstcoun.get(position);


                scoun=lstcoun.get(position).getId().toString();
                // scoun=scoun.replace(" ","%20");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sstate=edtstate.getText().toString();

                url= ipaddress.ip + "addstate.php?state="+sstate+"&con_id="+scoun+"";

                new Add_state.insert().execute();

            }
        });

        return v;
    }
    class insert extends AsyncTask< Void,Void,Void> {


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
    class selectcon extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            js = new JsonHelper();
            result = js.getdata(urlcoun);
            lstcoun=new ArrayList<>();

            try {
                JSONObject jo = new JSONObject(result);
                JSONArray ja = jo.getJSONArray("data");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo1 = ja.getJSONObject(i);
                    countrypojo c = new countrypojo();
                    c.setName(jo1.getString("coname"));
                    c.setId(jo1.getString("coid"));
                    lstcoun.add(c);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            countryadapter c = new countryadapter(context, lstcoun);
            spncoun.setAdapter(c);

        }
    }



}
