/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.client.GraphicsAPI.panels;

import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * GridPanel is a class that generically unites the grid layout and a panel.
 * It extends the javax.swing.JPanel class.
 *
 * @author SerSapessi
 * @version 1.0
 * @since 2022
 */
public class GridPanel extends JPanel{
    private GridLayout layout;
    
    public GridPanel(int rows, int col, int hgap, int vgap){
        layout = new GridLayout(rows, col, hgap, vgap);
        
        this.setLayout(layout);
    }
    public GridPanel(int rows, int col){
        this(rows,col,0,0);
    }
    public GridPanel(){
        this(1,1);
    }
}
