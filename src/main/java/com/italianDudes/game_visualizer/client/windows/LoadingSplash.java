package com.italianDudes.game_visualizer.client.windows;

import javax.swing.*;

public class LoadingSplash extends JFrame implements Runnable{

    private int width;
    private int height;
    private int x;
    private int y;

    public LoadingSplash(int width, int height, int x, int y){
        this.width=width;
        this.height=height;
        this.x=x;
        this.y=y;

        this.setResizable(false);
        this.setBounds(x,y,width,height);
        this.setUndecorated(true);
    }

    @Override
    public void run() {
        //#TODO: to be implemented
        System.out.println("Ciao");
    }
}
