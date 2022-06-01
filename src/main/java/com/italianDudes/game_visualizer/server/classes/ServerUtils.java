package com.italianDudes.game_visualizer.server.classes;

import com.italianDudes.game_visualizer.Game_Visualizer;
import com.italianDudes.game_visualizer.server.lists.ClientListHandler;
import com.italianDudes.game_visualizer.server.lists.PendingListHandler;
import com.italianDudes.game_visualizer.server.lists.RegisteredUserListHandler;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

@SuppressWarnings("unused")
public final class ServerUtils {

    //Constructors
    private ServerUtils(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //Methods
    public static ServerSocket instantiateServerSocketToPort(int port){

        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(Game_Visualizer.Defs.DEFAULT_CONNECTION_TIMEOUT);
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

    private static void initServer(){

        File serverDirectory = new File(ServerDefs.SERVER_DIRECTORY_PATH);

        if(!serverDirectory.exists() || !serverDirectory.isDirectory()){
            if(!serverDirectory.mkdir()){
                System.exit(ServerDefs.CANNOT_CREATE_SERVER_DIRECTORY);
            }
        }

        ClientListHandler.initList();
        PendingListHandler.initList();
        PendingListHandler.readPendingUsers();
        RegisteredUserListHandler.initList();
        RegisteredUserListHandler.readRegisteredUsers();
    }
    private static void saveServerLists(){
        PendingListHandler.writePendingUsers();
        RegisteredUserListHandler.writeRegisteredUsers();
    }
}
