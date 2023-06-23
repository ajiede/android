package com.example.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

    }

    public void click(View view){

        switch (view.getId()){
            case R.id.to_a:
                Intent intent1=new Intent(this,A_Activity.class);
                startActivity(intent1);
                break;
            case R.id.to_b:
                Intent intent2=new Intent("com.sss.b1");
                startActivity(intent2);
                break;
            case R.id.to_c:
                Intent intent3=new Intent();
                intent3.setAction("com.sss.action.x2");
                intent3.addCategory("com.sss.category.c2");
                intent3.setDataAndType(Uri.parse("http://"),"audio/11");
                startActivity(intent3);
                break;
            case R.id.to_d:
                Intent intent4=new Intent(this,Share_Activity.class);
                startActivity(intent4);
                break;
        }
    }
}