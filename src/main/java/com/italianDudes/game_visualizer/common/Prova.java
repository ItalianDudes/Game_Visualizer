package com.italianDudes.game_visualizer.common;

import java.io.Serializable;

public class Prova implements Serializable {
    public int casa;
    public double panino;

    public Prova(int casa, double panino){
        this.casa = casa;
        this.panino = panino;
    }

    @Override
    public String toString() {
        return "Prova{" +
                "casa=" + casa +
                ", panino=" + panino +
                '}';
    }
}
