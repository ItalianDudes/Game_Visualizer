/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.client.GraphicsAPI.panels;

import java.awt.GridLayout;
import javax.swing.JPanel;

public class GridPanel extends JPanel{
    private GridLayout layout;
    
    public GridPanel(int col, int rows, int hgap, int vgap){
        layout = new GridLayout(col, rows, hgap, vgap);
        
        this.setLayout(layout);
    }
    public GridPanel(int col, int rows){
        this(col,rows,0,0);
    }
    public GridPanel(){
        this(1,1);
    }
}
