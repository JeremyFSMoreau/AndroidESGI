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
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.data.remote.ShowServices.ShowServices;
import com.team.esgi.projet_esgi.models.KeyValueDB;
import com.team.esgi.projet_esgi.models.Actors.Actor;
import com.team.esgi.projet_esgi.models.Actors.ActorsList;
import com.team.esgi.projet_esgi.models.Episodes.Episode;
import com.team.esgi.projet_esgi.models.Episodes.EpisodesList;
import com.team.esgi.projet_esgi.models.Series.Serie;
import com.team.esgi.projet_esgi.models.User.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SerieFragment extends Fragment {

    private ShowServices mAPIService;
    private User user;
    ListView episodesListe;
    ListView actorsListe;
    ArrayList<String> initListEpisodes;
    ArrayList<String> initListActors;
    ArrayAdapter<String> mAdapterEpisodes;
    ArrayAdapter<String> mAdapterActors;
    Context mContext;
    Serie serie;
    @BindView(R.id.statusSerie) TextView statusSerie;
    @BindView(R.id.firstAiredSerie) TextView firstAiredSerie;
    @BindView(R.id.networkSerie) TextView networkSerie;
    @BindView(R.id.runtimeSerie) TextView runtimeSerie;
    @BindView(R.id.genreSerie) TextView genreSerie;
    @BindView(R.id.overviewSerie) TextView overviewSerie;
    @BindView(R.id.avgSerie) TextView avgSerie;
    @BindView(R.id.countNotesSerie) TextView countNotesSerie;
    @BindView(R.id.nomSerie) TextView nomSerie;

    public SerieFragment() {

    }

    public static SerieFragment newInstance() {
        return new SerieFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=this.getActivity();
        mAPIService = ApiUtils.getShowService();
        View view = inflater.inflate(R.layout.fragment_series_sheet, container, false);
        ButterKnife.bind(this, view);

        Gson gson = new Gson();
        String json = KeyValueDB.getUser(mContext);
        user = gson.fromJson(json,User.class);
        String jsonTwo = KeyValueDB.getSerie(mContext);
        serie = gson.fromJson(jsonTwo,Serie.class);

        episodesListe = view.findViewById(R.id.episodesList);
        actorsListe = view.findViewById(R.id.actorsList);

        initListEpisodes = new ArrayList<String>();
        initListActors = new ArrayList<String>();
        mAdapterEpisodes = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,initListEpisodes);
        mAdapterActors = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,initListActors);
        episodesListe.setAdapter(mAdapterEpisodes);
        actorsListe.setAdapter(mAdapterActors);
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
                    fillActors();
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

    public void fillActors()
    {
        mAPIService.actorsList(serie.getId().toString(),"Bearer " + user.getToken()).enqueue(new Callback<ActorsList>() {
            @Override
            public void onResponse(Call<ActorsList> call, Response<ActorsList> response) {
                if(response.isSuccessful()) {
                    ActorsList actorsList = new ActorsList();
                    actorsList = response.body();
                    for(Actor actor : response.body().getActor())
                    {
                        fillActorsListView(actor);
                        Log.d("test", "aaaa" + actor.getName());
                    }
                }
                else
                    Log.d("arf","c'est raté");
            }
            @Override
            public void onFailure(Call<ActorsList> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API." + t.toString());
            }
        });
    }

    public void fillDetails()
    {
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
        mAdapterEpisodes.add("S" + episode.getAiredSeason() + "E" + episode.getAiredEpisodeNumber() + " " + episode.getEpisodeName());
    }

    public void fillActorsListView(Actor actor)
    {
        mAdapterActors.add(actor.getName() + " \n AS \n " + actor.getRole());
    }



}
