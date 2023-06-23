package com.example.sudokugame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private class Cell{
        int value;  //初始值
        boolean fixed; // 那些固定不能修改的属性
        Button bt;  //交互按钮，点击空白地方 就可以填内容

        public Cell(int initvalue, Context THIS)
        {
            value = initvalue;
            if (value!=0) fixed=true;
            else fixed=false;
            bt=new Button(THIS);
            if (fixed) bt.setText(String.valueOf(value));
            else bt.setTextColor(Color.BLUE);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(fixed) return;
                    value++;
                    if(value>9) value= 1;
                    bt.setText(String.valueOf(value));
                    if(correct()){
                        tv.setText("");
                    }else{
                        tv.setText("存在重复的数字");
                    }
                }
            });
        }
    }

    boolean completed()
    {
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(table[i][j].value == 0)
                    return false;
            }
        }
        return true;
    }

    boolean correct(int i1,int j1, int i2, int j2)
    {
        boolean[] seen = new boolean[10];
        for(int i=1;i<=9;i++) seen[i] = false;
        for(int i=i1;i<i2;i++){
            for(int j=j1;j<j2;j++){
                int value=table[i][j].value;
                if(value!=0){
                    if(seen[value]) return false;
                    seen[value]=true;
                }
            }
        }
        return true;
    }

    boolean correct()
    {
        for(int i=0;i<9;i++){
            if(!correct(i,0,i+1,9)) return false;
        }
        for(int j=0;j<9;j++){
            if(!correct(0,j,9,j+1)) return false;
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(!correct(3*i,3*j,3*i+3,3*j+3))
                    return false;
            }
        }
        return true;
    }

    Cell[][] table;
    String input;
    TableLayout tl;
    TextView tv;
    LinearLayout linlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        input = "2 9 ? 7 4 3 8 6 1 "+
                "4 ? 1 8 6 5 9 ? 7 "+
                "8 7 6 1 9 2 5 4 3 "+
                "3 8 7 4 5 9 2 1 6 "+
                "6 1 2 3 ? 7 4 ? 5 "+
                "? 4 9 2 ? 6 7 3 8 "+
                "? ? 3 5 2 4 1 8 9 "+
                "9 2 8 6 7 1 ? 5 4 "+
                "1 5 4 9 3 ? 6 7 2 ";
        String[] split = input.split(" ");
        table=new Cell[9][9];
        tl = new TableLayout(this);
        for(int i=0;i<9;i++){
            TableRow tr = new TableRow(this);
            for(int j=0;j<9;j++){
                String s=split[i*9+j];
                Character c = s.charAt(0);
                table[i][j] = new Cell(c=='?'?0:c-'0',this);
                tr.addView(table[i][j].bt);
            }
            tl.addView(tr);
        }
        tl.setShrinkAllColumns(true);  //确保适应屏幕
        tl.setStretchAllColumns(true);
        tv=new TextView(this);
        linlay = new LinearLayout(this);
        linlay.addView(tl);
        linlay.addView(tv);
        linlay.setOrientation(LinearLayout.VERTICAL);
        setContentView(linlay);
    }
}