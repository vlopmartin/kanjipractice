package com.vlopmartin.apps.kanjipractice;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private Resources resources;
    private int selectedItem;

    public KanjiListAdapter(List<Kanji> items, OnListFragmentInteractionListener listener, Resources resources) {
        kanjiList = items;
        this.listener = listener;
        this.resources = resources;
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
        holder.idView.setText(String.valueOf(kanjiList.get(position).getId()));
        holder.contentView.setText(kanjiList.get(position).getWritten());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onListFragmentInteraction(holder.item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return kanjiList.size();
    }

    public class KanjiViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView idView;
        public final TextView contentView;
        public Kanji item;

        public KanjiViewHolder(View view) {
            super(view);
            this.view = view;
            idView = (TextView) view.findViewById(R.id.item_number);
            contentView = (TextView) view.findViewById(R.id.content);
        }

        public void onClick(View view) {
            System.out.println("Clicked");
        }

        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }

    public int getSelectedItem() { return selectedItem; }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }


}
