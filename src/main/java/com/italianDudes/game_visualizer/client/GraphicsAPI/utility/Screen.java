package com.italianDudes.game_visualizer.client.GraphicsAPI.utility;

import java.awt.*;

public class Screen {
    public static int getWidth(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        return size.width;
    }

    public static int getHeight(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        return size.height;
    }

    public static Dimension getResolution(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
