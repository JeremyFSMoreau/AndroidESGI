package com.team.esgi.projet_esgi.fragments.connection;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.team.esgi.projet_esgi.R;
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.data.remote.UserServices.UserServices;
import com.team.esgi.projet_esgi.models.KeyValueDB;
import com.team.esgi.projet_esgi.models.User.User;

public class DisconnectionFragment extends Fragment {

    private UserServices mAPIService;
    Context mContext;

    public DisconnectionFragment() {

    }

    public static DisconnectionFragment newInstance() {
        return new DisconnectionFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=this.getActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        SharedPreferences.Editor editor = KeyValueDB.getPrefs(mContext).edit();
        editor.clear().commit();

        return view;
    }



}
