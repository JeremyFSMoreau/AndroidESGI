package com.team.esgi.projet_esgi.data.remote;

import com.team.esgi.projet_esgi.data.remote.ShowServices.ShowServices;
import com.team.esgi.projet_esgi.data.remote.UserServices.UserServices;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://api.thetvdb.com";

    public static ShowServices getShowService() {

        return RetrofitClient.getClient(BASE_URL).create(ShowServices.class);
    }

    public static UserServices getUserService() {

        return RetrofitClient.getClient(BASE_URL).create(UserServices.class);
    }
}