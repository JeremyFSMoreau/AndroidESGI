package com.team.esgi.projet_esgi.data.remote.ShowServices;

import com.team.esgi.projet_esgi.models.SearchResult;
import com.team.esgi.projet_esgi.models.Series.EpisodesList;
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

}