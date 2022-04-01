/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.client.windows;

import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LauncherWindow extends JFrame implements ActionListener{
    
    //Panels
    private JPanel cTitleBar;       //Custom Title Bar
    private GridPanel centralPanel;    //Central Panel
    private GridPanel sideBarBPanel;   //SideBar Button Panel
    private GridPanel accountPanel;    //Account Panel
    private JPanel optionPanel;     //Option Panel
    private JPanel supportivePanel; //Supportive Panel
    
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
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
