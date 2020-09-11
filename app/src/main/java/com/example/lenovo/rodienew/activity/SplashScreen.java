package com.example.lenovo.rodienew.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.lenovo.rodienew.R;

/**
 * Created by lenovo on 01-02-2017.
 */

public class SplashScreen extends AppCompatActivity {

    ImageView img;
    Animation animation;
    SharedPreferences sp;
    int sid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        img=(ImageView)findViewById(R.id.img);

        sp=getSharedPreferences("pref",MODE_WORLD_READABLE);
        sid=sp.getInt("catid",5);

        Animation animation= AnimationUtils.loadAnimation(SplashScreen.this,R.anim.blink);
        img.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if(sid==5) {
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                    SplashScreen.this.finish();
                }
                else if(sid==0)
                {
                    Intent i = new Intent(SplashScreen.this, MainAdmin.class);
                    startActivity(i);
                    SplashScreen.this.finish();
                }
                else if(sid==1)
                {
                    Intent i = new Intent(SplashScreen.this, MainUser.class);
                    startActivity(i);
                    SplashScreen.this.finish();
                }
                else if(sid==2){
                    Intent i = new Intent(SplashScreen.this, Mainserviceprovider.class);
                    startActivity(i);
                    SplashScreen.this.finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
