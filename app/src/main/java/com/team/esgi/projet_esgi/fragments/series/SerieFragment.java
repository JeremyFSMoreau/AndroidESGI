package com.team.esgi.projet_esgi.fragments.series;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.team.esgi.projet_esgi.R;
import com.team.esgi.projet_esgi.data.remote.APIService;
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.models.KeyValueDB;
import com.team.esgi.projet_esgi.models.SearchResult;
import com.team.esgi.projet_esgi.models.Series.Episode;
import com.team.esgi.projet_esgi.models.Series.EpisodesList;
import com.team.esgi.projet_esgi.models.Series.Serie;
import com.team.esgi.projet_esgi.models.User.User;

import java.security.Key;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SerieFragment extends Fragment {

    private APIService mAPIService;
    private User user;
    ListView episodesListe;
    ArrayList<String> initList;
    ArrayAdapter<String> mAdapter;
    Context mContext;
    Serie serie;
    TextView statusSerie;
    TextView firstAiredSerie;
    TextView networkSerie;
    TextView runtimeSerie;
    TextView genreSerie;
    TextView overviewSerie;
    TextView avgSerie;
    TextView countNotesSerie;
    View view;

    TextView nomSerie;

    public SerieFragment() {

    }

    public static SerieFragment newInstance() {
        return new SerieFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=this.getActivity();
        mAPIService = ApiUtils.getAPIService();
        view = inflater.inflate(R.layout.fragment_series_sheet, container, false);

        Gson gson = new Gson();
        String json = KeyValueDB.getUser(mContext);
        user = gson.fromJson(json,User.class);
        String jsonTwo = KeyValueDB.getSerie(mContext);
        serie = gson.fromJson(jsonTwo,Serie.class);

        episodesListe = view.findViewById(R.id.episodesList);

        initList = new ArrayList<String>();
        mAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,initList);
        episodesListe.setAdapter(mAdapter);
        sendGet();




        return view;
    }

    public void sendGet() {
        mAPIService.serieDetails(serie.getId().toString(),"Bearer " + user.getToken()).enqueue(new Callback<Serie>() {
            @Override
            public void onResponse(Call<Serie> call, Response<Serie> response) {
                if(response.isSuccessful()) {
                    serie.setDataSerie(response.body().getDataSerie());
                    fillEpisodes();
                    fillDetails();
                }
                else
                    Log.d("arf","c'est raté");
            }
            @Override
            public void onFailure(Call<Serie> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API." + t.toString());
            }
        });
    }

    public void fillEpisodes()
    {
        mAPIService.episodesList(serie.getId().toString(),"Bearer " + user.getToken()).enqueue(new Callback<EpisodesList>() {
            @Override
            public void onResponse(Call<EpisodesList> call, Response<EpisodesList> response) {
                if(response.isSuccessful()) {
                    EpisodesList episodesList = new EpisodesList();
                    episodesList = response.body();
                    for(Episode episode : response.body().getEpisode())
                    {
                        fillEpisodesListView(episode);
                        Log.d("test", "aaaa" + episode.getEpisodeName());
                    }
                }
                else
                    Log.d("arf","c'est raté");
            }
            @Override
            public void onFailure(Call<EpisodesList> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API." + t.toString());
            }
        });
    }

    public void fillDetails()
    {
        nomSerie = view.findViewById(R.id.nomSerie);
        statusSerie = view.findViewById(R.id.statusSerie);
        firstAiredSerie = view.findViewById(R.id.firstAiredSerie);
        networkSerie= view.findViewById(R.id.networkSerie);
        runtimeSerie = view.findViewById(R.id.runtimeSerie);
        genreSerie = view.findViewById(R.id.genreSerie);
        overviewSerie = view.findViewById(R.id.overviewSerie);
        avgSerie = view.findViewById(R.id.avgSerie);
        countNotesSerie = view.findViewById(R.id.countNotesSerie);

        nomSerie.setText(serie.getDataSerie().getSeriesName());
        statusSerie.setText(serie.getDataSerie().getStatus());
        firstAiredSerie.setText(serie.getDataSerie().getFirstAired());
        networkSerie.setText(serie.getDataSerie().getNetwork());
        runtimeSerie.setText(serie.getDataSerie().getRuntime());
        overviewSerie.setText(serie.getDataSerie().getOverview());
        //avgSerie.setText(String.format("%02d",serie.getDataSerie().getSiteRating()));
        //countNotesSerie.setText(String.format("%02d",serie.getDataSerie().getSiteRatingCount()));
    }

    public void fillEpisodesListView(Episode episode)
    {
        mAdapter.add("S" + episode.getAiredSeason() + "E" + episode.getAiredEpisodeNumber() + " " + episode.getEpisodeName());
    }



}
