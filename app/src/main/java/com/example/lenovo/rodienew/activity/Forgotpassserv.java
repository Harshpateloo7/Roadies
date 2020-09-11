package com.example.lenovo.rodienew.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.util.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lenovo on 31-03-2017.
 */

public class Forgotpassserv extends AppCompatActivity {

    EditText edemail;
    String pass;
    Button btn;
    String url,resut;
    JsonHelper js;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.forgotpass);

        edemail = (EditText)findViewById(R.id.edforgotemail);
        btn = (Button)findViewById(R.id.btnforgot);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url = "http://10.0.2.2/rodi/getpassserv.php?email="+edemail.getText().toString();

                new getpass().execute();

            }
        });
    }

    class getpass extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            js = new JsonHelper();
            resut  = js.getdata(url);
            try {
                JSONObject jo = new JSONObject(resut);
                JSONArray ja = jo.getJSONArray("pass");
                JSONObject jo1 = ja.getJSONObject(0);
                pass = jo1.getString("pass");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (resut.length()<3){

                Toast.makeText(getApplicationContext(), "user not found", Toast.LENGTH_SHORT).show();
            }
            else{


                sendemail s = new sendemail(pass,edemail.getText().toString());
                s.execute();

            }
        }

    }

    class sendemail extends AsyncTask<Void,Void,Void>{

        String pass,email;
        sendemail(String pass,String email){

            this.pass = pass;
            this.email = email;

        }


        @Override
        protected Void doInBackground(Void... params) {

            url = "http://engineeringclass.net84.net/pooja/sendmail.php?email="+email+"&pass="+pass;
            js = new JsonHelper();
            resut = js.getdata(url);



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(),resut,Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
    }
}
