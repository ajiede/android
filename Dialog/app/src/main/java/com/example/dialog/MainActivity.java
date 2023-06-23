package com.example.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int yourChoice=-1;
    private ArrayList<Integer> yourChoices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void normalDialog(View view){
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(this);
        normalDialog.setIcon(R.drawable.an);
        normalDialog.setTitle("我是一个普通的对话框");
        normalDialog.setMessage("你要点击哪一个按钮");
        normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        normalDialog.setCancelable(false);
        normalDialog.show();
    }

    public void threeDialog(View view){
        AlertDialog.Builder threeDialog = new AlertDialog.Builder(this);
        threeDialog.setTitle("我是第三个按钮")
                .setIcon(R.drawable.an)
                .setMessage("请点击任何一个按钮")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNeutralButton("忽略", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    public void listDialog(View view){
        final String[] item={"一班","二班","三班","四班","五班"};
        AlertDialog.Builder listDialog = new AlertDialog.Builder(this);
        listDialog.setTitle("列表选择对话框")
                .setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "你选择的是："+item[i], Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    public void singleDialog(View view){
        final String[] item ={"文科（历史）","理科（物理）"};
        yourChoice = -1;
        AlertDialog.Builder singleDialog = new AlertDialog.Builder(this);
        singleDialog.setTitle("单选对话框")
                .setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        yourChoice = i;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(yourChoice==-1){
                            Toast.makeText(MainActivity.this, "你的选择是:"+item[yourChoice], Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "你还没有选择", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .show();
    }

    public void multiDialog(View view){
        final String[] items = {"政治","地理","化学","生物","科学技术","体育"};
        final boolean[] initChoice = {false,false,false,false,false,false};
        yourChoices.clear();
        AlertDialog.Builder multiDialog = new AlertDialog.Builder(this);
        multiDialog.setTitle("多选对话框")
                .setMultiChoiceItems(items, initChoice, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            yourChoices.add(i);
                        }else{
                            yourChoices.remove(i);
                        }
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int size = yourChoices.size();
                        String str = "";
                        for(int a = 0;a < size; a++){
                            str+= "，"+items[yourChoices.get(a)];
                        }
                        Toast.makeText(MainActivity.this, "你选择科目是:"+str, Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
    
    public void editDialog(View view){
        AlertDialog.Builder editDialog = new AlertDialog.Builder(this);
        final EditText editText = new EditText(MainActivity.this);
        editDialog.setView(editText)
                .setTitle("Edit对话框")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, ""+editText.getText(), Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    public void custerDialog(View view){
        final View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.edit_layout,null);
        AlertDialog.Builder custerDialog = new AlertDialog.Builder(this);
        custerDialog.setView(inflate)
                .setTitle("自定义对话框")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText ed = (EditText) inflate.findViewById(R.id.edit1);
                        Toast.makeText(MainActivity.this, ""+ed.getText(), Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}