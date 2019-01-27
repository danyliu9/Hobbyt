package com.example.danielryu.hobbyt.model;

public enum Interest {
    FITNESS("Fitness"), NATURE("Nature"), ENTERTAINMENT("Entertainment");
    // Private to avoid unnecessary instantiation
    private final String name;
    private Interest(String name) {
        this.name = name;
    }
}
