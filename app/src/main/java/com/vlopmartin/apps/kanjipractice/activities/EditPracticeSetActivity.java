package com.vlopmartin.apps.kanjipractice.activities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vlopmartin.apps.kanjipractice.Kanji;
import com.vlopmartin.apps.kanjipractice.R;

public class EditPracticeSetActivity extends AppCompatActivity implements KanjiFragment.OnListFragmentInteractionListener {

    private KanjiFragment globalSetFragment;
    private KanjiFragment practiceSetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_practice_set);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setFragments();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setFragments();
    }

    protected void setFragments() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (globalSetFragment != null) transaction.remove(globalSetFragment);
        if (practiceSetFragment != null) transaction.remove(practiceSetFragment);
        globalSetFragment = KanjiFragment.newInstance(Kanji.GLOBAL_SET);
        practiceSetFragment = KanjiFragment.newInstance(Kanji.PRACTICE_SET);
        transaction.add(R.id.globalSetFrame, globalSetFragment);
        transaction.add(R.id.practiceSetFrame, practiceSetFragment);
        transaction.commit();
    }

    @Override
    public void onListFragmentInteraction(Kanji kanjiItem, int action) {
        int kanjiSet = kanjiItem.getSet();
        if (action == KanjiFragment.ACTION_SELECT) {
            if (kanjiSet == Kanji.GLOBAL_SET) {
                globalSetFragment.removeItem(kanjiItem);
                practiceSetFragment.addItem(kanjiItem);
                kanjiItem.setSet(Kanji.PRACTICE_SET);
            } else if (kanjiSet == Kanji.PRACTICE_SET) {
                practiceSetFragment.removeItem(kanjiItem);
                globalSetFragment.addItem(kanjiItem);
                kanjiItem.setSet(Kanji.GLOBAL_SET);
            }
            kanjiItem.save(this.getApplicationContext());
        }
        else if (action == KanjiFragment.ACTION_DELETE) {
            if (kanjiSet == Kanji.GLOBAL_SET) globalSetFragment.removeItem(kanjiItem);
            else if (kanjiSet == Kanji.PRACTICE_SET) practiceSetFragment.removeItem(kanjiItem);
            kanjiItem.delete(this.getApplicationContext());
        }
    }

    public void onAddKanji(View v) {
        Intent intent = new Intent(this, NewKanjiActivity.class);
        this.startActivity(intent);
    }
}
