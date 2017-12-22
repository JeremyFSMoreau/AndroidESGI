package com.team.esgi.projet_esgi.fragments.connection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team.esgi.projet_esgi.R;

public class OtherFragment extends Fragment {

    public OtherFragment() {
        // Required empty public constructor
    }

    public static OtherFragment newInstance() {
        return new OtherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other, container, false);


        return view;
    }


}
