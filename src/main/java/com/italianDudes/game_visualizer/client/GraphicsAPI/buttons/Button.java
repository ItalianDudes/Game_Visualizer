package com.italianDudes.game_visualizer.client.GraphicsAPI.buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton {
    private int opacity;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius;
    private boolean isOver;

    //Constructors
    public Button(String text){
        super(text);
        opacity=100;
        setColor(Color.WHITE);
        setBorderColor(Color.BLACK);
        setColorOver(Color.GRAY);
        setColorClick(Color.CYAN);
        setRadius(0);
        isOver=false;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                setBackground(colorOver);
                isOver=true;
            }
            @Override
            public void mouseExited(MouseEvent e){
                setBackground(color);
                isOver=false;
            }
            @Override
            public void mousePressed(MouseEvent e){
                setBackground(colorClick);
            }
            @Override
            public void mouseReleased(MouseEvent e){

            }
        });
    }
    public Button(){
        this("");
    }

    //Getters and Setters
    public int getOpacity() {
        return opacity;
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isOver(){
        return isOver;
    }
}
