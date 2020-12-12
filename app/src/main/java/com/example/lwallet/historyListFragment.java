package com.example.lwallet;

import android.content.ClipData;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link historyListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class historyListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public historyListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment historyListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static historyListFragment newInstance(String param1, String param2) {
        historyListFragment fragment = new historyListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ListView myListView;
    String[] items;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history_list, container, false);

        Resources res = rootView.getResources();
        myListView = (ListView) rootView.findViewById(R.id.historyView);
        items = res.getStringArray((R.array.historyItems));

        myListView.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.history_list_detail, items));
       // myListView.setAdapter(new ArrayAdapter<ClipData.Item>(getContext(), R.layout.history_list_detail ));

        return rootView;
    }
}