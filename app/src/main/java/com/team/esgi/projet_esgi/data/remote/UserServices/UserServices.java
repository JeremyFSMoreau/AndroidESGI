package com.team.esgi.projet_esgi.data.remote.UserServices;

import com.team.esgi.projet_esgi.models.User.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserServices {

    @POST("/login")
    Call<User> login(@Body User body);

    @GET("/user")
    Call<User> show(@Header("Authorization") String authHeader);

}