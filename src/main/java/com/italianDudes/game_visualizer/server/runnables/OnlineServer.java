package com.italianDudes.game_visualizer.server.runnables;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class OnlineServer implements Runnable{

    //Attributes
    private final ServerSocket serverSocket;

    //Constructors
    public OnlineServer(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    //Methods
    @Override
    public void run() {

        boolean interruptRequest = false;
        Socket peerSocket;

        while(!interruptRequest) {

            peerSocket = null;

            if(Thread.currentThread().isInterrupted()){
                interruptRequest = true;
            }else {

                try {
                    peerSocket = serverSocket.accept();
                    System.out.println("Attempting connection by ["+peerSocket.getInetAddress()+":"+peerSocket.getPort()+"]");
                } catch (SocketTimeoutException ignored){
                } catch (IOException exception) {
                    System.err.println("An error has occurred, connection with peer is terminated");
                }

                //Authenticate User
                if(peerSocket!=null)
                    new Thread(new LoginHandler(peerSocket)).start();

            }

        }

        try {
            serverSocket.close();
        } catch (IOException ioException) {
            System.err.println("An error has occurred during server socket closing!");
        }

    }

}
