package com.ryan.checked.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ryan on 2016/11/10.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "checked.db";
    private static final int DATABASE_VERSION = 1;
    private final Context mContext;




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Constants.TABLE_NAME+ "("
                +Constants._ID+" INTEGER PRIMARY KEY,"
                +Constants.DATE+" TEXT,"
                +Constants.TIMESTAMP+" TEXT,"
                +Constants.TYPE+" INTEGER,"
                +Constants.TAG+" TEXT,"
                +Constants.DESCRIBE+" TEXT,"
                +Constants.MODIFIED+" INTEGER NOT NULL DEFAULT 0"
                +");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
