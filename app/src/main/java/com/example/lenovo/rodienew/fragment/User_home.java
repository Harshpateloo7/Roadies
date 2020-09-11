package com.example.lenovo.rodienew.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.example.lenovo.rodienew.R;

/**
 * Created by ADMIN on 23-02-2017.
 */

public class User_home extends Fragment {

    int mFlipping = 0 ; // Initially flipping is off

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.user_home,null);
        ViewFlipper flipper = (ViewFlipper)v.findViewById(R.id.flipper1);

        if(mFlipping==0){
            /** Start Flipping */
            flipper.startFlipping();
            mFlipping=1;
        }
        else{
            /** Stop Flipping */
            flipper.stopFlipping();
            mFlipping=0;

        }

        return v;
    }
}
