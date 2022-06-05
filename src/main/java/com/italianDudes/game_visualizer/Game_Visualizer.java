/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer;

import com.italianDudes.game_visualizer.client.Client;
import java.io.IOException;

public final class Game_Visualizer {

    //Constructors
    private Game_Visualizer(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //Methods
    public static void main(String[] args) throws IOException {
        //The program's initialization is launched
        GVSingleton.getInstance();

        Client.start();
    }
}
