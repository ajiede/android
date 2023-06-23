package com.example.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {
    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private SQLiteDatabase db;
    public DB(Context context){
        super(context,"TEST1.db",null,1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE users(name text primary key, psw text not null)";
        sqLiteDatabase.execSQL(sql);
    }
    public boolean add(String name, String psw){
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("psw",psw);
        long i = db.insert("users",null,values);
        if(i>0){
            Log.d("","插入成功");
            return true;
        }
        return false;
    }

    public boolean del(String name){
        long i = db.delete("users","name=?",new String[]{name});
        if(i>0){
            Log.d("","删除成功");
            return true;
        }
        return false;
    }

    public boolean change(String name, String NewPsw){
        ContentValues values = new ContentValues();
        values.put("psw",NewPsw);
        long i = db.update("users",values,"name=?",new String[]{name});
        if(i>0){
            Log.d("","修改成功");
            return true;
        }
        return false;
    }

    public ArrayList getAll(){
        ArrayList arrayList = new ArrayList<>();
        Cursor cursor = db.query("users",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String psw = cursor.getString(cursor.getColumnIndex("psw"));
            User u = new User(name,psw);
            arrayList.add(u);
        }
        return arrayList;
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
