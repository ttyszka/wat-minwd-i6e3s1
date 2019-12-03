package com.karol.web.controller;

public class VehicleLocalization {

    private String Lat;
    private String Lon;
    private String Time;
    private String Lines;
    private String Brigade;


    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLon() {
        return Lon;
    }

    public void setLon(String lon) {
        Lon = lon;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getLines() {
        return Lines;
    }

    public void setLines(String lines) {
        Lines = lines;
    }

    public String getBrigade() {
        return Brigade;
    }

    public void setBrigade(String brigade) {
        Brigade = brigade;
    }
}
