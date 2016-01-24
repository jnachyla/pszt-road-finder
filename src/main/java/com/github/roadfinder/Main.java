package com.github.roadfinder;

import com.github.roadfinder.model.impl.ModelImpl;
import com.github.roadfinder.view.View;

public class Main
{

    public static void main( String[] args )
    {
        ModelImpl.getInstance();
        View.launch( View.class, args );
    }
}
