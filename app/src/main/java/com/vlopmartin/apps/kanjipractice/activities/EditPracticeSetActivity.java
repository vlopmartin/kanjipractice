package com.vlopmartin.apps.kanjipractice.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vlopmartin.apps.kanjipractice.Kanji;
import com.vlopmartin.apps.kanjipractice.R;

public class EditPracticeSetActivity extends AppCompatActivity implements KanjiFragment.OnListFragmentInteractionListener {

    private Fragment globalSetFragment;
    private Fragment practiceSetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_practice_set);

        globalSetFragment = KanjiFragment.newInstance(0);
        practiceSetFragment = KanjiFragment.newInstance(0);

        // Add fragments
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.globalSetFrame, globalSetFragment);
        transaction.add(R.id.practiceSetFrame, practiceSetFragment);
        transaction.commit();

    }

    @Override
    public void onListFragmentInteraction(Kanji kanjiItem) {

    }
}
