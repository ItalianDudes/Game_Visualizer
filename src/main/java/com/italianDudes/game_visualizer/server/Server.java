package com.italianDudes.game_visualizer.server;

import com.italianDudes.game_visualizer.common.Credential;
import com.italianDudes.game_visualizer.common.Peer;
import com.italianDudes.game_visualizer.common.Prova;
import com.italianDudes.game_visualizer.common.Serializer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public final class Server {

    public static void start() throws IOException {
        System.out.println("Hello World! I'm a server.");

        ServerSocket serverSocket = new ServerSocket(45800);
        Socket clientConnection = serverSocket.accept();

        int integerNumber = 15;
        long longNumber = 1456153;
        float floatNumber = 4125.14f;
        double doubleNumber = 41235.14532521532;
        boolean booleanNumber = true;
        String string = "PAOLO";
        Object obj = new Prova(14,4124.14215);

        Credential userCredential = new Credential("Alessio","1234");

        Peer client = new Peer(clientConnection,userCredential);

        Serializer.sendInt(client,integerNumber,true);
        Serializer.sendLong(client,longNumber,true);
        Serializer.sendFloat(client,floatNumber,true);
        Serializer.sendDouble(client,doubleNumber,true);
        Serializer.sendBoolean(client,booleanNumber,true);
        Serializer.sendString(client,string,true);
        Serializer.sendObject(client,obj,true);

        client.getPeerSocket().close();


    }
}
