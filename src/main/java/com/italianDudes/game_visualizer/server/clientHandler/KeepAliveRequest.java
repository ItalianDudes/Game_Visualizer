/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.server.clientHandler;

import com.italianDudes.game_visualizer.common.Serializer;
import com.italianDudes.game_visualizer.common.Peer;

import java.io.IOException;

public class KeepAliveRequest implements Runnable{

    public String actualKeepAlive;
    private final Peer client;

    //Builders
    public KeepAliveRequest(Peer client){
        this.client = client;
        actualKeepAlive = null;
    }


    @Override
    public void run() {
        try {
            actualKeepAlive = Serializer.receiveString(client);
        }catch (IOException ignored){}
    }
}
