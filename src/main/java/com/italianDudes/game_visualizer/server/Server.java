/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.server;

import java.io.File;
import java.io.IOException;

public final class Server {

    public static void start() {
        System.out.println("Hello World! I'm a server.");

        String str = "Paolo";
        String strHash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(str);

        System.out.println(strHash);
    }
}
