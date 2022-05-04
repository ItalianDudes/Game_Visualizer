package com.italianDudes.game_visualizer.server.runnable;

import com.italianDudes.game_visualizer.client.Client;
import com.italianDudes.game_visualizer.common.Credential;
import com.italianDudes.game_visualizer.common.Defs;
import com.italianDudes.game_visualizer.common.Peer;
import com.italianDudes.game_visualizer.common.Serializer;
import com.italianDudes.game_visualizer.server.clientHandler.ClientListHandler;
import com.italianDudes.game_visualizer.server.clientHandler.RegisteredUserListHandler;

import java.io.IOException;
import java.net.Socket;

public class LoginHandler implements Runnable{

    //Attributes
    private final Peer peer;

    //Constructors
    public LoginHandler(Socket socket){
        this.peer = new Peer(socket);
    }

    //Methods
    @Override
    public void run() {

        int preAuthProtocol;
        try {
            preAuthProtocol = Serializer.receiveInt(peer);
        }catch (IOException ioException){
            preAuthProtocol = -1;
            try {
                peer.getPeerSocket().close();
            }catch (IOException ignored){}
        }

        if(preAuthProtocol!=-1) {
            switch (preAuthProtocol) {
                case Defs.PREAUTH_PROTOCOL_LOGIN:
                    Object buffer;
                    try {
                        buffer = Serializer.receiveObject(peer);
                    } catch (ClassNotFoundException | IOException exception) {
                        buffer = null;
                    }
                    if (buffer instanceof Credential) {
                        if (((Credential) buffer).getUsername() != null && ((Credential) buffer).getPassword() != null) {
                            if(RegisteredUserListHandler.contains((Credential) buffer)){
                                Peer authenticatedPeer = new Peer(peer.getPeerSocket(),(Credential) buffer);
                                ClientListHandler.addClient(authenticatedPeer);
                                new Thread(new PeerHandler(peer)).start();
                            }
                        }
                    }
                    break;

                case Defs.PREAUTH_PROTOCOL_REGISTER:
                    //TODO: PREAUTH_PROTOCOL_REGISTER
                    break;

                default:
                    try {
                        peer.getPeerSocket().close();
                    }catch (IOException ignored){}
                    break;
            }
        }

    }
}
