package com.italianDudes.game_visualizer.client.GraphicsAPI.buttons;

import java.awt.*;

public final class LauncherButton extends Button{

    private final static int fontSize = 14;
    public LauncherButton(String text){
        super(text);

        setColor(Color.GRAY);
        setBorderColor(Color.GRAY);
        setColorClick(Color.DARK_GRAY);
        setColorOver(Color.LIGHT_GRAY);
        setRadius(50);
        setContentAreaFilled(false);
    }
    public LauncherButton(){
        this("");
    }
}
