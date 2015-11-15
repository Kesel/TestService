package com.andriy.testservice;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Andriy on 10.11.2015.
 */
public interface AirportsService {

    @GET("/jsontest/timenow/")
    Call<TimeNow> getTimeNow();

    @GET("/places/coords_to_places_ru.json")
    Call<List<Airport>> airports(@Query("coords") String gps);

    @POST("/jsontest/pedometer/")
    Call<Task> createTask(@Body Task task);



}
