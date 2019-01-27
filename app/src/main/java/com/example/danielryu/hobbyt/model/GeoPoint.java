package com.example.danielryu.hobbyt.model;

public class GeoPoint {
    private double lat;
    private double lon;

    // EFFECTS: constructs geo-point at given latitude (lat) and longitude (lon)
    public GeoPoint(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLatitude() {
        return lat;
    }
    public double getLongitude() {
        return lon;
    }
}
