package com.example.lenovo.rodienew.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.adapter.FeedAdapter;
import com.example.lenovo.rodienew.adapter.userlistadapter;
import com.example.lenovo.rodienew.model.Feedpojo;
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

public class FeddlistAdmin extends Fragment {

    JsonHelper js;
    String result,url;
    ArrayList<Feedpojo> list;
    ListView lv;
    EditText edsearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.list,container,false);

        edsearch = (EditText) view.findViewById(R.id.edtserch);
        edsearch.setVisibility(View.GONE);

        lv = (ListView)view.findViewById(R.id.list1);

        url = ipaddress.ip+"feed.php";

        new getdata().execute();


        return view;
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
                JSONArray ja=jo.getJSONArray("data");

                for(int i=0;i<ja.length();i++)
                {
                    JSONObject jo1=ja.getJSONObject(i);
                    Feedpojo u=new Feedpojo();

                    u.setId(jo1.getString("fid"));
                    u.setName(jo1.getString("fname"));

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

            FeedAdapter ad = new FeedAdapter(getActivity(),list);
            lv.setAdapter(ad);

        }
    }
}
