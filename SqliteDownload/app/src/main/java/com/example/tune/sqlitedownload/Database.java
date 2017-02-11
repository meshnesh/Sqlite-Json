package com.example.tune.sqlitedownload;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by tune on 10/02/2017.
 */

public class Database extends SQLiteOpenHelper {

    public Database(Context context)
    {
        super(context, "Florensis.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableEmp="create table bedTbl(pestid text,pestname text,status text)";
        db.execSQL(tableEmp);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void insertData(String pestid,String pestname, String status)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("pestid",pestid);
        values.put("pestname",pestname);
        values.put("status",status);
        sqLiteDatabase.insert("bedTbl",null,values);
    }
    public ArrayList fetchData() {
        ArrayList<String>stringArrayList=new ArrayList<String>();
        String fetchdata="select * from bedTbl";
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(fetchdata, null);
        if(cursor.moveToFirst()){
            do
            {
                stringArrayList.add(cursor.getString(0));
                stringArrayList.add(cursor.getString(1));
                stringArrayList.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        return stringArrayList;
    }
}
