package com.italianDudes.game_visualizer.client.GraphicsAPI.panels;

import java.awt.*;

public class ImagePanel extends Panel{

    private Image img;
    private int height;
    private int width;
    private int x;
    private int y;

    public ImagePanel(Image img, int height, int width, int x, int y){
        super();

        this.img=img;
        this.height=height;
        this.width=width;
        this.x=x;
        this.y=y;
    }

    @Override
    public void paintComponent(Graphics g) {
        if(img!=null){
            g.drawImage(img,x,y,width,height,this);
        }else{
            g.setColor(Color.RED);
            g.fillRect(x,y,width,height);
        }

        super.paintComponent(g);
    }
}
