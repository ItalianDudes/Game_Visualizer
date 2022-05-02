/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.client;

import com.italianDudes.game_visualizer.client.windows.LauncherWindow;
import com.italianDudes.game_visualizer.common.Defs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Client is the class used to set the launcher window visible. That's it's only purpose.
 *
 * @author SerSapessi, Hacker6329
 * @version 1.0
 * @since 2022
 */
public class Client {
    public static void start(){
        LauncherWindow lWindow = new LauncherWindow();

        lWindow.setVisible(true);
    }
}
