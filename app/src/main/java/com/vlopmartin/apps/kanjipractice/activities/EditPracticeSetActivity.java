package com.vlopmartin.apps.kanjipractice.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        globalSetFragment = KanjiFragment.newInstance(0);
        practiceSetFragment = KanjiFragment.newInstance(1);
        transaction.add(R.id.globalSetFrame, globalSetFragment);
        transaction.add(R.id.practiceSetFrame, practiceSetFragment);
        transaction.commit();
    }

    @Override
    public void onListFragmentInteraction(Kanji kanjiItem) {
        int kanjiSet = kanjiItem.getSet();
        if (kanjiSet == 0) {
            globalSetFragment.removeItem(kanjiItem);
            practiceSetFragment.addItem(kanjiItem);
            kanjiItem.setSet(1);
        } else if (kanjiSet == 1) {
            practiceSetFragment.removeItem(kanjiItem);
            globalSetFragment.addItem(kanjiItem);
            kanjiItem.setSet(0);
        }
        kanjiItem.save(this.getApplicationContext());
    }

    public void onAddKanji(View v) {
        Intent intent = new Intent(this, NewKanjiActivity.class);
        this.startActivity(intent);
    }
}
