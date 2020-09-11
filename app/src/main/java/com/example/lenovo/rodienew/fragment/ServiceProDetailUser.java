package com.example.lenovo.rodienew.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.model.userlistpojo;
import com.example.lenovo.rodienew.util.JsonHelper;
import com.example.lenovo.rodienew.util.ipaddress;

/**
 * Created by ADMIN on 24-03-2017.
 */

@SuppressLint("ValidFragment")
public class ServiceProDetailUser extends Fragment {
    TextView id,name,email,pass,ph,city,state,coun,add;
    Button btn;
    userlistpojo u;

    JsonHelper js;
    String url;
    String uid;
    String result;
    @SuppressLint("ValidFragment")
    public ServiceProDetailUser(userlistpojo u){

        this.u = u;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.user_list_detail,null);

        SharedPreferences sp =getActivity().getSharedPreferences("pref", Context.MODE_WORLD_WRITEABLE);

        uid = sp.getString("id","");

        id=(TextView)v.findViewById(R.id.txtuid);
        btn = (Button)v.findViewById(R.id.btndelete);
        name=(TextView)v.findViewById(R.id.txtuname);
        email=(TextView)v.findViewById(R.id.txtuemail);
        pass=(TextView)v.findViewById(R.id.txtupass);
        ph=(TextView)v.findViewById(R.id.txtuph);
        city=(TextView)v.findViewById(R.id.txtucity);
        state=(TextView)v.findViewById(R.id.txtustate);
        coun=(TextView)v.findViewById(R.id.txtucoun);
        add=(TextView)v.findViewById(R.id.txtuaddr);

        btn.setText("Add To Favourite");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url = ipaddress.ip+"insertmyfav.php?uid="+uid+"&spid="+u.getId();
                new deleteservice().execute();

            }
        });

        id.setText(u.getId());
        name.setText(u.getName());
        email.setText(u.getEmail());
        pass.setText(u.getPass());
        ph.setText(u.getPhone());
        city.setText(u.getCity());
        state.setText(u.getState());
        coun.setText(u.getCountry());
        add.setText(u.getAddr());
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

            ServiceProlistUser f = new ServiceProlistUser(getActivity());
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_main_user,f);
            ft.commit();
        }
    }
}
