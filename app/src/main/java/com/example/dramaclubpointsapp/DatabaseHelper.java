package com.example.dramaclubpointsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    //Define facts about the database to set it up including its name
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pointSubmissions.db";

    //Define tables in the database
    public static final String TABLE_SCORES = "scores";             // table name
    public static final String COLUMN_ID = "_id";                   // unique id for the entry
    public static final String COLUMN_TIME_STAMP = "timeStamp";
    public static final String COLUMN_FIRST_NAME = "first";
    public static final String COLUMN_LAST_NAME = "last";
    public static final String COLUMN_PRODUCTION = "prod";
    public static final String COLUMN_POINTS = "points";
    public static final String COLUMN_MEMES = "memes";

    //constructor
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    /*
    Here we create the specific query needed to create the table.  The syntax is EXTREMELY important.
    The execSQL method is called to execute this request to create the database as defined by the query.
     */

    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " + TABLE_SCORES + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TIME_STAMP + " TEXT, " +
                COLUMN_FIRST_NAME + " TEXT, " +
                COLUMN_LAST_NAME + " TEXT, " +
                COLUMN_PRODUCTION + " TEXT, " +

                //this following line could cause problems
                COLUMN_POINTS + " DOUBLE, " +
                COLUMN_MEMES + " TEXT )" + ";";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_SCORES;
        db.execSQL(query);
        onCreate(db);
    }


    public void addSubmission(PointSubmission sub) {
        // ContentValues is like a datastructure that allows you to attach values
        // it is similar to how we would put items into an intent

        ContentValues values = new ContentValues();


        //you gotta go thru this and add new stuff for all the values of the columns
        //you got this and keep up the good work... have fun!!
        values.put(COLUMN_TIME_STAMP, sub.getTime());
        values.put(COLUMN_FIRST_NAME, sub.getFirstName());
        values.put(COLUMN_LAST_NAME, sub.getLastName());
        values.put(COLUMN_PRODUCTION, sub.getNameOfProduction());
        values.put(COLUMN_POINTS, sub.getPoints());
        values.put(COLUMN_MEMES, sub.getMemes());
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TABLE_SCORES, null, values);  // inserts these values into this table

        Log.d(TAG, "Tried to insert score, result was " + result);

        db.close();             // need to close the database when we are done modifying it.
    }

    // This method creates a String representation of all the database elements
    // this is simply for quick viewing of our database contents

    public String databasetoString() {
        String dbstring = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SCORES + " WHERE 1";
        // This means to select all from the database

        // The cursor will extract the entries from the database
        Cursor c = db.rawQuery(query, null);

        // Move the cursor to the first position and then move through the db to the last
        c.moveToFirst();
        while(!c.isAfterLast()) {
            if(c.getString(c.getColumnIndex(COLUMN_TIME_STAMP)) != null ) {
                dbstring += c.getString(c.getColumnIndex(COLUMN_TIME_STAMP)) + "\t";
                dbstring += c.getString(c.getColumnIndex(COLUMN_LAST_NAME)) + ", ";
                dbstring += c.getString(c.getColumnIndex(COLUMN_FIRST_NAME)) + "\t";
                //dbstring += c.getString(c.getColumnIndex(COLUMN_PRODUCTION)) + ", ";
                dbstring += c.getString(c.getColumnIndex(COLUMN_POINTS));
                //dbstring += c.getString(c.getColumnIndex(COLUMN_MEMES));
                dbstring += "\n";
            }
            c.moveToNext();
        }

        db.close();
        return dbstring;

    }
}
