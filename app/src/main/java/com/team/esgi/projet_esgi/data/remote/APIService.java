package com.team.esgi.projet_esgi.data.remote;

import com.team.esgi.projet_esgi.models.User;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    /*@POST("/login")
    @FormUrlEncoded
    Call<User> savePost(@Field("apikey") String apikey,
                        @Field("userkey") String userkey,
                        @Field("username") String username);*/

    @POST("/login")
    Call<User> login(@Body User body);



}