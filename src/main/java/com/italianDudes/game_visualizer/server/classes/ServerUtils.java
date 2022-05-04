package com.italianDudes.game_visualizer.server.classes;

import com.italianDudes.game_visualizer.common.Defs;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerUtils {

    public static ServerSocket instantiateServerSocketToPort(int port){ //TODO: Test instantiateServerSocketToPort()

        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(Defs.DEFAULT_CONNECTION_TIMEOUT);
            return serverSocket;
        }catch (IllegalArgumentException invalidArgumentException){
            System.err.println("Port out of bounds: 0-65535, provided "+port);
            System.exit(ServerDefs.PORT_OUT_OF_BOUNDS);
        }catch (IOException e){
            System.err.println("Cannot establish a serverSocket on port "+port);
            System.exit(ServerDefs.IMPOSSIBLE_TO_BIND_PORT);
        }
        return null;
    }
}
