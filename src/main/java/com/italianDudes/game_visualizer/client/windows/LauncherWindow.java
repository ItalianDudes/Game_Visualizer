/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.client.windows;

import com.italianDudes.game_visualizer.client.GraphicsAPI.buttons.LauncherButton;
import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * LauncherWindow is the class used to build the graphical layout of the launcher window.
 * It extends the javax.swing.JFrame class and implements the java.awt.event.ActionListener interface.
 *
 * @author SerSapessi
 * @version 1.0
 * @since 2022
 */
public class LauncherWindow extends JFrame implements ActionListener{

    //Frame Dimensions bounds
    private int width;
    private int height;
    private int x;
    private int y;

    //Panels
    private JPanel cTitleBar;       //Custom Title Bar
    private GridPanel centralOptPanel;    //Central Option Panel
    private GridPanel accountPanel;    //Account Panel
    private GridPanel accountTextPanel;
    private GridPanel setPanel;
    private JPanel optionPanel;     //Option Panel
    private BorderPanel supportivePanel; //Supportive Panel

    private GridPanel sideBarPanel;    //SideBar Panel
    
    private BorderPanel appPanel;        //App Panel
    private BorderPanel launcherPanel;   //Launcher Panel
    
    //Buttons
    private LauncherButton launchB;        //Launch Button
    private LauncherButton optB;           //Option Button #TODO: da implementare con icona
    private LauncherButton cAccountB;      //Change Account Button
    private LauncherButton homeB;          //Home Button
    private LauncherButton extB;           //Extensions Button
    
    //Labels
    private JLabel accountL;        //Account Label
    private JLabel setL;            //Set Label
    
    //Text Fields
    private JTextField accountTF;   //Account Text Field
    
    //Combo Boxes
    private JComboBox<String> setsDisplayedCB;  //Sets Displayed Combo Box

    public LauncherWindow(int width, int height, int x, int y){
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

        sideBarPanel = new GridPanel(2,1,0,10);
        sideBarPanel.setBorder(new EmptyBorder(200,10,200,10));
        supportivePanel = new BorderPanel();
        appPanel.setWest(sideBarPanel);
        appPanel.setCenter(supportivePanel);
        
        optionPanel = new JPanel();
        optionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        centralOptPanel = new GridPanel(2,1);
        centralOptPanel.setBorder(new EmptyBorder(250,200,250,200));
        setPanel = new GridPanel(1,2);
        supportivePanel.setCenter(centralOptPanel);
        supportivePanel.setSouth(optionPanel);

        accountPanel = new GridPanel(2, 1);
        accountTextPanel = new GridPanel(1,2);
        
        //Buttons initialization
        launchB = new LauncherButton("AVVIA");     //#TODO: usare la traduzione automatica
        launchB.addActionListener(this);
        cAccountB = new LauncherButton("Change Account");
        cAccountB.addActionListener(this);
        homeB = new LauncherButton("Home");
        homeB.addActionListener(this);
        extB = new LauncherButton("Extensions");
        extB.addActionListener(this);

        centralOptPanel.add(launchB);
        centralOptPanel.add(setPanel);
        sideBarPanel.add(homeB);
        sideBarPanel.add(extB);
        
        //Labels initialization
        setL = new JLabel("Sets:");         //#TODO: usare la traduzione automatica
        accountL = new JLabel("Account: ");
        
        setPanel.add(setL);
        accountTextPanel.add(accountL);

        //Combo Box initialization
        setsDisplayedCB = new JComboBox<>();

        setPanel.add(setsDisplayedCB);

        //TextFields initialization
        accountTF = new JTextField("<insert account>");
        accountTextPanel.add(accountTF);

        accountPanel.add(accountTextPanel);
        accountPanel.add(cAccountB);

        //Sets background color
        getContentPane().setBackground(Color.RED);

        //Translucency initialization
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if(!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)){
            System.err.println("Translucency is not supported!");
        }

        //Initialize JFrame
        this.add(launcherPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(x,y,width,height);
        this.setResizable(false);
        this.setTitle("Launcher Window");
        this.setUndecorated(false);                         //#  TODO: settare a true per disabilitare la titleBar di base
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == extB){
            System.out.println("Visibile");

            this.dispose();
            ExtWindow extWindow = new ExtWindow(width, height, getX(), getY());
            extWindow.setVisible(true);
        }
    }
}
