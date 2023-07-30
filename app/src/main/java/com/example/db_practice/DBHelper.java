package com.example.db_practice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper
{

    public DBHelper(@Nullable Context context) {
        super(context, "PhoneBook", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table Phone(ID integer primary key autoincrement,NAME text,NUMBER text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void adddata(String name, String number)
    {
        String query="insert into Phone(NAME,NUMBER) values ('"+name+"','"+number+"')";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public void updatedata(String name, String number)
    {
        String query="update Phone set Name='"+name+"',NUMBER='"+number+"' where ID="+1+"";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public Cursor displaydata()
    {
        String query = "select * from Phone";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = getReadableDatabase().rawQuery(query,null);
        return cursor;

    }

    public void datadelete(CharSequence id)
    {
        String query ="delete from Phone where ID="+id+"";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }
}
