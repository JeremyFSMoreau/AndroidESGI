package com.team.esgi.projet_esgi.data.remote;

import com.team.esgi.projet_esgi.models.SearchResult;
import com.team.esgi.projet_esgi.models.Episodes.EpisodesList;
import com.team.esgi.projet_esgi.models.Series.Serie;
import com.team.esgi.projet_esgi.models.User.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    /*@POST("/login")
    @FormUrlEncoded
    Call<User> savePost(@Field("apikey") String apikey,
                        @Field("userkey") String userkey,
                        @Field("username") String username);*/

    @POST("/login")
    Call<User> login(@Body User body);

    @GET("/user")
    Call<User> show(@Header("Authorization") String authHeader);

    @GET("/search/series?")
    Call<SearchResult> list(@Query("name") String name, @Header("Authorization") String authHeader);

    @GET("/series/{id}")
    Call<Serie> serieDetails(@Path("id") String name, @Header("Authorization") String authHeader);

    @GET("/series/{id}/episodes")
    Call<EpisodesList> episodesList(@Path("id") String name, @Header("Authorization") String authHeader);

    @GET("/series/{id}/actors")
    Call<Serie> actorsList(@Path("id") String name, @Header("Authorization") String authHeader);

}