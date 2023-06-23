package com.example.button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private RadioButton man;
    private RadioButton woman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        man = (RadioButton) findViewById(R.id.man);
        woman = (RadioButton) findViewById(R.id.woman);

        man.setOnClickListener(this);
        woman.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.man:
                Toast.makeText(MainActivity2.this, man.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.woman:
                Toast.makeText(MainActivity2.this, woman.getText(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}