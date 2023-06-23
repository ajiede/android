package com.example.fragment.CS;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.fragment.R;

public class CsActivity extends AppCompatActivity {

    final String TAG="sss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs);
        if(savedInstanceState==null){
            Log.d(TAG,"no savedInstanceState");
            CsFragment csFragment=CsFragment.instance("sss");
            getSupportFragmentManager().beginTransaction().replace(R.id.layout_content_fragment2,csFragment).commitNow();
        }else {
            Log.d(TAG,"save InstanceState");
        }
    }
}