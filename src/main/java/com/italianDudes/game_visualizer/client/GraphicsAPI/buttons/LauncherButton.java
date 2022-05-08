package com.italianDudes.game_visualizer.client.GraphicsAPI.buttons;

import java.awt.*;

public final class LauncherButton extends Button {

    public LauncherButton(String text){
        super(text);

        setColor(Color.GRAY);
        setBorderColor(Color.BLACK);
        setColorClick(Color.DARK_GRAY);
        setColorOver(Color.LIGHT_GRAY);
        setRadius(40);
        setContentAreaFilled(false);
    }
    public LauncherButton(){
        this("");
    }
}
