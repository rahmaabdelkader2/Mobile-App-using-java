package com.example.lab2_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseAdaptor {
    static DatabaseHelper dbHelper;
    //private Context context;
    public DatabaseAdaptor(Context context) {
        dbHelper = new DatabaseHelper(context);


    }


    public long addEntry(DataClass data) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result;

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.COLUMN_MOBILE_NUMBER, data.getMobileNumber());
            contentValues.put(DatabaseHelper.COLUMN_MESSAGE, data.getMessage());

            result = db.insertWithOnConflict(DatabaseHelper.TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        } finally {
            db.close();
        }

        return result;
    }


    public DataClass getEntry(String mobileNumber) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        DataClass entry = null;
        Cursor cursor = null;

        try {
            String[] args = {mobileNumber};
            cursor = db.query(
                    DatabaseHelper.TABLE_NAME,
                    null,
                    "mobile_number = ?",
                    args,
                    null, null, null);

            if (cursor.moveToFirst()) {
                entry = new DataClass(cursor.getString(0), cursor.getString(1));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return entry;
    }




    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "user_messages.db";
        private static final String TABLE_NAME = "messages";

        private static final String COLUMN_MOBILE_NUMBER = "mobile_number";
        private static final String COLUMN_MESSAGE = "message";

        public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_MOBILE_NUMBER + " TEXT PRIMARY KEY, " +
                    COLUMN_MESSAGE + " TEXT NOT NULL)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}