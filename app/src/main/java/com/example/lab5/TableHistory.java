package com.example.lab5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import java.util.ArrayList;

public class TableHistory {

    private final String TABLE_NAME = "image";
    private final String COLUMN_ID = "id";
    private final String COLUMN_IMAGE = "image";
    private final String COLUMN_LIKE = "likeOrDislike";

    private int indexId;
    private int indexImage;
    private int indexLike;

    private DBHelperImage dbHelper;
    private SQLiteDatabase database;

    TableHistory(Context context) {
        dbHelper = new DBHelperImage(context);

        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);

        indexId = cursor.getColumnIndex(COLUMN_ID);
        indexImage = cursor.getColumnIndex(COLUMN_IMAGE);
        indexLike = cursor.getColumnIndex(COLUMN_LIKE);

        cursor.close();
        database.close();
    }

    public void insert(int like, Bitmap image) {

        database = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LIKE, like);
        byte[] picture = BitmapUtility.getBytes(image);
        cv.put(COLUMN_IMAGE, picture);

        database.insert(TABLE_NAME, null, cv);

        database.close();
    }

    public ArrayList<HistoryHelper> select() {
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + " LIMIT 10", null);
        cursor.moveToFirst();

        ArrayList<HistoryHelper> buffers = new ArrayList<>();
        byte[] image;

        while (!cursor.isAfterLast()) {
            image = cursor.getBlob(indexImage);
            buffers.add(new HistoryHelper(cursor.getInt(indexLike), BitmapUtility.getImage(image)));
            cursor.moveToNext();
        }

        cursor.close();
        database.close();

        return buffers;
    }

}
