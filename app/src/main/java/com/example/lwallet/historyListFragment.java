package com.example.lwallet;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

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
    Connection cn = new Connection();
    ArrayList<String> placeHolder = new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history_list, container, false);

        //ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getContext(), )

        Resources res = rootView.getResources();
        myListView = (ListView) rootView.findViewById(R.id.historyView);


        cn.getHistory(getActivity().getIntent().getStringExtra("Username"), new Connection.HistoryCallback() {
            @Override
            public void onCallback(ArrayList<String[]> oArr) {
                for(int i = 0; i < oArr.size();i++)
                {
                    placeHolder.add("Amount : " + oArr.get(i)[0] + "\n" + "Date : " +  oArr.get(i)[1] + "\n" + "Status : " +  oArr.get(i)[2]);
                    Log.d("IN ARR IS", cn.inArrr[0] + cn.inArrr[1] + cn.inArrr[2]);
                    Log.d("ORR is : ", oArr.get(i)[0] + "\n" +  oArr.get(i)[1] + "\n" +  oArr.get(i)[2]); // Messy here, something is wrong

                }
                //Log.d("OUTER ARRAY IS ", oArr + ""); // Retrieve data
                myListView.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.history_list_detail, placeHolder));
            }
        });


//        placeHolder.add(cn.oArrr.get(0).get(0) + "\n" +  cn.oArrr.get(0).get(0 + 1) + "\n" +  cn.oArrr.get(0).get(0+2));

        //Intent intent = getActivity().getIntent();



//        Log.d("IN ARRAY IS", cn.inArrr.get(1));
        //Log.d("IN ARRAY IS", cn.oArrr.get(1).get(1));







//        items = new String[placeHolder.size()];
////        items = res.getStringArray(R.array.historyItems);
//
//        for(int i = 0; i < items.length; i++)
//        {
//            items[i] = placeHolder.get(i);
//        }

//        myListView.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.history_list_detail, items));


        return rootView;
    }
}