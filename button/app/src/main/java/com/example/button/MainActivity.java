package com.example.button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rd_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton man = (RadioButton) findViewById(R.id.man);
                RadioButton woman = (RadioButton) findViewById(R.id.woman);
                switch (i){
                    case R.id.man:
                        Toast.makeText(MainActivity.this, man.getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.woman:
                        Toast.makeText(MainActivity.this, woman.getText(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }
}