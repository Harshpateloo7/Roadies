package com.example.lenovo.rodienew.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.model.userlistpojo;
import com.example.lenovo.rodienew.util.JsonHelper;
import com.example.lenovo.rodienew.util.ipaddress;

/**
 * Created by Admin on 3/15/2017.
 */

@SuppressLint("ValidFragment")
public class User_list_detail extends Fragment {

    TextView id,name,email,pass,ph,city,state,coun,add;
    Button btndel;
    userlistpojo u;
    JsonHelper js;
    String url;
    String result;
    int a;
    @SuppressLint("ValidFragment")
    public User_list_detail(userlistpojo u){

        this.u = u;

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.user_list_detail,container,false);

        id=(TextView)v.findViewById(R.id.txtuid);
        name=(TextView)v.findViewById(R.id.txtuname);
        email=(TextView)v.findViewById(R.id.txtuemail);
        pass=(TextView)v.findViewById(R.id.txtupass);
        ph=(TextView)v.findViewById(R.id.txtuph);
        city=(TextView)v.findViewById(R.id.txtucity);
        state=(TextView)v.findViewById(R.id.txtustate);
        coun=(TextView)v.findViewById(R.id.txtucoun);
        add=(TextView)v.findViewById(R.id.txtuaddr);
        btndel=(Button)v.findViewById(R.id.btndelete);

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    url = ipaddress.ip + "deleteuser.php?id=" + u.getId();
                    new deleteuser().execute();

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

    class deleteuser extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {

            js = new JsonHelper();
            result = js.getdata(url);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);

            UserList f = new UserList(getActivity());
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_main_admin,f);
            ft.commit();
        }
    }
}
