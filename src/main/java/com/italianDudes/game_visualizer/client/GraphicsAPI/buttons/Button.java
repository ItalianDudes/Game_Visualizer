package com.italianDudes.game_visualizer.client.GraphicsAPI.buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Button is a wrapper class of the javax.swing.JButton class.
 * It gives the user more methods to simply set up nice buttons.
 */
public class Button extends JButton {

    /**
     * Checks whether the mouse cursor is over the button.
     *
     * @return It is boolean value of the expression: if the cursor is over the button, it returns true, else it returns false.
     */
    public boolean isOver() {
        return over;
    }

    /**
     * Gives the user the ability to tell the button whether the mouse cursor is or isn't over it, even if that's not the case.
     *
     * @param over This is the value of the boolean expression: true if the cursor is over the button, false if it isn't.
     */
    public void setOver(boolean over) {
        this.over = over;
    }

    /**
     * It gives the user the object containing the button's color. The color of a button is its background color.
     *
     * @return It is the instanced value of the button's color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * It gives the user the ability to specify the button's color. The color of button is its background color.
     *
     * @param color This is the color the user wants to set for the button.
     */
    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    /**
     * This is the color the button takes when the mouse cursor is over it.
     *
     * @return It is the instanced value of the button's color when the mouse cursor is over it.
     */
    public Color getColorOver() {
        return colorOver;
    }

    /**
     * It gives the user the ability to specify the button's color when the mouse cursor is over it.
     *
     * @param colorOver This is the color the user wants to set for the button when the mouse cursor is over it.
     */
    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    /**
     * This is the color the button takes when it is clicked.
     *
     * @return It is the instanced value of the button's color when it is clicked.
     */
    public Color getColorClick() {
        return colorClick;
    }

    /**
     * It gives the user the ability to specify the button's color when it is clicked.
     *
     * @param colorClick This is the color the user wants to set for the button when it is clicked.
     */
    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    /**
     * This is the button's border color.
     *
     * @return It is the instance value of the button's border color.
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * It gives the user the ability to specify the button's border color.
     *
     * @param borderColor This is the color the user wants to set for the button's border.
     */
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * This is the button radius. The bigger the value is, the rounder the button's edges are.
     *
     * @return It is the int value of the current button's radius.
     */
    public int getRadius() {
        return radius;
    }

    /**
     * It gives the user the ability to specify the button radius. The bigger the value is, the rounder the button's edges are.
     *
     * @param radius This is the button radius the user wants to set for the button.
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * This constructor lets the user specify the text the button should display.
     * For debugging purposes, the button already has colors set:
     * - Color Over: new Color(179,250,160);
     * - Color Click: new Color(152,184,144);
     * - Border Color: new Color(30,136,56).
     * The constructor doesn't specify any radius for the button. It also set the content are as filled.
     * PS: if the user wants to specify a radius for the button, then it should specify the are as not filled.
     * They can do it using the method "setContentAreaFilled()".
     * The focus is also not painted.
     *
     * @param text This is the text the user wants the button to disply.
     */
    public Button(String text) {
        //  Init Color
        super(text);

        setColor(Color.WHITE);
        colorOver = new Color(179, 250, 160);
        colorClick = new Color(152, 184, 144);
        borderColor = new Color(30, 136, 56);
        setContentAreaFilled(true);
        setFocusPainted(false);
        //  Add event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(colorOver);
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(color);
                over = false;

            }

            @Override
            public void mousePressed(MouseEvent me) {
                setBackground(colorClick);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    setBackground(colorOver);
                } else {
                    setBackground(color);
                }
            }
        });
    }

    /**
     * This constructor lets the user create a button without texts or icons.
     * It recalls the constructor "public Button(String text)".
     */
    public Button(){
        this("");
    }

    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 0;

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //  Paint Border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        //  Border set 2 Pix
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(grphcs);
    }
}
