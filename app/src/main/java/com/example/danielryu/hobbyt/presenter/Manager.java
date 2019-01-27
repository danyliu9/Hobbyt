package com.example.danielryu.hobbyt.presenter;

import com.example.danielryu.hobbyt.view.View;

public abstract class Manager {
    View v;

    public Manager(View v) {
        this.v = v;
    }

}
