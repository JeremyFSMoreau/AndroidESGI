package com.team.esgi.projet_esgi.fragments.connection;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team.esgi.projet_esgi.R;
import com.team.esgi.projet_esgi.data.remote.APIService;
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.models.KeyValueDB;

public class OtherFragment extends Fragment {

    private APIService mAPIService;
    private TextView messageConnexion;
    Context mContext;

    public OtherFragment() {

    }

    public static OtherFragment newInstance() {
        return new OtherFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=this.getActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        messageConnexion = view.findViewById(R.id.message_connexion);
        messageConnexion.setText(KeyValueDB.getUsername(mContext));
        mAPIService = ApiUtils.getAPIService();

        return view;
    }



}