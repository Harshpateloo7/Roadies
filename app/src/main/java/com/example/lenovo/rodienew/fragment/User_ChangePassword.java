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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.activity.ureg;
import com.example.lenovo.rodienew.adapter.countryadapter;
import com.example.lenovo.rodienew.model.countrypojo;
import com.example.lenovo.rodienew.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lenovo on 01-03-2017.
 */

@SuppressLint("ValidFragment")
public class User_ChangePassword extends Fragment {


    EditText currentpass,newpass,repass;
    Button update;
    String result,url,scpass,snpass,srepass,sid;
    JsonHelper js;
    SharedPreferences sp;
    Context context;

    @SuppressLint("ValidFragment")
    public  User_ChangePassword(Context context)
    {
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.changepassword,null);
        currentpass=(EditText)v.findViewById(R.id.edtcurentpass);
        newpass=(EditText)v.findViewById(R.id.edtnewpass);
        repass=(EditText)v.findViewById(R.id.edtrenewpass);
        update=(Button)v.findViewById(R.id.btnupdatepass);


        sp = getActivity().getSharedPreferences("pref", Context.MODE_WORLD_READABLE);

        sid=sp.getString("id","");
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scpass=currentpass.getText().toString();
                snpass=newpass.getText().toString();
                srepass=repass.getText().toString();
                if(!snpass.equals(srepass))
                {
                    Toast.makeText(context, "Password does not match", Toast.LENGTH_SHORT).show();
                }
                else if(scpass.equals("")) {
                    currentpass.setError("Please enter your current password");
                    currentpass.requestFocus();
                }
                else if(snpass.equals("")) {
                    newpass.setError("Please enter your current password");
                    newpass.requestFocus();
                }
                else if(srepass.equals("")) {
                    repass.setError("Please enter your current password");
                    repass.requestFocus();
                }
                else
                {
                    url = ipaddress.ip + "upassupdate.php?opass=" + scpass + "&npass=" + snpass + "&id=" + sid;
                    new selectcon().execute();
                }
            }
        });

        return v;
    }

    class selectcon extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            js = new JsonHelper();
            result = js.getdata(url);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context, "Password Updated", Toast.LENGTH_SHORT).show();

        }
    }
}
