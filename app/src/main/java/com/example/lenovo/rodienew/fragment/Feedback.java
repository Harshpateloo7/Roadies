package com.example.lenovo.rodienew.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
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
import com.example.lenovo.rodienew.util.JsonHelper;
import com.example.lenovo.rodienew.util.ipaddress;

import java.util.zip.Inflater;

/**
 * Created by Admin on 3/28/2017.
 */

@SuppressLint("ValidFragment")
public class Feedback extends Fragment {

    EditText edtfeed;
    Button btnsub,btncan;
    String url,result;
    String sfeed;
    JsonHelper js;
    Context context;

    @SuppressLint("ValidFragment")
    public Feedback(Context context){

        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.feedback,null);

        edtfeed=(EditText)v.findViewById(R.id.edtfeedback);
        btnsub=(Button)v.findViewById(R.id.btnsub);
        btncan=(Button)v.findViewById(R.id.btncan);

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sfeed=edtfeed.getText().toString();

                url = ipaddress.ip +"feedd.php?feed="+sfeed;

                new Feedback.insert().execute();
            }
        });
        return v;

    }
    public class insert extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {

            js=new JsonHelper();
            result=js.getdata(url);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context,"Message Sent Successfully...",Toast.LENGTH_LONG).show();

        }
    }
}
