/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.client.windows;

import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * LauncherWindow is the class used to build the graphical layout of the launcher window.
 * It extends the javax.swing.JFrame class and implements the java.awt.event.ActionListener interface.
 *
 * @author SerSapessi
 * @version 1.0
 * @since 2022
 */
public class LauncherWindow extends JFrame implements ActionListener{
    
    //Panels
    private JPanel cTitleBar;       //Custom Title Bar
    private GridPanel centralOptPanel;    //Central Option Panel
    private GridPanel sideBarBPanel;   //SideBar Button Panel
    private GridPanel accountPanel;    //Account Panel
    private GridPanel accountTextPanel;
    private GridPanel setPanel;
    private JPanel optionPanel;     //Option Panel
    private BorderPanel supportivePanel; //Supportive Panel
    private BorderPanel centralLayoutPanel; //Central Layout Panel (It serves the purpose of putting down the layout for the Central Option Panel
    private JPanel bufferPanel;     //It serves the purpose of filling out the blank spots inside the centralLayoutPanel, to make the layout works
    
    private BorderPanel sideBarPanel;    //SideBar Panel
    private JPanel centeringPanel;  //Centering Panel
    
    private BorderPanel appPanel;        //App Panel
    private BorderPanel launcherPanel;   //Launcher Panel
    
    //Buttons
    private JButton launchB;        //Launch Button
    private JButton optB;           //Option Button
    private JButton cAccountB;      //Change Account Button
    private JButton homeB;          //Home Button
    private JButton extB;           //Extensions Button
    private JButton setsB;          //Sets Button
    
    //Labels
    private JLabel accountL;        //Account Label
    private JLabel setL;            //Set Label
    
    //Text Fields
    private JTextField accountTF;   //Account Text Field
    
    //Combo Boxes
    private JComboBox<String> setsDisplayedCB;  //Sets Displayed Combo Box
    
    public LauncherWindow(){
        //Panels initialization
        bufferPanel = new JPanel();

        launcherPanel = new BorderPanel();
        appPanel = new BorderPanel();
        cTitleBar = new JPanel();
        
        launcherPanel.setNorth(cTitleBar);
        launcherPanel.setCenter(appPanel);
        
        sideBarPanel = new BorderPanel();
        supportivePanel = new BorderPanel();
        //supportivePanel.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        appPanel.setWest(sideBarPanel);
        appPanel.setCenter(supportivePanel);
        
        optionPanel = new JPanel();
        optionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        centralLayoutPanel = new BorderPanel();
        centralLayoutPanel.setNorth(bufferPanel);
        centralLayoutPanel.setEast(bufferPanel);
        centralLayoutPanel.setSouth(bufferPanel);
        centralLayoutPanel.setWest(bufferPanel);

        centralOptPanel = new GridPanel(2,1);
        centralOptPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
        setPanel = new GridPanel(1,2);
        centralLayoutPanel.setCenter(centralOptPanel);
        supportivePanel.setCenter(centralLayoutPanel);
        supportivePanel.setSouth(optionPanel);
        
        sideBarBPanel  = new GridPanel(3, 1);
        centeringPanel = new JPanel();
        accountPanel = new GridPanel(2, 1);
        accountTextPanel = new GridPanel(1,2);
        sideBarPanel.setNorth(sideBarBPanel);
        sideBarPanel.setCenter(centeringPanel);
        sideBarPanel.setSouth(accountPanel);
        
        //Buttons initialization
        launchB = new JButton("AVVIA");     //#TODO: usare la traduzione automatica
        cAccountB = new JButton("Change Account");
        homeB = new JButton("Home");
        extB = new JButton("Extensions");
        setsB = new JButton("Sets");

        centralOptPanel.add(launchB);
        centralOptPanel.add(setPanel);
        sideBarBPanel.add(homeB);
        sideBarBPanel.add(extB);
        sideBarBPanel.add(setsB);
        
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

        //Initialize JFrame
        this.add(launcherPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0,0,900,600);
        this.setResizable(false);
        this.setTitle("Launcher Window");
        this.setUndecorated(false);                         //#TODO: settare a true per disabilitare la titleBar di base
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
