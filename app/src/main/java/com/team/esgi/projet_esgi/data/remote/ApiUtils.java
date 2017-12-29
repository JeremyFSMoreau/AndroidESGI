package com.team.esgi.projet_esgi.data.remote;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://api.thetvdb.com";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}