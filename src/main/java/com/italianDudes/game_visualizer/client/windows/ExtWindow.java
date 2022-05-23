package com.italianDudes.game_visualizer.client.windows;

import com.italianDudes.game_visualizer.client.GraphicsAPI.buttons.LauncherButton;
import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.BorderPanel;
import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.GridPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExtWindow extends JFrame implements ActionListener {

    private BorderPanel appPanel;        //App Panel
    private BorderPanel launcherPanel;   //Launcher Panel

    private JPanel cTitleBar;       //Custom Title Bar

    private BorderPanel sideBarPanel;    //SideBar Panel

    private GridPanel sideBarBPanel;   //SideBar Button Panel

    private LauncherButton homeB;          //Home Button
    private LauncherButton extB;           //Extensions Button
    private LauncherButton setsB;          //Sets Button

    public ExtWindow(){
        //Panels initialization
        launcherPanel = new BorderPanel();
        appPanel = new BorderPanel();
        cTitleBar = new JPanel();

        launcherPanel.setNorth(cTitleBar);
        launcherPanel.setCenter(appPanel);

        sideBarPanel = new BorderPanel();
        appPanel.setWest(sideBarPanel);

        sideBarBPanel = new GridPanel(3,1,0,10);
        sideBarPanel.setCenter(sideBarBPanel);

        //Initialize JFrame
        this.add(launcherPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0,0,900,600);
        this.setResizable(false);
        this.setTitle("Launcher Window");
        this.setUndecorated(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
