package com.example.notez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database name
    private static final String DATABASE_NAME = "SignUp.db";
    // Database version
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        // Create the user table
        MyDatabase.execSQL("CREATE TABLE allusers(username TEXT PRIMARY KEY, password TEXT)");
        // Create the notes table
        MyDatabase.execSQL("CREATE TABLE notes(username TEXT, title TEXT, subtitle TEXT, content TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {
        // Drop the tables if they exist
        MyDatabase.execSQL("DROP TABLE IF EXISTS allusers");
        MyDatabase.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(MyDatabase); // Recreate tables
    }

    // Method to insert new user data
    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDatabase.insert("allusers", null, contentValues);

        return result != -1; // Return true if insert was successful
    }

    // Method to check if a username already exists
    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM allusers WHERE username = ?", new String[]{username});
        return cursor.getCount() > 0; // Return true if username exists
    }

    // Method to validate username and password
    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM allusers WHERE username = ? AND password = ?", new String[]{username, password});
        return cursor.getCount() > 0; // Return true if credentials are valid
    }

    // Method to insert a new note
    public Boolean insertNote(String username, String title, String subtitle, String content) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("title", title);
        contentValues.put("subtitle", subtitle);
        contentValues.put("content", content);
        long result = MyDatabase.insert("notes", null, contentValues);

        return result != -1; // Return true if insert was successful
    }

    // Method to retrieve notes for a specific user
    public Cursor getNotes(String username) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        return MyDatabase.rawQuery("SELECT * FROM notes WHERE username = ?", new String[]{username});
    }

    // Method to update an existing note (optional)
    public Boolean updateNote(String username, String title, String subtitle, String content) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("subtitle", subtitle);
        contentValues.put("content", content);
        int result = MyDatabase.update("notes", contentValues, "username = ? AND title = ?", new String[]{username, title});

        return result > 0; // Return true if the update was successful
    }
}
