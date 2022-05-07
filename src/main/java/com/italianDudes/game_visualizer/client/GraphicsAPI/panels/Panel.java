package com.italianDudes.game_visualizer.client.GraphicsAPI.panels;

import javax.swing.*;

public class Panel extends JPanel {

    private boolean isDragged;

    private int opacity;

    public Panel(){
        super();
        isDragged=false;
        opacity=100;
    }

    public void setOpacity(int opacity){
        this.opacity=opacity;
    }

    public int getOpacity() {
        return opacity;
    }
}
