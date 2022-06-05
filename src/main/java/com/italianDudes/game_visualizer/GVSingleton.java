package com.italianDudes.game_visualizer;

import com.italianDudes.gvedk.common.DirectoryHandler;
import com.italianDudes.gvedk.common.Logger;
import com.italianDudes.gvedk.common.OSUtils;
import com.italianDudes.gvedk.common.Property;
import com.italianDudes.gvedk.common.error.os.UnsupportedOSError;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

/**
 * This Singleton contains all the information shared by the whole launcher. It is thread-safe and, following the Singleton logic,
 * can be instantiated only once.
 * It contains:
 *
 * - the options' loading task;
 * - the launcher's options;
 */
public class GVSingleton {

    //This is the Singleton's instance
    private static GVSingleton instance;

    private GVSingleton() throws IOException {

        OS_ROOT = System.getProperty("user.home")+"/";
        if(OSUtils.isLinux()){
            OS_ROOT+=".Game_Visualizer/";
        }else if(OSUtils.isWindows()){
            OS_ROOT+="AppData/Roaming/.Game_Visualizer/";
        }else{
            throw new UnsupportedOSError("This OS is not supported yet");
        }

        if (checkAndInitializeRoutine()){
            configLoadingTask();
        }else{
            throw new AccessDeniedException("The program couldn't install its components correctly due to a folder access issue");
        }
    }

    //Here there are all the variables' instances inside the Singleton
    private String OS_ROOT;
    private ArrayList<Property> configs;
    private File optionsFile;

    synchronized public static GVSingleton getInstance() throws IOException {
        if(instance==null){
            instance=new GVSingleton();
        }
        return instance;
    }

    /**
     * This method checks the integrity of the Game Visualizer's folder system and repairs it if needed.
     *
     * @return it returns whether the method was able to complete its routine.
     * @throws IOException
     */
    public boolean checkAndInitializeRoutine() throws IOException {
        boolean isSuccessful = true;


        if(DirectoryHandler.createDirectory(OS_ROOT) || DirectoryHandler.directoryExist(new File(OS_ROOT))){
            Logger.logWithCaller(OS_ROOT+" either has just been created or already existed");

            //Checks the test dir
            if(DirectoryHandler.createDirectory(OS_ROOT+Game_Visualizer.Defs.TEST_DIR) || DirectoryHandler.directoryExist(new File(OS_ROOT+Game_Visualizer.Defs.TEST_DIR))){
                Logger.logWithCaller(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+" either has just been created or already existed");
                optionsFile = new File(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+"options.cfg");

                if(!optionsFile.exists()){
                    Logger.logWithCaller(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+optionsFile.getName()+" file not found");
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(optionsFile));

                    bufferedWriter.write("launcherOpenedBack=false");
                    bufferedWriter.flush();

                    bufferedWriter.close();
                    Logger.logWithCaller(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+optionsFile.getName()+" file just created");
                }else{
                    Logger.logWithCaller(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+optionsFile.getName()+" file found");
                }

            }else{
                Logger.logWithCaller(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+": the routine was unable to complete its task!");
                isSuccessful=false;
            }

            if(isSuccessful && (!DirectoryHandler.createDirectory(OS_ROOT+Game_Visualizer.Defs.EXTENSIONS_DIR) && !DirectoryHandler.directoryExist(new File(OS_ROOT+Game_Visualizer.Defs.EXTENSIONS_DIR)))){
                isSuccessful=false;
            }
        }else{
            Logger.logWithCaller(OS_ROOT+": the routine was unable to complete its task!");
            isSuccessful=false;
        }

        return isSuccessful;
    }

    public void configLoadingTask() throws IOException {
        String line;
        boolean isLauncherOpenedBack = false;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(optionsFile));

        while((line = bufferedReader.readLine()) != null){
            if(line.contains("launcherOpenedBack")){
                isLauncherOpenedBack = Boolean.parseBoolean(line.split("=")[1].trim());
            }
        }

        configs.add(new Property("launcherOpenedBack",String.valueOf(isLauncherOpenedBack)));

        bufferedReader.close();
    }
}
