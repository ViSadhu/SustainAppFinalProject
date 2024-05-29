package com.example.vihang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context) {
        this(context, "dbStudents", null, 1);
        Log.d("DBHelper", "constructor called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // query to create a table
        String query;

        query = "CREATE TABLE IF NOT EXISTS tbl_funfacts " +
                "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "content LONGTEXT" +
                ")";
        db.execSQL(query);

    }

    public void addTestingFacts() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // facts from https://recyclingpartnership.org/communitiesforrecycling/16-fun-recycling-facts-for-kids/
        values.put("content",
                "Glass can be recycled over and over again indefinitely. It never loses its purity or its composure, " +
                        "which makes it the perfect material to continue recycling and producing.");
        db.insert("tbl_funfacts", null, values);

        values.put("content", "How long does it take for a used aluminum drink can to be " +
                "recycled into a new one and put back on the grocery shelf? Just 60 days.");
        db.insert("tbl_funfacts", null, values);

        values.put("content", "Recycling helps save energy. If you recycle one glass bottle, " +
                "it saves enough energy to light a 100-watt bulb for four hours, power a computer for 30 minutes, " +
                "or a television for 20 minutes.");
        db.insert("tbl_funfacts", null, values);

        values.put("content", "Recycled cartons can be used to make toilet paper, paper towels, " +
                "or even eco-friendly building materials like roof cover boards.");
        db.insert("tbl_funfacts", null, values);

        values.put("content", "One metric ton of electronic scrap from personal computers " +
                "contains more gold than that recovered from 17 tons of gold ore.");
        db.insert("tbl_funfacts", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String> getFacts() {

        ArrayList<String> facts = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("tbl_funfacts", new String[]{"content"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            facts.add(cursor.getString(0));
        }

        cursor.close();
        return facts;
    }

    public int getFactsCount() {
        return getFacts().size();
    }

    public String getRandomFact() {

        Log.d("DBHelper", "Facts count: " + getFactsCount());

        int random = (int) (Math.random() * getFactsCount());


        Log.d("DBHelper", "All facts: " + getFacts());
        Log.d("DBHelper", "Random value: " + random);
        Log.d("DBHelper", "Random Fact: " + getFacts().get(random));

        return getFacts().get(random);

    }
}
