package com.example.lab5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperImage extends SQLiteOpenHelper {
    private static String DB_NAME = "history.db";
    private static int DB_VERSION = 1;
    private final String TABLE_NAME = "image";
    private final String COLUMN_ID = "id";
    private final String COLUMN_IMAGE = "image";
    private final String COLUMN_LIKE = "likeOrDislike";

    private final Context context;

    public DBHelperImage(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IMAGE + " BLOB, " +
                COLUMN_LIKE + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
