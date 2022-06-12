/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.client.windows;

import com.italianDudes.game_visualizer.GVSingleton;
import com.italianDudes.game_visualizer.Game_Visualizer;
import com.italianDudes.game_visualizer.client.GraphicsAPI.buttons.LauncherButton;
import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.BorderPanel;
import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.GridPanel;
import com.italianDudes.game_visualizer.client.utility.ExtLaunchTask;
import com.italianDudes.gvedk.common.InfoFlags;
import com.italianDudes.gvedk.common.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
    private GridPanel setPanel;
    private JPanel optionPanel;     //Option Panel
    private BorderPanel supportivePanel; //Supportive Panel

    private GridPanel sideBarPanel;    //SideBar Panel
    
    private BorderPanel appPanel;        //App Panel
    private BorderPanel launcherPanel;   //Launcher Panel
    
    //Buttons
    private LauncherButton launchB;        //Launch Button
    private LauncherButton optB;           //Option Button #TODO: da implementare con icona
    private LauncherButton homeB;          //Home Button
    private LauncherButton extB;           //Extensions Button
    
    //Labels
    private JLabel setL;            //Set Label

    //Combo Boxes
    private JComboBox<String> setsDisplayedCB;  //Sets Displayed Combo Box

    public LauncherWindow(int width, int height, int x, int y) throws IOException {
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
        centralOptPanel.setBorder(new EmptyBorder(225,200,225,200));
        setPanel = new GridPanel(1,2);
        supportivePanel.setCenter(centralOptPanel);
        supportivePanel.setSouth(optionPanel);
        
        //Buttons initialization
        launchB = new LauncherButton("AVVIA");     //#TODO: usare la traduzione automatica
        launchB.setRadius(0);
        launchB.addActionListener(this);
        homeB = new LauncherButton("Home");
        homeB.addActionListener(this);
        extB = new LauncherButton("Extensions");
        extB.addActionListener(this);

        centralOptPanel.add(launchB);
        centralOptPanel.add(setPanel);
        sideBarPanel.add(homeB);
        sideBarPanel.add(extB);
        
        //Labels initialization
        setL = new JLabel("Extension:");         //#TODO: usare la traduzione automatica
        
        setPanel.add(setL);

        //Combo Box initialization
        setsDisplayedCB = new JComboBox<>();

        for(int i=0;i<GVSingleton.getInstance().getExtsAttributes().size();i++){
            setsDisplayedCB.addItem(GVSingleton.getInstance().getExtsAttributes().get(i).getValue(Game_Visualizer.Defs.MANIFEST_EXT_NAME_ENTRY));
        }
        setsDisplayedCB.setSelectedIndex(0);

        setPanel.add(setsDisplayedCB);

        //Sets background color
        getContentPane().setBackground(Color.RED);

        //Translucency initialization
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if(!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)){
            Logger.logWithCaller("Translucency is not supported");
        }

        //Initialize JFrame
        this.add(launcherPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(x,y,width,height);
        this.setResizable(false);
        this.setTitle("Launcher Window - Home");
        this.setUndecorated(false);                         //#  TODO: settare a true per disabilitare la titleBar di base
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Logger.logWithCaller("Button clicked");
        if(e.getSource() == extB){
            Logger.logWithCaller("Extension button clicked");

            this.dispose();
            ExtWindow extWindow;
            try {
                Logger.logWithCaller("Switching to the Extension Window");
                extWindow = new ExtWindow(width, height, getX(), getY());
            } catch (IOException ex) {
                Logger.logWithCaller("Fatal error: the program data are being initialized now and the program crashed");
                Logger.log(new RuntimeException(),new InfoFlags(new RuntimeException(),true));
                throw new RuntimeException(ex);
            }
            extWindow.setVisible(true);
        }else if(e.getSource() == launchB){
            Logger.logWithCaller("Launch button clicked");

            try {
                GVSingleton.getInstance().getTaskDescriptors().get(setsDisplayedCB.getSelectedIndex()).launchTask();
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        Logger.logWithCaller("Home button clicked");
    }
}
