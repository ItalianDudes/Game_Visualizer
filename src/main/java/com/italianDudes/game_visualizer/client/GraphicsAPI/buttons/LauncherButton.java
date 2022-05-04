package com.italianDudes.game_visualizer.client.GraphicsAPI.buttons;

import javax.swing.*;

public class LauncherButton extends JButton {

    public LauncherButton(String text){
        super(text);
        setContentAreaFilled(false);
    }
    public LauncherButton(){
        this("");
    }
}
