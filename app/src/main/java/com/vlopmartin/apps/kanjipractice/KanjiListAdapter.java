package com.vlopmartin.apps.kanjipractice;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vlopmartin.apps.kanjipractice.activities.KanjiFragment;
import com.vlopmartin.apps.kanjipractice.activities.KanjiFragment.OnListFragmentInteractionListener;
import com.vlopmartin.apps.kanjipractice.activities.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class KanjiListAdapter extends RecyclerView.Adapter<KanjiListAdapter.KanjiViewHolder> {

    public final List<Kanji> kanjiList;
    private final OnListFragmentInteractionListener listener;

    public KanjiListAdapter(List<Kanji> items, OnListFragmentInteractionListener listener) {
        kanjiList = items;
        this.listener = listener;
    }

    @Override
    public KanjiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_kanji, parent, false);
        return new KanjiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final KanjiViewHolder holder, final int position) {
        holder.item = kanjiList.get(position);
        holder.contentView.setText(kanjiList.get(position).getWritten());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onListFragmentInteraction(holder.item, KanjiFragment.ACTION_SELECT);
                }
            }
        });

        holder.view.findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListFragmentInteraction(holder.item, KanjiFragment.ACTION_DELETE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kanjiList.size();
    }

    public class KanjiViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView contentView;
        public Kanji item;

        public KanjiViewHolder(View view) {
            super(view);
            this.view = view;
            contentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }


}
