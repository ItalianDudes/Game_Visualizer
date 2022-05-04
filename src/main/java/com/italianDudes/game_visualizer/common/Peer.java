/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.common;

import com.italianDudes.game_visualizer.App;
import com.italianDudes.game_visualizer.common.messages.Message;
import com.italianDudes.game_visualizer.common.messages.loginStatus.SuccessfulLogin;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Peer implements Serializable {

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
    public Peer establishConnection(String domain, int port, Credential userCredential) throws IOException, Message {
        return establishConnection(domain,port,Defs.DEFAULT_CONNECTION_TIMEOUT,userCredential);
    }
    public Peer establishConnection(String domain, int port, int timeout, Credential userCredential) throws Message, IOException {
        if(timeout<=0){
            timeout=Defs.DEFAULT_CONNECTION_TIMEOUT;
        }
        InetSocketAddress address = new InetSocketAddress(domain,port);
        Socket connectionSocket = new Socket();
            connectionSocket.setSoTimeout(timeout);
            connectionSocket.connect(address, timeout);

        if(App.isClientSide()) {

            Peer serverConnection = new Peer(connectionSocket, userCredential);
            Serializer.sendObject(serverConnection, userCredential); //Send User Credential for Login
            java.lang.Object result;
            try {
                result = Serializer.receiveObject(serverConnection); //Server's answer to the login request
            } catch (ClassNotFoundException classNotFoundException) {
                connectionSocket.close();
                return null;
            }
            if (result == null) {
                connectionSocket.close();
                return null;
            } else if (result instanceof Message) {
                if (result instanceof SuccessfulLogin) {
                    return serverConnection;
                } else {
                    connectionSocket.close();
                    throw (Message) result;
                }
            }

        }else{

            java.lang.Object userCredentials;
            try {
                userCredentials = Serializer.receiveObject(new Peer(connectionSocket, null));
            }catch (ClassNotFoundException classNotFoundException){
                connectionSocket.close();
                return null;
            }

            if(userCredentials instanceof Credential){
                return new Peer(connectionSocket,credential);
            }else{
                connectionSocket.close();
                return null;
            }

        }
        return null; //Unreachable instruction
    }
    @Override
    public boolean equals(java.lang.Object o) {
        if(!(o instanceof Peer))
            return false;
        Peer client = (Peer) o;
        return client.getCredential().equals(this.getCredential()) && client.peerSocket.equals(this.peerSocket);
    }
}
