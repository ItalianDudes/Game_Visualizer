/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.server;

import com.italianDudes.game_visualizer.client.Client;
import com.italianDudes.game_visualizer.server.classes.ServerConfig;
import com.italianDudes.game_visualizer.server.classes.ServerDefs;
import com.italianDudes.game_visualizer.server.classes.ServerUtils;
import com.italianDudes.game_visualizer.server.lists.ClientListHandler;
import com.italianDudes.game_visualizer.server.lists.PendingListHandler;
import com.italianDudes.game_visualizer.server.lists.RegisteredUserListHandler;
import com.italianDudes.game_visualizer.server.runnables.OnlineServer;

import java.io.File;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public final class Server {

    private static File serverDirectory;

    private static void initServer(){
        serverDirectory = new File(ServerDefs.SERVER_DIRECTORY_PATH);

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

    public static void start() { //TODO: interrupt of all started server when called "stop" command

        initServer();

        ServerConfig configs = ServerConfig.readConfig();

        System.out.println(configs);

        ServerSocket serverSocket = ServerUtils.instantiateServerSocketToPort(configs.getPort());

        Thread onlineServer = new Thread(new OnlineServer(serverSocket));
        onlineServer.start();

        System.out.println("Server Status: ONLINE on port "+configs.getPort());

        Scanner scan = new Scanner(System.in);

        String buffer;

        do{
            buffer = scan.nextLine();
        }while (!buffer.equals("stop"));

        onlineServer.interrupt();

        System.out.print("Spegnimento del server in corso");
        System.out.flush();
        while(onlineServer.isAlive()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {}
            System.out.print(".");
            System.out.flush();
        }
        System.out.println();
        System.out.println("Server chiuso!");

    }
}
