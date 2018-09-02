package com.vlopmartin.apps.kanjipractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Kanji {

    public static final int GLOBAL_SET = 0;
    public static final int PRACTICE_SET = 1;

    public static final String createTableSQL = "CREATE TABLE KANJIS (" +
            "ID INTEGER PRIMARY KEY, " +
            "READ TEXT, " +
            "WRITTEN TEXT, " +
            "KANJI_SET INTEGER)";

    private long id;
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    private String read;
    public String getRead() { return read; }
    public void setRead(String read) { this.read = read; }

    private String written;
    public String getWritten() { return written; }
    public void setWritten(String written) { this.written = written; }

    private int set;
    public int getSet() { return set; }
    public void setSet(int set) { this.set = set; }

    public Kanji(long id, String read, String written, int set) {
        this.setId(id);
        this.setRead(read);
        this.setWritten(written);
        this.setSet(set);
    }

    public void save(Context ctx) throws DuplicateKanjiException {
        SQLiteDatabase db = new DBHelper(ctx).getWritableDatabase();

        Cursor cursor = db.query("KANJIS", null, "WRITTEN = ?", new String[] {this.written}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            Kanji other = readCursor(cursor);
            if (other.getId() != this.getId()) {
                throw new DuplicateKanjiException();
            }
        }

        ContentValues values = new ContentValues();
        if (this.id != 0) {
            values.put("ID", this.getId());
        }
        values.put("READ", this.getRead());
        values.put("WRITTEN", this.getWritten());
        values.put("KANJI_SET", this.getSet());

        long id = db.replace("KANJIS", null, values);
        this.setId(id);
    }

    public void delete(Context ctx) {
        SQLiteDatabase db = new DBHelper(ctx).getWritableDatabase();

        db.delete("KANJIS", "ID = ?", new String[] {String.valueOf(this.getId())});
    }

    private static Kanji readCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex("ID"));
        String read = cursor.getString(cursor.getColumnIndex("READ"));
        String written = cursor.getString(cursor.getColumnIndex("WRITTEN"));
        int set = cursor.getInt(cursor.getColumnIndex("KANJI_SET"));
        return new Kanji(id, read, written, set);
    }

    public static Kanji getById(Context ctx, long id) {
        Kanji ret;

        SQLiteDatabase db = new DBHelper(ctx).getReadableDatabase();
        Cursor cursor = db.query("KANJIS", null, "id = ?", new String[] {String.valueOf(id)}, null, null, null);

        if (cursor.moveToNext()) {
            ret = readCursor(cursor);
        } else {
            ret = null;
        }

        cursor.close();
        return ret;
    }

    public static List<Kanji> getSet(Context ctx, int set) {
        List<Kanji> ret = new ArrayList<Kanji>();

        SQLiteDatabase db = new DBHelper(ctx).getReadableDatabase();
        Cursor cursor = db.query("KANJIS", null, "KANJI_SET = ?", new String[] {String.valueOf(set)}, null, null, null);

        while (cursor.moveToNext()) {
            ret.add(readCursor(cursor));
        }

        cursor.close();
        return ret;
    }

    public class DuplicateKanjiException extends Exception { }
}
