package com.andriy.testservice;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andriy on 10.11.2015.
 */
public class Airport {

    @SerializedName("iata")
    private String mIata;

    @SerializedName("name")
    private String mName;

    @SerializedName("airport_name")
    private String mAirportName;

    public Airport() {
    }
}
