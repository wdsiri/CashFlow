package com.example.cashflow.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LoginDBOpenHelper extends SQLiteOpenHelper {

    public static final String TABLE_LOGINS = "logins";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String DATABASE_NAME = "logins.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_LOGINS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + "text not null,"
            + COLUMN_EMAIL + " text not null,"
            + COLUMN_PASSWORD + "text not null);";

    public LoginDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LoginDBOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGINS);
        onCreate(db);
    }
}
