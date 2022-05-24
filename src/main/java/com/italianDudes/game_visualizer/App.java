/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer;

import com.italianDudes.game_visualizer.client.Client;
import java.io.IOException;

public final class App {

    //Constructors
    private App(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //Methods
    public static void main(String[] args) throws IOException {
        Client.start();
    }
}
