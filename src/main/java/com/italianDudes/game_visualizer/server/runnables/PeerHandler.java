package com.italianDudes.game_visualizer.server.runnables;

import com.italianDudes.game_visualizer.common.Peer;

public class PeerHandler implements Runnable{

    //Attributes
    private final Peer peer;

    //Constructors
    public PeerHandler(Peer peer){
        this.peer = peer;
    }

    //Methods
    @Override
    public void run() {
        System.out.println("Connection established with ["+peer.peerConnectionToString()+"]: Welcome \""+peer.getCredential().getUsername()+"\"!");
        //TODO: invoke extension server program main method
    }
}
