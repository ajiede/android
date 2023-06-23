package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name,psw;
    Button add,del,change,seeAll;
    TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        psw = findViewById(R.id.psw);
        add = findViewById(R.id.add);
        del = findViewById(R.id.del);
        change = findViewById(R.id.change);
        seeAll = findViewById(R.id.seeAll);
        content = findViewById(R.id.content);
        DB db = new DB(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String p = psw.getText().toString();
                if(db.add(n,p)){
                    Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                if(db.del(n)){
                    Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String p = psw.getText().toString();
                if(db.change(n,p)){
                    Toast.makeText(MainActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText("");
                ArrayList a = db.getAll();
                for(Object b:a){
                    String n = content.getText().toString();
                    String str = ( (User)b ).toString()+"\n";
                    content.setText(str);
                }
            }
        });
    }
}