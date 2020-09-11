package com.example.lenovo.rodienew.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.model.*;
import com.example.lenovo.rodienew.util.CheckInternet;
import com.example.lenovo.rodienew.util.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import  com.example.lenovo.rodienew.adapter.customspinner;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.lenovo.rodienew.util.ipaddress;



public class MainActivity extends AppCompatActivity {

    EditText edname,edpass;
    Button btnlog;
    TextView newuser, forgpass;
    loginpojo l;
    String sname;
    String spass;
    String url1,url;
    String url2;
    String url3;
    int selectedcat;
    JsonHelper js;
    String result;
    Employee e;
    HashMap<String,String> param;
    LinearLayout lin;
    Spinner category;
    TextView txtforgotpass;

    int logo[]={R.drawable.admin,R.drawable.userlogo,R.drawable.tralogo};
    ArrayList<String> listcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* lin = (LinearLayout)findViewById(R.id.lin);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(lin, "rotationY", 0, 360);
        rotation.setDuration(3000);
        rotation.start();*/



        edname = (EditText)findViewById(R.id.eduname);
        edpass = (EditText)findViewById(R.id.edupass);
        category=(Spinner)findViewById(R.id.spncategory);
        newuser=(TextView)findViewById(R.id.txtregister);
        forgpass=(TextView)findViewById(R.id.txtforgpass);

        txtforgotpass = (TextView)findViewById(R.id.txtforgpass);

        btnlog = (Button)findViewById(R.id.btnlogin);

        listcat=new ArrayList<>();
        listcat.add("Admin");
        listcat.add("User");
        listcat.add("Service Provider");

        customspinner c=new customspinner(MainActivity.this,logo,listcat);
        category.setAdapter(c);


        txtforgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedcat=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ureg.class);
                startActivity(i);
            }
        });

        forgpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedcat == 0){

                }
                else if (selectedcat==1){

                    Intent i = new Intent(getApplicationContext(),Forgotpass.class);
                    startActivity(i);
                }
                else {


                    Intent i = new Intent(getApplicationContext(),Forgotpassserv.class);
                    startActivity(i);

                }

            }
        });



        btnlog.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sname = edname.getText().toString();
                spass = edpass.getText().toString();


                if (sname.length()==0 || spass.length()==0){

                    Toast.makeText(MainActivity.this,"Fill all the fields",Toast.LENGTH_LONG).show();
                    edname.setError("Fill this");
                    edpass.setError("Fill this");
                }
                else {

                    if (selectedcat == 0){
                        url = ipaddress.ip + "alogin.php?aemail="+sname+"&apass="+spass;
                    }
                    else if (selectedcat==1){
                    url = ipaddress.ip+"ulogin.php?uemail="+sname+"&upass="+spass;
                    }
                    else {
                    url = ipaddress.ip + "tlogin.php?temail=" + sname + "&tpass=" + spass;
                    }

                    try {
                        new LoginData().execute();
                    }catch (Exception e)
                    {
//                        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();

                    }


                }

            }
        });


    }

    class LoginData extends AsyncTask<Void,Void,Void>
    {

        JSONArray ja;
        @Override
        protected Void doInBackground(Void... params) {
            try {
            js = new JsonHelper();

            result = js.getdata(url);


                JSONObject jo = new JSONObject(result);


                if(jo.has("data"))
                ja = jo.getJSONArray("data");

                JSONObject jo1 = ja.getJSONObject(0);

                l = new loginpojo();


                l.setId(jo1.getString("id"));
                l.setName(jo1.getString("name"));
                l.setEmail(jo1.getString("email"));
                l.setPass(jo1.getString("pass"));
                l.setPhone(jo1.getString("phone"));
                l.setCity(jo1.getString("city"));
                l.setState(jo1.getString("state"));
                l.setCountry(jo1.getString("country"));
                l.setAdd(jo1.getString("addr"));


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                if (result.length() < 3) {
                    Toast.makeText(MainActivity.this, "Invalid  Username / Password", Toast.LENGTH_LONG).show();
                    edname.setText("");
                    edpass.setText("");
                    edname.requestFocus();

                } else {

                    switch (selectedcat) {

                        case 0:

                            SharedPreferences sp = getSharedPreferences("pref", MODE_WORLD_READABLE);
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putInt("catid", 0);
                            edit.putString("id", l.getId());
                            edit.putString("name", l.getName());
                            edit.putString("email", l.getEmail());
                            edit.putString("pass", l.getPass());
                            edit.putString("phone", l.getPhone());
                            edit.putString("city", l.getCity());
                            edit.putString("state", l.getState());
                            edit.putString("country", l.getCountry());
                            edit.putString("add", l.getAdd());
                            edit.commit();

                            Intent i = new Intent(MainActivity.this, MainAdmin.class);
                            i.putExtra("name", l.getName());
                            startActivity(i);
                            MainActivity.this.finish();

                            break;

                        case 1:


                            SharedPreferences sp1 = getSharedPreferences("pref", MODE_WORLD_READABLE);
                            SharedPreferences.Editor edit1 = sp1.edit();
                            edit1.putInt("catid", 1);
                            edit1.putString("id", l.getId());
                            edit1.putString("name", l.getName());
                            edit1.putString("email", l.getEmail());
                            edit1.putString("pass", l.getPass());
                            edit1.putString("phone", l.getPhone());
                            edit1.putString("city", l.getCity());
                            edit1.putString("state", l.getState());
                            edit1.putString("country", l.getCountry());
                            edit1.putString("add", l.getAdd());
                            edit1.commit();

                            Intent i1 = new Intent(MainActivity.this, MainUser.class);
                            i1.putExtra("name", l.getName());
                            startActivity(i1);
                            MainActivity.this.finish();

                            break;

                        case 2:


                            SharedPreferences sp2 = getSharedPreferences("pref", MODE_WORLD_READABLE);
                            SharedPreferences.Editor edit2 = sp2.edit();
                            edit2.putInt("catid", 2);
                            edit2.putString("id", l.getId());
                            edit2.putString("name", l.getName());
                            edit2.putString("email", l.getEmail());
                            edit2.putString("pass", l.getPass());
                            edit2.putString("phone", l.getPhone());
                            edit2.putString("city", l.getCity());
                            edit2.putString("state", l.getState());
                            edit2.putString("country", l.getCountry());
                            edit2.putString("add", l.getAdd());
                            edit2.commit();
                            ;

                            Intent i2 = new Intent(MainActivity.this, Mainserviceprovider.class);
                            i2.putExtra("name", l.getName());
                            startActivity(i2);
                            MainActivity.this.finish();

                            break;

                        default:

                            Toast.makeText(MainActivity.this, "Invalid  Username / Password", Toast.LENGTH_LONG).show();

                            break;
                    }
                }
            }catch (Exception e)
            {
                e.getMessage();
            }
            }

        }

}
