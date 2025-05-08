package com.example.tele_weather;

import com.example.tele_weather.bean.LocationModel;
import com.example.tele_weather.bean.PronosticoModel;
import com.example.tele_weather.bean.SportModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("v1/search.json?key=ec24b1c6dd8a4d528c1205500250305")
    Call<List<LocationModel>> getLocations(@Query("q") String location);

    @GET("v1/forecast.json?key=ec24b1c6dd8a4d528c1205500250305")
    Call<PronosticoModel> getForecast(@Query("q") String idLocation, @Query("days") int days);

    @GET("v1/sports.json?key=ec24b1c6dd8a4d528c1205500250305")
    Call<SportModel> getFootballMatches(@Query("q") String location);

}
