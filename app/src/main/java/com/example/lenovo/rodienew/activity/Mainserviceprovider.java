package com.example.lenovo.rodienew.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lenovo.rodienew.R;
import com.example.lenovo.rodienew.fragment.Allreq;
import com.example.lenovo.rodienew.fragment.MyAcceptedreq;
import com.example.lenovo.rodienew.fragment.MypendingReq;
import com.example.lenovo.rodienew.fragment.ServiceProAccount;
import com.example.lenovo.rodienew.fragment.Transporter_home;

public class Mainserviceprovider extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences sp;
    String sname,semail;
    TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainserviceprovider);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sp=getSharedPreferences("pref",MODE_WORLD_READABLE);
        sname=sp.getString("name","");
        semail=sp.getString("email","");


        Fragment f=new Fragment();
        Transporter_home h=new Transporter_home();
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.addToBackStack(f.getClass().getName());
        ft.replace(R.id.content_service_provider,h);
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View v=navigationView.getHeaderView(0);

        Menu menu = navigationView.getMenu();
        MenuItem nav_home=menu.findItem(R.id.nav_home);
        nav_home.setTitle("Homes");
        nav_home.setIcon(R.drawable.bunglow);

        Menu menu1 = navigationView.getMenu();
        MenuItem nav_bservice=menu1.findItem(R.id.nav_bservice);
        nav_bservice.setIcon(R.drawable.datapen);

        Menu menu2 = navigationView.getMenu();
        MenuItem nav_request=menu2.findItem(R.id.nav_request);
        nav_request.setIcon(R.drawable.viewfile);

        Menu menu3 = navigationView.getMenu();
        MenuItem nav_service=menu3.findItem(R.id.nav_Service_Request);
        nav_service.setIcon(R.drawable.checkmak);

        Menu menu4 = navigationView.getMenu();
        MenuItem nav_acc=menu4.findItem(R.id.upservice);
        nav_acc.setIcon(R.drawable.account);





        name=(TextView)v.findViewById(R.id.txttname);
        email=(TextView)v.findViewById(R.id.txtemail);
        name.setText(sname);
        email.setText(semail);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainserviceprovider, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The ac1tion bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            SharedPreferences.Editor ed = sp.edit();
            ed.clear();
            ed.commit();
            Intent i=new Intent(Mainserviceprovider.this,MainActivity.class);
            startActivity(i);
            Mainserviceprovider.this.finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment f = null;

        if (id == R.id.nav_home) {
            Fragment f1=new Fragment();
            Transporter_home h=new Transporter_home();
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.addToBackStack(f1.getClass().getName());
            ft.replace(R.id.content_service_provider,h);
            ft.commit();
        }   else if (id == R.id.nav_bservice) {


            Fragment f1=new Fragment();
            MypendingReq h=new MypendingReq(Mainserviceprovider.this);
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.addToBackStack(f1.getClass().getName());
            ft.replace(R.id.content_service_provider,h);
            ft.commit();


        } else if (id == R.id.nav_request) {

            Fragment f1=new Fragment();
            Allreq h=new Allreq(Mainserviceprovider.this);
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.addToBackStack(f1.getClass().getName());
            ft.replace(R.id.content_service_provider,h);
            ft.commit();

        } else if (id == R.id.nav_Service_Request) {


            Fragment f1=new Fragment();
            MyAcceptedreq h=new MyAcceptedreq(Mainserviceprovider.this);
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.addToBackStack(f1.getClass().getName());
            ft.replace(R.id.content_service_provider,h);
            ft.commit();

        }

        else if (id == R.id.upservice){
            Fragment f1=new Fragment();
            ServiceProAccount h=new ServiceProAccount(Mainserviceprovider.this);
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.addToBackStack(f1.getClass().getName());
            ft.replace(R.id.content_service_provider,h);
            ft.commit();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
