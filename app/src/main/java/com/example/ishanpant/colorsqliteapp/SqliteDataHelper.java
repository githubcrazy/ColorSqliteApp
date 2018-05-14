package com.example.ishanpant.colorsqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SqliteDataHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "color.db";
    public static final int DB_VERSION = 16;
    public static final String TABLE_NAME = "colorTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COLOR = "color";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_COLOR +
            " INTEGER" + ")";
    public SqliteDataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addName(String name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME , name);
        sqLiteDatabase.insert(TABLE_NAME , null ,contentValues);
    }

    public void addColor(int color) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COLOR,color);
        sqLiteDatabase.insert(TABLE_NAME , null ,values);
    }

    public Cursor getValues() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.query(TABLE_NAME , null , null , null ,null , null, COLUMN_ID + " DESC");
    }

    public Cursor getColor(String name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT color FROM colorTable WHERE name ="+name+";", null);
    }
}
