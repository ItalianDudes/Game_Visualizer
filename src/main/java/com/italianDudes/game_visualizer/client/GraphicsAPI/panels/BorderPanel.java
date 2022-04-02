/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.client.GraphicsAPI.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;

public class BorderPanel extends JPanel{
    private BorderLayout layout;
    
    public BorderPanel(int hgap, int vgap){
        layout = new BorderLayout(hgap,vgap);
        
        this.setLayout(layout);
    }
    public BorderPanel(){
        this(0,0);
    }
    
    //Setters
    public void setNorth(Component c){
        this.add(c, BorderLayout.NORTH);
    }
    public void setEast(Component c){
        this.add(c, BorderLayout.EAST);
    }
    public void setSouth(Component c){
        this.add(c,BorderLayout.SOUTH);
    }
    public void setWest(Component c){
        this.add(c, BorderLayout.WEST);
    }
    public void setCenter(Component c){
        this.add(c, BorderLayout.CENTER);
    }
}
