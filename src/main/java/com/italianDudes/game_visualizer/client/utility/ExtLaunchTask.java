package com.italianDudes.game_visualizer.client.utility;

import com.italianDudes.game_visualizer.Game_Visualizer;
import com.italianDudes.gvedk.common.Extension;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ExtLaunchTask extends Thread{
    private final String extPathToDir;
    private final String extPathToMain;

    public ExtLaunchTask(String extPathToDir, String extPathToMain){
        this.extPathToDir=extPathToDir.replaceAll("\\.","/");        //Path to the extension's directory
        this.extPathToMain=extPathToMain;                                           //Path inside the Extension's Jar to its start() method
    }

    @Override
    public void run(){
        Class classPointer;

        try {
            classPointer = Class.forName(extPathToMain);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Method method = classPointer.getDeclaredMethod(Game_Visualizer.Defs.EXT_START_METHOD);

            Extension ext= (Extension) classPointer.getConstructor(File.class).newInstance(new File(Game_Visualizer.Defs.EXTENSIONS_DIR+extPathToDir));

            method.invoke(ext);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
