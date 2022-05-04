package com.italianDudes.game_visualizer.server.runnables;

import com.italianDudes.game_visualizer.common.Credential;
import com.italianDudes.game_visualizer.common.Defs;
import com.italianDudes.game_visualizer.common.Peer;
import com.italianDudes.game_visualizer.common.Serializer;
import com.italianDudes.game_visualizer.server.lists.ClientListHandler;
import com.italianDudes.game_visualizer.server.lists.PendingListHandler;
import com.italianDudes.game_visualizer.server.lists.RegisteredUserListHandler;

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
            Object buffer;
            switch (preAuthProtocol) {
                case Defs.PREAUTH_PROTOCOL_LOGIN:
                    System.out.println("Attempting to login by ["+peer.getPeerSocket().getInetAddress()+"]");
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
                                new Thread(new PeerHandler(authenticatedPeer)).start();
                            }else{
                                System.out.println("Connection refused to ["+peer.getPeerSocket().getInetAddress()+"]: user not registered");
                            }
                        }else{
                            System.out.println("Connection refused to ["+peer.getPeerSocket().getInetAddress()+"]: username or password are null");
                        }
                    }else{
                        System.out.println("Connection refused to ["+peer.getPeerSocket().getInetAddress()+"]: protocol not respected");
                    }
                    break;

                case Defs.PREAUTH_PROTOCOL_REGISTER:
                    System.out.println("Attempting to register by ["+peer.getPeerSocket().getInetAddress()+"]");
                    try {
                        buffer = Serializer.receiveObject(peer);
                    } catch (ClassNotFoundException | IOException exception) {
                        buffer = null;
                    }
                    if (buffer instanceof Credential) {
                        if (((Credential) buffer).getUsername() != null && ((Credential) buffer).getPassword() != null) {
                            if(!RegisteredUserListHandler.containsUsername(((Credential) buffer).getUsername()) && !PendingListHandler.contains(((Credential) buffer).getUsername())) {
                                try {
                                    Serializer.sendBoolean(peer, PendingListHandler.addUser((Credential) buffer));
                                }catch (IOException ignored){}
                            }else{
                                System.out.println("Connection refused to ["+peer.getPeerSocket().getInetAddress()+"]: user already registered or already in pending list");
                            }
                        }else{
                            System.out.println("Connection refused to ["+peer.getPeerSocket().getInetAddress()+"]: username or password are null");
                        }
                    }else{
                        System.out.println("Connection refused to ["+peer.getPeerSocket().getInetAddress()+"]: protocol not respected");
                    }
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
