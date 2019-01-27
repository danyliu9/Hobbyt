package com.example.danielryu.hobbyt.model;

import java.net.URL;

public class ContactInfo {
    private String address;
    private GeoPoint geoLocation;
    private URL webAddress;
    private String phoneNumber;

    public ContactInfo(String address, GeoPoint geoLocation, URL webAddress, String phoneNumber) {
        this.address = address;
        this.geoLocation = geoLocation;
        this.webAddress = webAddress;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }
    public GeoPoint getGeoLocation() {
        return geoLocation;
    }
    public URL getWebAddress() {
        return webAddress;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
