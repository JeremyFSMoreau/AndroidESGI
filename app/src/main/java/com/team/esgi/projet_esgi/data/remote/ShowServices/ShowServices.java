package com.team.esgi.projet_esgi.data.remote.ShowServices;

import com.team.esgi.projet_esgi.models.Series.LastUpdated;
import com.team.esgi.projet_esgi.models.Series.SearchResult;
import com.team.esgi.projet_esgi.models.Actors.ActorsList;
import com.team.esgi.projet_esgi.models.Episodes.EpisodesList;
import com.team.esgi.projet_esgi.models.Series.Serie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ShowServices {

    @GET("/search/series?")
    Call<SearchResult> list(@Query("name") String name, @Header("Authorization") String authHeader);

    @GET("/series/{id}")
    Call<Serie> serieDetails(@Path("id") String name, @Header("Authorization") String authHeader);

    @GET("/series/{id}/episodes")
    Call<EpisodesList> episodesList(@Path("id") String name, @Header("Authorization") String authHeader);

    @GET("/series/{id}/actors")
    Call<ActorsList> actorsList(@Path("id") String name, @Header("Authorization") String authHeader);

    @GET("/updated/query")
    Call<LastUpdated> updatedSeries(@Query("fromTime") String fromTime, @Header("Authorization") String authHeader);

}