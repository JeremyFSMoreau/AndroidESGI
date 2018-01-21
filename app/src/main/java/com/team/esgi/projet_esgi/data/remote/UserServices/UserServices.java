package com.team.esgi.projet_esgi.data.remote.UserServices;

import com.team.esgi.projet_esgi.models.User.Favorite;
import com.team.esgi.projet_esgi.models.User.NoteList;
import com.team.esgi.projet_esgi.models.User.User;
import com.team.esgi.projet_esgi.models.User.UserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserServices {

    @POST("/login")
    Call<User> login(@Body User body);

    @GET("/refresh_token")
    Call<User> refreshToken(@Header("Authorization") String authHeader);

    @GET("/user")
    Call<User> show(@Header("Authorization") String authHeader);

    @GET("/user/favorites")
    Call<UserData> favoritesList(@Header("Authorization") String authHeader);

    @GET("/user/ratings/query?itemType=series")
    Call<NoteList> getNotes(@Header("Authorization") String authHeader);

    @PUT("/user/favorites/{id}")
    Call<UserData> setFavorite(@Path("id") String id, @Header("Authorization") String authHeader);

    @DELETE("/user/ratings/series/{itemId}")
    Call<UserData> setNote(@Path("itemId") String itemId, @Header("Authorization") String authHeader);

    @DELETE("/user/favorites/{id}")
    Call<UserData> unsetFavorite(@Path("id") String id, @Header("Authorization") String authHeader);
}