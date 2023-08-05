package com.example.wastemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PickupData {

    // Database constants
    private static final String DATABASE_NAME = "pickup_data.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "pickup_requests";

    // Column names
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_CONTACT = "contact";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";

    private Context context;
    private SQLiteDatabase database;

    public PickupData(Context context) {
        this.context = context;
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long insertPickup(String name, String address, String contact) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_CONTACT, contact);

        return database.insert(TABLE_NAME, null, values);
    }

    public Cursor getAllPickups() {
        return database.query(TABLE_NAME, null, null, null, null, null, null);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_ADDRESS + " TEXT, " +
                    COLUMN_CONTACT + " TEXT)";
                    db.execSQL(createTableQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}



