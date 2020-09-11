package com.example.lenovo.rodienew.activity;

import android.app.Fragment;
import android.app.FragmentManager;
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
import com.example.lenovo.rodienew.adapter.MyfavList;
import com.example.lenovo.rodienew.fragment.Feedback;
import com.example.lenovo.rodienew.fragment.MyaddedService;
import com.example.lenovo.rodienew.fragment.SerPro_list;
import com.example.lenovo.rodienew.fragment.ServiceProlistUser;
import com.example.lenovo.rodienew.fragment.Transporter_home;
import com.example.lenovo.rodienew.fragment.User_Acoount;
import com.example.lenovo.rodienew.fragment.User_addreq;
import com.example.lenovo.rodienew.fragment.User_home;

public class MainUser extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sp;
    String sname,semail;
    TextView name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sp=getSharedPreferences("pref",MODE_WORLD_READABLE);
        sname=sp.getString("name","");
        semail=sp.getString("email","");

       /* Fragment f=new Fragment();
        Transporter_home h=new Transporter_home();
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.addToBackStack(f.getClass().getName());
        ft.replace(R.id.content_main_user,h);
        ft.commit();*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
         View v=navigationView.getHeaderView(0);
        name=(TextView)v.findViewById(R.id.txtuname);
        email=(TextView)v.findViewById(R.id.txtemail);
        name.setText(sname);
        email.setText(semail);


        Fragment f=new Fragment();
        ServiceProlistUser h1=new ServiceProlistUser(MainUser.this);
        FragmentTransaction ft1=getFragmentManager().beginTransaction();
        ft1.addToBackStack(f.getClass().getName());
        ft1.replace(R.id.content_main_user,h1);
        ft1.commit();

    }

    @Override
    public void onBackPressed() {

        FragmentManager fm = getFragmentManager();
        int count = fm.getBackStackEntryCount();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (count>0){

            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            SharedPreferences.Editor ed = sp.edit();
            ed.clear();
            ed.commit();
            Intent i=new Intent(MainUser.this,MainActivity.class);
            startActivity(i);
            MainUser.this.finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home1) {
             Fragment f=new Fragment();
            ServiceProlistUser h1=new ServiceProlistUser(MainUser.this);
            FragmentTransaction ft1=getFragmentManager().beginTransaction();
            ft1.addToBackStack(f.getClass().getName());
            ft1.replace(R.id.content_main_user,h1);
            ft1.commit();

           /* Fragment f=new Fragment();
            Transporter_home h=new Transporter_home();
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.addToBackStack(f.getClass().getName());
            ft.replace(R.id.content_main_user,h);
            ft.commit();*/

        } else if (id == R.id.nav_myaccount) {

            Fragment f=new Fragment();
            User_Acoount h=new User_Acoount(MainUser.this);
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.addToBackStack(f.getClass().getName());
            ft.replace(R.id.content_main_user,h);
            ft.commit();
        } else if (id == R.id.nav_addrequest) {
            Fragment f=new Fragment();
            User_addreq h=new User_addreq (MainUser.this);
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.addToBackStack(f.getClass().getName());
            ft.replace(R.id.content_main_user,h);
            ft.commit();

        } else if (id == R.id.nav_myfav) {

            Fragment f=new Fragment();
            MyfavList h = new MyfavList(MainUser.this);
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.addToBackStack(f.getClass().getName());
            ft.replace(R.id.content_main_user,h);
            ft.commit();

        } else if (id == R.id.nav_service) {

            Fragment f=new Fragment();
            MyaddedService h = new MyaddedService(MainUser.this);
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.addToBackStack(f.getClass().getName());
            ft.replace(R.id.content_main_user,h);
            ft.commit();


        } else if (id == R.id.nav_feedback) {
            Fragment f=new Fragment();
            Feedback h = new Feedback(MainUser.this);
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.addToBackStack(f.getClass().getName());
            ft.replace(R.id.content_main_user,h);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
