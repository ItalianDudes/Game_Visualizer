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
        //TODO: PeerHandler body (run)
        System.out.println("Connection established with ["+peer.getPeerSocket().getInetAddress()+"]: Welcome \""+peer.getCredential().getUsername()+"\"!");
    }
}
