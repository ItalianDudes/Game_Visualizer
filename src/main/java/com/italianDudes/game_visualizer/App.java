/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer;

import com.italianDudes.game_visualizer.client.Client;
import com.italianDudes.game_visualizer.common.Defs;
import com.italianDudes.game_visualizer.server.Server;

import java.io.IOException;

public class App {

    private static boolean clientSide;
    public static void main(String[] args) {
        if(args==null || args.length==0){
            clientSide=true;
            Client.start();
        }else if(args[0].equals(Defs.ARGUMENTS_START_AS_SERVER)){
            clientSide=false;
            Server.start();
        }else{
            System.err.println("Error, invalid arguments!");
        }
    }

    public static boolean isClientSide(){
        return clientSide;
    }
    public static boolean isServerSide(){
        return !clientSide;
    }
}
