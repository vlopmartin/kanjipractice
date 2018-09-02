package com.vlopmartin.apps.kanjipractice.activities;

import android.os.Bundle;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kanjiReadView = findViewById(R.id.kanjiRead);
        kanjiWrittenView = findViewById(R.id.kanjiWritten);
    }

    public void onSave(View v) {
        saveAndExit(v, Kanji.GLOBAL_SET);
    }

    public void onSaveToPracticeSet(View v) {
        saveAndExit(v, Kanji.PRACTICE_SET);
    }

    private void saveAndExit(View v, int set) {
        String kanjiRead = kanjiReadView.getText().toString();
        String kanjiWritten = kanjiWrittenView.getText().toString();
        Kanji kanji = new Kanji(0, kanjiRead, kanjiWritten, set);
        try {
            kanji.save(this.getApplicationContext());
            this.setResult(RESULT_OK);
            this.finish();
        } catch (Kanji.DuplicateKanjiException e) {
            Snackbar.make(v, R.string.duplicate_kanji_message, Snackbar.LENGTH_LONG).show();
        }
    }

}
