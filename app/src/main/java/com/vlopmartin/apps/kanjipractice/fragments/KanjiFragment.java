package com.vlopmartin.apps.kanjipractice.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlopmartin.apps.kanjipractice.Kanji;
import com.vlopmartin.apps.kanjipractice.KanjiListAdapter;
import com.vlopmartin.apps.kanjipractice.R;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class KanjiFragment extends Fragment {

    public static final int ACTION_SELECT = 0;
    public static final int ACTION_DELETE = 1;

    private static final String ARG_KANJI_SET = "kanji-set";
    private int kanjiSet = 0;
    private OnListFragmentInteractionListener listener;
    private KanjiListAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public KanjiFragment() {
    }

    public static KanjiFragment newInstance(int kanjiSet) {
        KanjiFragment fragment = new KanjiFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_KANJI_SET, kanjiSet);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            kanjiSet = getArguments().getInt(ARG_KANJI_SET);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kanji_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new KanjiListAdapter(Kanji.getSet(this.getContext(), getArguments().getInt(ARG_KANJI_SET)), listener);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            listener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Kanji kanjiItem, int action);
    }

    public void addItem(Kanji item) {
        int index = adapter.kanjiList.size();
        adapter.kanjiList.add(index, item);
        adapter.notifyItemInserted(index);
    }

    public void removeItem(Kanji item) {
        int index = adapter.kanjiList.indexOf(item);
        adapter.kanjiList.remove(index);
        adapter.notifyItemRemoved(index);
    }
}
