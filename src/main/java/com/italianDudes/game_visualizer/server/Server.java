/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.server;

import com.italianDudes.game_visualizer.server.classes.ServerConfig;
import com.italianDudes.game_visualizer.server.classes.ServerDefs;

import java.io.File;

public final class Server {

    public static void start() {

        File serverDirectory = new File(ServerDefs.SERVER_DIRECTORY_PATH);

        if(!serverDirectory.exists() || !serverDirectory.isDirectory()){
            if(!serverDirectory.mkdir()){
                System.exit(ServerDefs.CANNOT_CREATE_SERVER_DIRECTORY);
            }
        }

        ServerConfig configs = ServerConfig.readConfig();

        System.out.println(configs);

        /*

        ServerSocket serverSocket = ServerUtils.instantiateServerSocketToPort(configs.getPort());

        Thread onlineServer = new Thread(new OnlineServer(serverSocket));

        */

    }
}
