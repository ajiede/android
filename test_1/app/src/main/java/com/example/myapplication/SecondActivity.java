package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        System.out.println("SecondActivity-onCreate");
    }

    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("SecondActivity-onStart");
    }

    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("SecondActivity-onStop");
    }

    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("SecondActivity-onPause");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        System.out.println("SecondActivity-onRestart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("SecondActivity-onResume");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("SecondActivity-onDestroy");
    }
}
