package com.team.esgi.projet_esgi.fragments.series;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.team.esgi.projet_esgi.R;
import com.team.esgi.projet_esgi.data.remote.APIService;
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.models.KeyValueDB;
import com.team.esgi.projet_esgi.models.Series.Serie;
import com.team.esgi.projet_esgi.models.User.User;

import java.security.Key;

public class SerieFragment extends Fragment {

    private APIService mAPIService;
    private TextView messageConnexion;
    Context mContext;
    Serie serie;

    public SerieFragment() {

    }

    public static SerieFragment newInstance() {
        return new SerieFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=this.getActivity();
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        messageConnexion = view.findViewById(R.id.message_connexion);

        Gson gson = new Gson();
        String json = KeyValueDB.getSerie(mContext);
        serie = gson.fromJson(json,Serie.class);

        messageConnexion.setText(serie.getSeriesName());

        return view;
    }



}
