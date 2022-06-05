package com.italianDudes.game_visualizer.client.windows;

import com.italianDudes.game_visualizer.client.GraphicsAPI.buttons.LauncherButton;
import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.BorderPanel;
import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.GridPanel;
import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.Panel;
import com.italianDudes.game_visualizer.client.customComponents.ExtComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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
    private BorderPanel centerPanel;    //Center Panel

    private JList<ExtComponent> extList;    //Extensions' List
    private GridPanel extInfoPanel;     //Extensions' Info Panel

    private LauncherButton homeB;          //Home Button
    private LauncherButton extB;           //Extensions Button

    private JLabel extNumL;         //Extensions' Number Label
    private JLabel authL;           //Author's Label
    private JLabel dateL;           //Date's Label

    public ExtWindow(int width, int height, int x, int y){
        ExtComponent extComponent;

        this.width=width;
        this.height=height;
        this.x=x;
        this.y=y;

        //Panels initialization
        launcherPanel = new BorderPanel();
        appPanel = new BorderPanel();
        cTitleBar = new JPanel();
        centerPanel = new BorderPanel();
        extInfoPanel = new GridPanel(1,3);

        sideBarPanel = new GridPanel(2,1, 0, 10);
        sideBarPanel.setBorder(new EmptyBorder(200,10,200,10));

        //Buttons initialization
        homeB = new LauncherButton("Home");
        homeB.addActionListener(this);
        extB = new LauncherButton("Extensions");
        extB.addActionListener(this);

        //List initialization
        extList = new JList<>();
        DefaultListModel<ExtComponent> defaultListModel = new DefaultListModel<>();

        extList.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        extComponent = new ExtComponent("Prova1","Autore1","1/1/1900",null);
        extComponent.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        //extList.add(extComponent);
        ExtComponent l1 = new ExtComponent("Test1","Autore1","1/1/1",null);
        ExtComponent l2 = new ExtComponent("Test2","Autore2","2/2/2",null);

        defaultListModel.addElement(l1);
        defaultListModel.addElement(l2);

        extList.setModel(defaultListModel);
        //Labels' initialization
        extNumL = new JLabel("2");

        //Components manipulation step
        extInfoPanel.add(extNumL);

        centerPanel.setNorth(extInfoPanel);
        centerPanel.setCenter(extList);

        launcherPanel.setNorth(cTitleBar);
        launcherPanel.setCenter(appPanel);

        sideBarPanel.add(homeB);
        sideBarPanel.add(extB);

        appPanel.setWest(sideBarPanel);
        appPanel.setCenter(centerPanel);

        //Initialize JFrame
        this.add(launcherPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(x,y,width,height);
        this.setResizable(false);
        this.setTitle("Launcher Window - Extensions");
        this.setUndecorated(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == homeB){
            this.dispose();
            LauncherWindow launcherWindow = new LauncherWindow(width, height, getX(), getY());
            launcherWindow.setVisible(true);
        }
    }
}
