package com.italianDudes.game_visualizer.client.GraphicsAPI.panels;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private boolean isDragged;

    private int green;
    private int red;
    private int blue;
    private int opacity;    //To be considered only if opaque is not set to true

    public Panel(){
        super();

        isDragged=false;
        opacity=100;
        green=255;
        red=255;
        blue=255;
        this.setOpaque(false);
    }

    public void setOpacity(int opacity){
        this.opacity=opacity;
    }

    public int getOpacity() {
        return opacity;
    }

    public void setBackgroundColor(int red, int green, int blue){
        this.red=red;
        this.green=green;
        this.blue=blue;
    }

    @Override
    public void paintComponent(Graphics g){
        if(!this.isOpaque()){
            g.setColor(new Color(red,green,blue,opacity));
            Rectangle r = g.getClipBounds();
            g.fillRect(r.x,r.y,r.width,r.height);
        }
        super.paintComponent(g);
    }
}
