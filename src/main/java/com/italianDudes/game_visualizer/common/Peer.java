package com.italianDudes.game_visualizer.common;

import java.net.Socket;

public class Peer {

    //Attributes
    private final Socket peerSocket;
    private final Credential credential;

    //Builder
    public Peer(Socket peerSocket, Credential credential){
        this.peerSocket = peerSocket;
        this.credential = credential;
    }

    //Methods
    public Socket getPeerSocket(){
        return peerSocket;
    }
    public Credential getCredential(){
        return credential;
    }
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Peer))
            return false;
        Peer client = (Peer) o;
        return client.getCredential().equals(this.getCredential()) && client.peerSocket.equals(this.peerSocket);
    }
}
