/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.client.GraphicsAPI.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;

/**
 * BorderPanel is a class that generically unites the border layout and a panel.
 * It extends the javax.swing.JPanel class.
 *
 * @author SerSapessi
 * @version 1.0
 * @since 2022
 */
public class BorderPanel extends JPanel{
    private BorderLayout layout;

    /**
     * This is a constructor of the BorderPanel class.
     *
     * @param hgap  it describes how spanned each column is between each other.
     * @param vgap  it describes how spanned each row is between each other.
     */
    public BorderPanel(int hgap, int vgap){
        layout = new BorderLayout(hgap,vgap);
        
        this.setLayout(layout);
    }

    /**
     * This is a constructor of the BorderPanel class.
     * It simply has 0 hgap and 0 vgap.
     */
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
