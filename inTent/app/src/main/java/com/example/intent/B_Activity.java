package com.example.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class B_Activity extends AppCompatActivity {

    private RadioGroup rg;
    private CheckBox cb1, cb2, cb3;
    private EditText ed1, ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_b);
        rg = (RadioGroup) findViewById(R.id.action_group);
        cb1 = (CheckBox) findViewById(R.id.cb_c1);
        cb2 = (CheckBox) findViewById(R.id.cb_c2);
        cb3 = (CheckBox) findViewById(R.id.cb_c3);
        ed1 = (EditText) findViewById(R.id.edit_1);
        ed2 = (EditText) findViewById(R.id.edit_2);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.button1:
                int id = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(id);
                String action = rb.getText().toString();
                Intent intent = new Intent(action);
                List<String> catStr = getCatageorys();
                if (catStr != null && catStr.size() > 0) {
                    for (String s : catStr) {
                        intent.addCategory(s);
                    }
                }
                String uri = ed1.getText().toString();
                String type = ed2.getText().toString();
                if (type != null && type.length() > 0) {
                    intent.setDataAndType(Uri.parse(uri), type);
                } else {
                    intent.setData(Uri.parse(uri));


                }


        }
    }

    private List<String> getCatageorys(){
        List<String> catagorys=new ArrayList<String>();
        if(cb1.isChecked()){
            catagorys.add(cb1.getText().toString());
        }

        if(cb2.isChecked()){
            catagorys.add(cb2.getText().toString());
        }

        if(cb3.isChecked()){
            catagorys.add (cb3.getText().toString());

        }

        return catagorys;


    }
}