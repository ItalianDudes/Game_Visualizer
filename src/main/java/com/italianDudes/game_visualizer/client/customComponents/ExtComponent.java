package com.italianDudes.game_visualizer.client.customComponents;

import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.GridPanel;
import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.Panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public final class ExtComponent extends Panel {

    private final static int HEIGHT = 20;
    private final static int WIDTH = 30;
    private final static int X = 0;
    private final static int Y = 0;

    private final static int MAIN_HGAP = 10;
    private final static int BORDER_SPAN = 10;

    private String extName;
    private String extAuth;
    private String extDate;

    public ExtComponent(String extName, String extAuth, String extDate, Image img){
        this.extName=extName;
        this.extAuth=extAuth;
        this.extDate=extDate;

        GridPanel mainPanel = new GridPanel(1,1,MAIN_HGAP,0);
        mainPanel.setBorder(new EmptyBorder(BORDER_SPAN,BORDER_SPAN,BORDER_SPAN,BORDER_SPAN));
        GridPanel namePanel = new GridPanel(2,1);
        GridPanel authDatePanel = new GridPanel(1,2);

        JLabel extNameL = new JLabel(extName);
        extNameL.setToolTipText(extName);
        JLabel extAuthL = new JLabel(extAuth);
        extAuthL.setToolTipText(extAuth);
        JLabel extDateL = new JLabel(extDate);
        extDateL.setToolTipText(extDate);

        //ImagePanel imgPanel = new ImagePanel(img,HEIGHT-BORDER_SPAN,WIDTH-(BORDER_SPAN+MAIN_HGAP),X+BORDER_SPAN,Y+BORDER_SPAN);

        authDatePanel.add(extAuthL,extDateL);
        namePanel.add(extNameL,authDatePanel);
        mainPanel.add(namePanel);

        this.add(mainPanel);
    }

    @Override
    public String toString(){
        return extName;
    }

    public String getExtAuth(){
        return extAuth;
    }
    public String getExtDate(){
        return extDate;
    }
}
