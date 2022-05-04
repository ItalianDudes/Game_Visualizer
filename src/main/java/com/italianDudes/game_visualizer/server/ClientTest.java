package com.italianDudes.game_visualizer.server;

import com.italianDudes.game_visualizer.common.Credential;
import com.italianDudes.game_visualizer.common.Defs;
import com.italianDudes.game_visualizer.common.Peer;
import com.italianDudes.game_visualizer.common.Serializer;
import com.italianDudes.game_visualizer.common.messages.Message;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientTest {

    public static void start() throws IOException {

        Scanner scan = new Scanner(System.in);

        System.out.print("Inserisci username: ");
        String username = scan.nextLine();
        System.out.print("Inserisci password: ");
        String password = DigestUtils.sha512Hex(scan.nextLine());

        Credential credentials = new Credential(username,password);
        System.out.println(credentials);

        Peer peer = Peer.establishConnection("127.0.0.1",45800,credentials);

        Serializer.sendInt(peer, Defs.PREAUTH_PROTOCOL_LOGIN);
        Serializer.sendObject(peer,credentials);
        scan.nextLine();
        peer.getPeerSocket().close();
    }

}
