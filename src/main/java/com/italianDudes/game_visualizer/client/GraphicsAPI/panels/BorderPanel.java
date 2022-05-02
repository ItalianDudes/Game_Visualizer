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
     * @param hgap it describes how spanned each column is between each other.
     * @param vgap it describes how spanned each row is between each other.
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

    /**
     * This method sets the specified Component to the NORTH position inside the BorderPanel.
     *
     * @param c this is the Component that needs to be positioned at the NORTH section of the BorderPanel.
     */
    public void setNorth(Component c){
        this.add(c, BorderLayout.NORTH);
    }

    /**
     * This method sets the specified component to the EAST position inside the BorderPanel.
     *
     * @param c this is the Component that needs to be positioned at the EAST section of the BorderPanel.
     */
    public void setEast(Component c){
        this.add(c, BorderLayout.EAST);
    }

    /**
     * This method sets the specified component to the SOUTH position inside the BorderPanel.
     *
     * @param c this is the Component that needs to be positioned at the SOUTH section of the BorderPanel.
     */
    public void setSouth(Component c){
        this.add(c,BorderLayout.SOUTH);
    }

    /**
     * This method sets the specified component to the WEST position inside the BorderPanel.
     *
     * @param c this is the Component that needs to be positioned at the WEST section of the BorderPanel.
     */
    public void setWest(Component c){
        this.add(c, BorderLayout.WEST);
    }

    /**
     * This method sets the specified component to the CENTER position inside the BorderPanel.
     *
     * @param c this is the Component that needs to be positioned at the CENTER section of the BorderPanel.
     */
    public void setCenter(Component c){
        this.add(c, BorderLayout.CENTER);
    }
}
