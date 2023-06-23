package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("MainActivity-onCreate");
        EditText ed5 = (EditText) findViewById(R.id.ed5);
        EditText ed6 = (EditText) findViewById(R.id.ed6);
        EditText ed7 = (EditText) findViewById(R.id.ed7);
        EditText ed8 = (EditText) findViewById(R.id.ed8);

        ed5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "请输入数字", Toast.LENGTH_SHORT).show();
            }
        });

        ed6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Toast.makeText(MainActivity.this, "改变焦点", Toast.LENGTH_SHORT).show();
            }
        });

        ed7.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView tv, int i, KeyEvent keyEvent) {
                Toast.makeText(MainActivity.this, ""+tv.getText(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        ed8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Toast.makeText(MainActivity.this, "请输入", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Toast.makeText(MainActivity.this, ""+editable, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("MainActivity-onStart");
    }

    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("MainActivity-onStop");
    }

    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("MainActivity-onPause");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        System.out.println("MainActivity-onRestart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("MainActivity-onResume");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("MainActivity-onDestroy");
    }

    public void jump(View view){

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }



}