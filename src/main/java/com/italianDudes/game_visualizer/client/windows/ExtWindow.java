package com.italianDudes.game_visualizer.client.windows;

import com.italianDudes.game_visualizer.GVSingleton;
import com.italianDudes.game_visualizer.Game_Visualizer;
import com.italianDudes.game_visualizer.client.GraphicsAPI.buttons.LauncherButton;
import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.BorderPanel;
import com.italianDudes.game_visualizer.client.GraphicsAPI.panels.GridPanel;
import com.italianDudes.game_visualizer.client.customComponents.ExtComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

    public ExtWindow(int width, int height, int x, int y) throws IOException {
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

        for(int i=0;i<GVSingleton.getInstance().getExtsAttributes().size();i++){
            ExtComponent tempExtC = new ExtComponent(GVSingleton.getInstance().getExtsAttributes().get(i).getValue(Game_Visualizer.Defs.MANIFEST_EXT_NAME_ENTRY),
                                                        GVSingleton.getInstance().getExtsAttributes().get(i).getValue(Game_Visualizer.Defs.MANIFEST_AUTH_ENTRY),
                                                        GVSingleton.getInstance().getExtsAttributes().get(i).getValue(Game_Visualizer.Defs.MANIFEST_DATE_ENTRY),
                                                        null);
            defaultListModel.addElement(tempExtC);
        }

        extList.setModel(defaultListModel);
        //Labels' initialization
        extNumL = new JLabel(String.valueOf(GVSingleton.getInstance().getExtsAttributes().size()));

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
