package com.github.roadfinder;

import com.github.roadfinder.controler.Controller;
import com.github.roadfinder.view.View;

public class Main {

    public static void main(String[] args) {
        View.launch(View.class,args);
        new Controller();
    }
}
