package com.example.lenovo.rodienew.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.activity.AllserviceDetail;
import com.example.lenovo.rodienew.adapter.MyserviceAdapter;
import com.example.lenovo.rodienew.model.MyservicePojo;
import com.example.lenovo.rodienew.util.JsonHelper;
import com.example.lenovo.rodienew.util.ipaddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lenovo on 29-03-2017.
 */

@SuppressLint("ValidFragment")
public class Allreq extends Fragment {

    ListView lst;
    String url,result;
    MyserviceAdapter u;
    ArrayList<MyservicePojo> list;
    JsonHelper js;
    Context context;
    EditText edsearch;


    @SuppressLint("ValidFragment")
    public Allreq(Context context)
    {
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.list,null);

        CardView cv = (CardView)v.findViewById(R.id.card);
        cv.setVisibility(View.GONE);
        edsearch = (EditText)v.findViewById(R.id.edtserch);
        lst=(ListView)v.findViewById(R.id.list1);

        SharedPreferences sp  = getActivity().getSharedPreferences("pref",Context.MODE_WORLD_WRITEABLE);
        String uid = sp.getString("id","");
        url= ipaddress.ip+"allreq.php";
        new getdata().execute();

        edsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                url = ipaddress.ip+"searchreq.php?name="+edsearch.getText().toString();
                new getdata().execute();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                MyservicePojo u = (MyservicePojo)list.get(position);

                AllserviceDetail d=new AllserviceDetail(u);
                FragmentTransaction ft=getFragmentManager().beginTransaction();

                ft.replace(R.id.content_service_provider,d);
                ft.commit();
            }
        });

        return v;
    }

    class getdata extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            js=new JsonHelper();
            result=js.getdata(url);
            list=new ArrayList<>();

            try {
                JSONObject jo=new JSONObject(result);
                JSONArray ja=jo.getJSONArray("req");

                for(int i=0;i<ja.length();i++)
                {
                    JSONObject jo1=ja.getJSONObject(i);
                    MyservicePojo u=new MyservicePojo();

                    u.setId(jo1.getString("id"));
                    u.setDisc(jo1.getString("Description"));
                    u.setTocity(jo1.getString("To_City"));
                    u.setTostate(jo1.getString("To_State"));
                    u.setTocountry(jo1.getString("To_Country"));
                    u.setToaddress(jo1.getString("To_Address"));
                    u.setFcity(jo1.getString("From_City"));
                    u.setFstate(jo1.getString("From_State"));
                    u.setFcountry(jo1.getString("From_Country"));
                    u.setFaddress(jo1.getString("From_Address"));
                    u.setDate(jo1.getString("date"));
                    u.setTime(jo1.getString("time"));

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

            u=new MyserviceAdapter(context,list);
            lst.setAdapter(u);
        }
    }
}



