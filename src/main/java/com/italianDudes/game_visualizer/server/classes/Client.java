package com.italianDudes.game_visualizer.server.classes;

import com.italianDudes.game_visualizer.common.Credential;

import java.net.Socket;

public class Client {

    //Attributes
    private final Socket clientSocket;
    private final Credential credential;

    //Builder
    public Client(Socket clientSocket, Credential credential){
        this.clientSocket = clientSocket;
        this.credential = credential;
    }

    //Methods
    public Socket getClientSocket(){
        return clientSocket;
    }
    public Credential getCredential(){
        return credential;
    }
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Client))
            return false;
        Client client = (Client) o;
        return client.getCredential().equals(this.getCredential()) && client.clientSocket.equals(this.clientSocket);
    }
}
