package com.italianDudes.game_visualizer.client.GraphicsAPI.utility;

public class Options {

    private boolean launcherOpenedBack;

    public Options(boolean launcherOpenedBack){
        this.launcherOpenedBack = launcherOpenedBack;
    }

    public void isLauncherOpenedBack(boolean value){
        launcherOpenedBack = value;
    }
    public boolean isLauncherOpenedBack(){
        return launcherOpenedBack;
    }

}
