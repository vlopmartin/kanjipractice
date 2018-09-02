package com.vlopmartin.apps.kanjipractice.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vlopmartin.apps.kanjipractice.Kanji;
import com.vlopmartin.apps.kanjipractice.R;

import java.util.Collections;
import java.util.List;

public class PracticeActivity extends AppCompatActivity {

    protected TextView kanjiReadView;
    protected TextView kanjiWrittenView;
    protected View practiceView;
    protected View completedView;
    protected ImageButton previousButton;
    protected ImageButton nextButton;
    protected WebView webView;

    protected List<Kanji> practiceList;
    protected int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kanjiReadView = findViewById(R.id.kanjiRead);
        kanjiWrittenView = findViewById(R.id.kanjiWritten);
        practiceView = findViewById(R.id.practiceView);
        completedView = findViewById(R.id.completedView);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);
        webView = findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);

        practiceList = Kanji.getSet(this.getApplicationContext(), Kanji.PRACTICE_SET);
        Collections.shuffle(practiceList);
        currentIndex = -1;
        moveSteps(1);
    }

    public void onReturn(View v) {
        setResult(RESULT_OK);
        finish();
    }

    public void onNext(View v) {
        moveSteps(1);
    }

    public void onPrevious(View v) {
        moveSteps(-1);
    }

    public void onToggleKanji(View v) {
        int visibility = kanjiWrittenView.getVisibility();
        if (visibility == View.INVISIBLE) kanjiWrittenView.setVisibility(View.VISIBLE);
        else kanjiWrittenView.setVisibility(View.INVISIBLE);
    }

    public void onShowWebView(View v) {
        String written = kanjiWrittenView.getText().toString();
        String[] jishoUrl = {"https://jisho.org/search/", "%23kanji"};
        webView.setVisibility(View.VISIBLE);
        webView.loadUrl(jishoUrl[0] + written + jishoUrl[1]);
    }

    public void moveSteps(int n) {
        currentIndex += n;
        if (currentIndex == 0) previousButton.setEnabled(false);
        else previousButton.setEnabled(true);
        if (currentIndex >= practiceList.size()) {
            practiceView.setVisibility(View.GONE);
            completedView.setVisibility(View.VISIBLE);
            nextButton.setEnabled(false);
        } else {
            completedView.setVisibility(View.GONE);
            practiceView.setVisibility(View.VISIBLE);
            nextButton.setEnabled(true);

            Kanji kanji = practiceList.get(currentIndex);
            kanjiReadView.setText(kanji.getRead());
            kanjiWrittenView.setText(kanji.getWritten());
            kanjiWrittenView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            if (webView.getVisibility() == View.VISIBLE) {
                webView.setVisibility(View.GONE);
                return true;
            } else {
                return super.onOptionsItemSelected(item);
            }
        }
        return true;
    }


}
