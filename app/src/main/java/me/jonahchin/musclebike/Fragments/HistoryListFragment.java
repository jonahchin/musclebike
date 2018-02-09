package me.jonahchin.musclebike.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.jonahchin.musclebike.Adapters.HistoryListAdapter;
import me.jonahchin.musclebike.Entities.Ride;
import me.jonahchin.musclebike.Interfaces.HistoryListCallbacks;
import me.jonahchin.musclebike.R;

/**
 * Created by jonahchin on 2017-11-14.
 */

public class HistoryListFragment extends Fragment {
    private static final String TAG = "HistoryListFrag";
    private RecyclerView mHistoryRecyclerView;
    private HistoryListAdapter mHistoryAdapter;
    private TextView mTitle;

    private HistoryListCallbacks mCallbacks;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_list, container, false);

        Log.e(TAG, "Creating view for history frag");

        mTitle = view.findViewById(R.id.title_bar_title);
        mTitle.setText("History");

        mHistoryRecyclerView = view.findViewById(R.id.history_recycler_view);
        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final List<Ride> rideList = new ArrayList<>();

        for(int i = 0; i < 100; i++){
            rideList.add(new Ride(10*i, 10*i+55, new Date(System.currentTimeMillis()), 60.9));
        }


        if(mHistoryAdapter == null){
            mHistoryAdapter = new HistoryListAdapter(rideList);
        }

        mHistoryAdapter.setClickListener(new HistoryListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mCallbacks.onListItemClick(rideList.get(position));
            }
        });



        mHistoryRecyclerView.setAdapter(mHistoryAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mCallbacks = (HistoryListCallbacks) context;
        } catch (ClassCastException e) {
            Log.e(TAG, context.toString() + " must implement HistoryListCallbacks");
        }
    }
}
