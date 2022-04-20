/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.client.windows;

import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LauncherWindow extends JFrame implements ActionListener{
    
    //Panels
    private JPanel cTitleBar;       //Custom Title Bar
    private GridPanel centralOptPanel;    //Central Option Panel
    private GridPanel sideBarBPanel;   //SideBar Button Panel
    private GridPanel accountPanel;    //Account Panel
    private GridPanel setPanel;
    private JPanel optionPanel;     //Option Panel
    private BorderPanel supportivePanel; //Supportive Panel
    
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
        launcherPanel = new BorderPanel();
        appPanel = new BorderPanel();
        cTitleBar = new JPanel();
        
        launcherPanel.setNorth(cTitleBar);
        launcherPanel.setCenter(appPanel);
        
        sideBarPanel = new BorderPanel();
        supportivePanel = new BorderPanel();
        appPanel.setWest(sideBarPanel);
        appPanel.setCenter(supportivePanel);
        
        optionPanel = new JPanel();
        centralOptPanel = new GridPanel(1,2);
        setPanel = new GridPanel(2,1);
        supportivePanel.setCenter(centralOptPanel);
        supportivePanel.setSouth(optionPanel);
        
        sideBarBPanel  = new GridPanel(1, 3);
        centeringPanel = new JPanel();
        accountPanel = new GridPanel(1, 2);
        sideBarPanel.setNorth(sideBarBPanel);
        sideBarPanel.setCenter(centeringPanel);
        sideBarPanel.setSouth(accountPanel);
        
        //Buttons initialization
        launchB = new JButton("AVVIA");     //#TODO: usare la traduzione automatica
        
        centralOptPanel.add(launchB);
        centralOptPanel.add(setPanel);
        
        //Labels initialization
        setL = new JLabel("Sets:");         //#TODO: usare la traduzione automatica
        
        setPanel.add(setL);
        
        //Combo Box initialization
        setsDisplayedCB = new JComboBox<>();
        
        setPanel.add(setsDisplayedCB);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
