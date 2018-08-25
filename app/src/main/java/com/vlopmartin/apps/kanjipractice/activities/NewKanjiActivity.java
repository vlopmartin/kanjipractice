package com.vlopmartin.apps.kanjipractice.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.vlopmartin.apps.kanjipractice.Kanji;
import com.vlopmartin.apps.kanjipractice.R;

public class NewKanjiActivity extends AppCompatActivity {

    protected EditText kanjiReadView;
    protected EditText kanjiWrittenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_kanji);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kanjiReadView = findViewById(R.id.kanjiRead);
        kanjiWrittenView = findViewById(R.id.kanjiWritten);
    }

    public void onSave(View v) {
        String kanjiRead = kanjiReadView.getText().toString();
        String kanjiWritten = kanjiWrittenView.getText().toString();
        Kanji kanji = new Kanji(0, kanjiRead, kanjiWritten, 0);
        kanji.save(this.getApplicationContext());
        this.setResult(RESULT_OK);
        this.finish();
    }

}
