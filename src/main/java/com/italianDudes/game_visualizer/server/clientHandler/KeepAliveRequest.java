package com.italianDudes.game_visualizer.server.clientHandler;

import com.italianDudes.game_visualizer.common.StringHandler;
import com.italianDudes.game_visualizer.server.classes.Client;

import java.io.IOException;

public class KeepAliveRequest implements Runnable{

    public String actualKeepAlive;
    private final Client client;

    //Builders
    public KeepAliveRequest(Client client){
        this.client = client;
        actualKeepAlive = null;
    }


    @Override
    public void run() {
        try {
            actualKeepAlive = StringHandler.receiveString(client.getClientSocket().getInputStream());
        }catch (IOException ignored){}
    }
}
