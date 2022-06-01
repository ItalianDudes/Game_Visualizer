/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.common;


import com.italianDudes.game_visualizer.Game_Visualizer;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

@SuppressWarnings("unused")
public class Peer implements Serializable {

    //Attributes
    private final Socket peerSocket;
    private final Credential credential;

    //Builder
    public Peer(Socket peerSocket, Credential credential){
        this.peerSocket = peerSocket;
        this.credential = credential;
    }
    public Peer(Socket peerSocket){
        this.peerSocket = peerSocket;
        this.credential = null;
    }

    //Methods
    public Socket getPeerSocket(){
        return peerSocket;
    }
    public Credential getCredential(){
        return credential;
    }
    public static Peer establishConnection(String domain, int port, Credential userCredential) throws IOException {
        return establishConnection(domain,port, Game_Visualizer.Defs.DEFAULT_CONNECTION_TIMEOUT,userCredential);
    }
    public static Peer establishConnection(String domain, int port, int timeout, Credential userCredential) throws IOException {
        if(timeout<=0){
            timeout= Game_Visualizer.Defs.DEFAULT_CONNECTION_TIMEOUT;
        }
        InetSocketAddress address = new InetSocketAddress(domain,port);
        Socket connectionSocket = new Socket();
            connectionSocket.setSoTimeout(timeout);
            connectionSocket.connect(address, timeout);

        return new Peer(connectionSocket, userCredential);
    }
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Peer))
            return false;
        Peer client = (Peer) o;
        return client.getCredential().equals(this.getCredential()) && client.peerSocket.equals(this.peerSocket);
    }

    @Override
    public String toString(){
        String username = credential!=null?credential.getUsername():null;
        return "Username: "+username+"\nSocket: ["+peerSocket.getInetAddress()+":"+peerSocket.getPort()+"]";
    }

    public String peerConnectionToString(){
        return peerSocket.getInetAddress()+":"+peerSocket.getPort();
    }

}
