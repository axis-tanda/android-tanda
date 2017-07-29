package com.tanda.hackathon.acss.axis.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tanda.hackathon.acss.axis.R;

public class MeetingFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnMeetingListener mListener;

    public MeetingFragment() {
        // Required empty public constructor
    }

    public static MeetingFragment newInstance(String param1, String param2) {
        MeetingFragment fragment = new MeetingFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meeting, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onMeetingInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMeetingListener) {
            mListener = (OnMeetingListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onStart() {
        super.onStart();
        String[] items = {"AAA", "BBB", "CCC", "DDD", "EEE"};
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        ListView listView = (ListView) getActivity().findViewById(R.id.roomsList);
        listView.setAdapter(itemsAdapter);
    }



    public interface OnMeetingListener {
        // TODO: Update argument type and name
        void onMeetingInteraction(Uri uri);
    }
}
