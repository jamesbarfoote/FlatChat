package com.example.barfoote.james.flatchatapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by James on 11/17/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SQLiteUser.db";
    private static final int DATABASE_VERSION = 1;
    public static final String USER_TABLE_NAME = "user";
    public static final String USER_COLUMN_ID = "_id";
    public static final String USER_COLUMN_USER_ID = "userID";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_GROUP = "group";
    public static final String USER_COLUMN_PICTURE = "pic";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USER_TABLE_NAME + "(" +
                        USER_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        USER_COLUMN_USER_ID + " INTEGER, " +
                        USER_COLUMN_EMAIL + " TEXT, " +
                        USER_COLUMN_GROUP + " TEXT," +
                        USER_COLUMN_PICTURE + " BLOB)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {// TODO: 11/17/2015 change this so it properly upgrades
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(int userID, String email, String group, byte[] pic) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_USER_ID, userID);
        contentValues.put(USER_COLUMN_EMAIL, email);
        contentValues.put(USER_COLUMN_GROUP, group);
        contentValues.put(USER_COLUMN_PICTURE, pic);
        db.insert(USER_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updatePerson(Integer id, int userID, String email, String group, byte[] pic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_USER_ID, userID);
        contentValues.put(USER_COLUMN_EMAIL, email);
        contentValues.put(USER_COLUMN_GROUP, group);
        contentValues.put(USER_COLUMN_PICTURE, pic);
        db.update(USER_TABLE_NAME, contentValues, USER_COLUMN_ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Cursor getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + USER_TABLE_NAME + " WHERE " +
                USER_COLUMN_ID + "=?", new String[] { Integer.toString(id) } );
        return res;
    }
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + USER_TABLE_NAME, null );
        return res;
    }

    public Integer deleteUser(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USER_TABLE_NAME,
                USER_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }

}
