package com.italianDudes.game_visualizer.client.windows;

import com.italianDudes.game_visualizer.client.GraphicsAPI.buttons.LauncherButton;
import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.BorderPanel;
import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.GridPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExtWindow extends JFrame implements ActionListener {

    //Frame Dimensions bounds
    private int width;
    private int height;
    private int x;
    private int y;

    private BorderPanel appPanel;        //App Panel
    private BorderPanel launcherPanel;   //Launcher Panel

    private JPanel cTitleBar;       //Custom Title Bar

    private GridPanel sideBarPanel;    //SideBar Panel

    private LauncherButton homeB;          //Home Button
    private LauncherButton extB;           //Extensions Button

    public ExtWindow(int width, int height, int x, int y){
        this.width=width;
        this.height=height;
        this.x=x;
        this.y=y;

        //Panels initialization
        launcherPanel = new BorderPanel();
        appPanel = new BorderPanel();
        cTitleBar = new JPanel();

        launcherPanel.setNorth(cTitleBar);
        launcherPanel.setCenter(appPanel);

        sideBarPanel = new GridPanel(2,1);
        appPanel.setWest(sideBarPanel);

        //Initialize JFrame
        this.add(launcherPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(x,y,width,height);
        this.setResizable(false);
        this.setTitle("Launcher Window");
        this.setUndecorated(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
