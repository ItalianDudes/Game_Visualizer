package com.italianDudes.game_visualizer.client.GraphicsAPI.buttons;

import javax.swing.*;

public final class LauncherButton extends Button {

    public LauncherButton(String text){
        super(text);
        setContentAreaFilled(false);
    }
    public LauncherButton(){
        this("");
    }
}
