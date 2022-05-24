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
        }

        Object buffer;
        switch (preAuthProtocol) {
            case Defs.PREAUTH_PROTOCOL_LOGIN:
                System.out.println("Attempting to login by ["+peer.peerConnectionToString()+"]");
                try {
                    buffer = Serializer.receiveObject(peer);
                } catch (ClassNotFoundException | IOException exception) {
                    buffer = null;
                }
                if (buffer instanceof Credential) {
                    Credential receivedCredential = (Credential) buffer;
                    if (receivedCredential.getUsername() != null && receivedCredential.getPassword() != null) {
                        if(RegisteredUserListHandler.contains(receivedCredential)){
                            Peer authenticatedPeer = new Peer(peer.getPeerSocket(), receivedCredential);
                            boolean alreadyLogged = !ClientListHandler.addClient(authenticatedPeer);
                            if(alreadyLogged){
                                System.out.println("Connection refused to ["+peer.peerConnectionToString()+"]: user already logged");
                                try {
                                    Serializer.sendBoolean(peer, false);
                                    peer.getPeerSocket().close();
                                }catch (IOException e){
                                    try {
                                        peer.getPeerSocket().close();
                                    }catch (IOException ignored){}
                                }
                            }else {
                                try {
                                    Serializer.sendBoolean(peer, true);
                                    new Thread(new PeerHandler(authenticatedPeer)).start();
                                }catch (IOException e){
                                    System.out.println("Error during send confirmation to ["+peer.peerConnectionToString()+"]");
                                    try {
                                        peer.getPeerSocket().close();
                                    }catch (IOException ignored){}
                                }
                            }
                        }else{
                            System.out.println("Connection refused to ["+peer.peerConnectionToString()+"]: user not registered or invalid username and password");
                            try {
                                Serializer.sendBoolean(peer, false);
                                peer.getPeerSocket().close();
                            }catch (IOException e){
                                try {
                                    peer.getPeerSocket().close();
                                }catch (IOException ignored){}
                            }
                        }
                    }else{
                        System.out.println("Connection refused to ["+peer.peerConnectionToString()+"]: username or password are null");
                        try {
                            Serializer.sendBoolean(peer, false);
                            peer.getPeerSocket().close();
                        }catch (IOException e){
                            try {
                                peer.getPeerSocket().close();
                            }catch (IOException ignored){}
                        }
                    }
                }else{
                    System.out.println("Connection refused to ["+peer.peerConnectionToString()+"]: protocol not respected");
                    try {
                        Serializer.sendBoolean(peer, false);
                        peer.getPeerSocket().close();
                    }catch (IOException e){
                        try {
                            peer.getPeerSocket().close();
                        }catch (IOException ignored){}
                    }
                }
                break;

            case Defs.PREAUTH_PROTOCOL_REGISTER:
                System.out.println("Attempting to register by ["+peer.peerConnectionToString()+"]");
                try {
                    buffer = Serializer.receiveObject(peer);
                } catch (ClassNotFoundException | IOException exception) {
                    buffer = null;
                }
                if (buffer instanceof Credential) {
                    Credential receivedCredential = (Credential) buffer;
                    if (receivedCredential.getUsername() != null && receivedCredential.getPassword() != null) {
                        if(!RegisteredUserListHandler.containsUsername(receivedCredential.getUsername()) && !PendingListHandler.contains(receivedCredential.getUsername())) {
                            try {
                                Serializer.sendBoolean(peer, PendingListHandler.addUser(receivedCredential));
                            }catch (IOException ignored){}
                            System.out.println("User \""+receivedCredential.getUsername()+"\" added to PendingUserList");
                            System.out.println("Connection with ["+peer.peerConnectionToString()+"] will be closed: successful register request");
                        }else{
                            System.out.println("Connection refused to ["+peer.peerConnectionToString()+"]: user already registered or already in pending list");
                            try {
                                Serializer.sendBoolean(peer, false);
                                peer.getPeerSocket().close();
                            }catch (IOException e){
                                try {
                                    peer.getPeerSocket().close();
                                }catch (IOException ignored){}
                            }
                        }
                    }else{
                        System.out.println("Connection refused to ["+peer.peerConnectionToString()+"]: username or password are null");
                        try {
                            Serializer.sendBoolean(peer, false);
                            peer.getPeerSocket().close();
                        }catch (IOException e){
                            try {
                                peer.getPeerSocket().close();
                            }catch (IOException ignored){}
                        }
                    }
                }else{
                    System.out.println("Connection refused to ["+peer.peerConnectionToString()+"]: protocol not respected");
                    try {
                        Serializer.sendBoolean(peer, false);
                        peer.getPeerSocket().close();
                    }catch (IOException e){
                        try {
                            peer.getPeerSocket().close();
                        }catch (IOException ignored){}
                    }
                }
                break;

            default:
                System.out.println("Connection refused to ["+peer.peerConnectionToString()+"]: pre-auth code is invalid");
                try {
                    peer.getPeerSocket().close();
                }catch (IOException ignored){}
                break;
        }

    }
}
