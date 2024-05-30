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
        this(context, "dbVihang", null, 1);
        Log.d("DBHelper", "constructor called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // query to create the facts table
        String createFactsTblQuery;

        createFactsTblQuery = "CREATE TABLE IF NOT EXISTS tbl_funfacts " +
                "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "fact LONGTEXT" +
                ")";

        db.execSQL(createFactsTblQuery);

        // query to create the tips table
        String createTipsTblQuery;

        createTipsTblQuery = "CREATE TABLE IF NOT EXISTS tbl_tips " +
                "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tip LONGTEXT" +
                ")";

        db.execSQL(createTipsTblQuery);

    }

    public void addFacts() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // facts from https://recyclingpartnership.org/communitiesforrecycling/16-fun-recycling-facts-for-kids/
        values.put("fact",
                "Glass can be recycled over and over again indefinitely. It never loses its purity or its composure, " +
                        "which makes it the perfect material to continue recycling and producing.");
        db.insert("tbl_funfacts", null, values);

        values.put("fact", "How long does it take for a used aluminum drink can to be " +
                "recycled into a new one and put back on the grocery shelf? Just 60 days.");
        db.insert("tbl_funfacts", null, values);

        values.put("fact", "Recycling helps save energy. If you recycle one glass bottle, " +
                "it saves enough energy to light a 100-watt bulb for four hours, power a computer for 30 minutes, " +
                "or a television for 20 minutes.");
        db.insert("tbl_funfacts", null, values);

        values.put("fact", "Recycled cartons can be used to make toilet paper, paper towels, " +
                "or even eco-friendly building materials like roof cover boards.");
        db.insert("tbl_funfacts", null, values);

        values.put("fact", "One metric ton of electronic scrap from personal computers " +
                "contains more gold than that recovered from 17 tons of gold ore.");
        db.insert("tbl_funfacts", null, values);
    }

    public void addTips() {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // tips from https://www.earthday.org/7-tips-to-recycle-better/

        values.put("tip",
                "Don’t recycle anything smaller than a credit card. That includes straws," +
                        " bottle caps, coffee pods, plastic cutlery, paperclips," +
                        " and a million other tiny things that creep into our daily lives.");
        db.insert("tbl_tips", null, values);

        values.put("tip", "Not all plastics are treated equally. Rigid plastics are recyclable, " +
                "labeled by resin codes 1 through 7. Generally, the higher the number, the less recyclable it is.");
        db.insert("tbl_tips", null, values);

        values.put("tip", "When it comes to recycling, one of the worst things you can do is wishcycle. " +
                "That’s when we optimistically put non-recyclable objects in recycling bins.");
        db.insert("tbl_tips", null, values);

        values.put("tip", "Make sure your recyclables are clean, empty and dry.");
        db.insert("tbl_tips", null, values);

        values.put("tip", "Even though grocery bags are technically recyclable, " +
                "you must go to a drop-off area to do that, not your curbside bin.");
        db.insert("tbl_tips", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String> getFacts() {

        ArrayList<String> facts = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("tbl_funfacts", new String[]{"fact"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            facts.add(cursor.getString(0));
        }

        cursor.close();
        return facts;
    }

    public int getFactsCount() { return getFacts().size(); }

    public String getRandomFact() {

        Log.d("DBHelper", "Facts count: " + getFactsCount());

        int random = (int) (Math.random() * getFactsCount());


        Log.d("DBHelper", "All facts: " + getFacts());
        Log.d("DBHelper", "Random value: " + random);
        Log.d("DBHelper", "Random Fact: " + getFacts().get(random));

        return getFacts().get(random);

    }

    public ArrayList<String> getTips() {

        ArrayList<String> tips = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("tbl_tips", new String[]{"tip"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            tips.add(cursor.getString(0));
        }

        cursor.close();
        return tips;

    }
    public int getTipsCount() { return getTips().size(); }

    public String getRandomTip() {

        int random = (int) (Math.random() * getTipsCount());

        Log.d("DBHelper", "All tips: " + getTips());
        Log.d("DBHelper", "Random value: " + random);
        Log.d("DBHelper", "Random Tip: " + getTips().get(random));

        return random > 0 ? getTips().get(random) : getTips().get(1);

    }

}
