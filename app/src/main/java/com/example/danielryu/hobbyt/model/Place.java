package com.example.danielryu.hobbyt.model;

import java.util.HashSet;
import java.util.Set;

public class Place {
    private String name;
    private ContactInfo contactInfo;
    private Set<Interest> interests;
    private Set<Weather> weatherSet;

    public Place(String name, ContactInfo contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        interests = new HashSet<Interest>();
        weatherSet= new HashSet<Weather>();
    }

    // Getters:
    public String getName() { return name; }
    public ContactInfo getContactInfo() { return contactInfo; }
    public Set<Interest> getInterests() { return interests; }
    public Set<Weather> getWeatherSet() { return weatherSet; }

    public void addInterest(Interest newInterest) {
        interests.add(newInterest);
    }

    public void addWeather(Weather newWeather) {
        weatherSet.add(newWeather);
    }
}
