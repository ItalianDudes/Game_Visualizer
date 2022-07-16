package com.italianDudes.game_visualizer.client.utility;

import com.italianDudes.game_visualizer.GVSingleton;
import com.italianDudes.game_visualizer.Game_Visualizer;
import com.italianDudes.gvedk.common.Extension;
import com.italianDudes.gvedk.common.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.jar.JarFile;

public class ExtLaunchTask extends Thread{
    private final String extPathToDir;
    private final String extPathToMain;
    private final String jarName;

    public ExtLaunchTask(String extPathToDir, String extPathToMain, String jarName){
        this.extPathToDir=extPathToDir;        //Path to the extension's directory
        this.extPathToMain=extPathToMain.replaceAll("\\.","/");      //Path inside the Extension's Jar to its start() method
        this.jarName=jarName;

        Logger.logWithCaller("Path to dir: "+this.extPathToDir + "; Path to Main: "+this.extPathToMain);
    }

    @Override
    public void run(){
        Class classPointer;

        /*
        JarFile jarFilePointer = null;

        try {
            jarFilePointer = new JarFile(GVSingleton.getInstance().getOSRoot()+Game_Visualizer.Defs.EXTENSIONS_DIR+jarName);
        }catch (IOException e){}
        */

        try {
            classPointer = Class.forName(GVSingleton.getInstance().getOSRoot()+Game_Visualizer.Defs.EXTENSIONS_DIR+jarName+"/"+extPathToMain);
            Logger.logWithCaller("Extension's Main class found");
        } catch (ClassNotFoundException e) {
            Logger.logWithCaller("Extension's Main class not found");
            throw new RuntimeException(e);
        } catch (IOException e){
            Logger.logWithCaller("DEBUG ERROR");
            throw new RuntimeException(e);
        }

        try {
            Method method = classPointer.getDeclaredMethod(Game_Visualizer.Defs.EXT_START_METHOD);
            Logger.logWithCaller("Extension's start() method found");

            Extension ext= (Extension) classPointer.getConstructor(File.class).newInstance(new File(Game_Visualizer.Defs.EXTENSIONS_DATA_DIR+extPathToDir));
            Logger.logWithCaller("The parameters have been passed to the Extension");

            method.invoke(ext);
            Logger.logWithCaller("The Extension's start() method has been invoked");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            Logger.logWithCaller("Issues in the invoking of the Extension's start() method and the passing of the needed parameters");
            throw new RuntimeException(e);
        }
    }
}
