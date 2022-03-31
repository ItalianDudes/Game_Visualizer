package com.italianDudes.game_visualizer.client;

import com.italianDudes.game_visualizer.common.Credential;
import com.italianDudes.game_visualizer.common.Peer;
import com.italianDudes.game_visualizer.common.Prova;
import com.italianDudes.game_visualizer.common.Serializer;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void start() throws IOException, ClassNotFoundException {
        System.out.println("Hello World! I'm a client.");

        Socket serverConnection = new Socket("127.0.0.1",45800);

        int integerNumber;
        long longNumber;
        float floatNumber;
        double doubleNumber;
        boolean booleanNumber;
        String string;
        Object obj;

        Credential userCredential = new Credential("Alessio","1234");

        Peer peer = new Peer(serverConnection,userCredential);

        System.out.println("Integer: "+Serializer.receiveInt(peer,true));
        System.out.println("Long: "+Serializer.receiveLong(peer,true));
        System.out.println("Float: "+Serializer.receiveFloat(peer,true));
        System.out.println("Double: "+Serializer.receiveDouble(peer,true));
        System.out.println("Boolean: "+Serializer.receiveBoolean(peer,true));
        System.out.println("String: \""+Serializer.receiveString(peer,true)+"\"");
        Prova object = (Prova) Serializer.receiveObject(peer,true);
        System.out.println("Object: "+object);

        peer.getPeerSocket().close();
    }
}
