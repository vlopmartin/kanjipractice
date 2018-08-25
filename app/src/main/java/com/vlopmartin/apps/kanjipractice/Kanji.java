package com.vlopmartin.apps.kanjipractice;

public class Kanji {

    public static final String createTableSQL = "CREATE TABLE KANJIS (" +
            "ID INTEGER PRIMARY KEY, " +
            "READ TEXT, " +
            "WRITTEN TEXT," +
            "SET INTEGER)";

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
}
