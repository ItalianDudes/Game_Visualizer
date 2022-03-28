package com.italianDudes.game_visualizer;

import com.italianDudes.game_visualizer.client.Client;
import com.italianDudes.game_visualizer.common.Defs;
import com.italianDudes.game_visualizer.server.Server;

public class App {

    public static void main(String[] args) {
        if(args==null || args.length==0){
            Client.start();
        }else if(args[0].equals(Defs.ARGUMENTS_START_AS_SERVER)){
            Server.start();
        }else{
            System.out.println("Error, invalid arguments!");
        }
    }
}
