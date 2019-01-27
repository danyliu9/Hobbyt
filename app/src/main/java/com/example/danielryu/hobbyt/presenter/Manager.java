package com.example.danielryu.hobbyt.presenter;

import com.example.danielryu.hobbyt.view.View;

public abstract class Manager {
    static View v;

    public static void setView(View v1) {
        v = v1;
    }

}
