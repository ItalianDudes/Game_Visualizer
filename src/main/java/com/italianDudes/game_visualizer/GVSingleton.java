package com.italianDudes.game_visualizer;

import com.italianDudes.game_visualizer.client.GraphicsAPI.utility.Options;
import com.italianDudes.gvedk.common.DirectoryHandler;
import com.italianDudes.gvedk.common.Logger;

import java.io.*;
import java.nio.file.AccessDeniedException;

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
        os=System.getProperty("os.name").toLowerCase();

        if(isUnix()){
            OS_ROOT="/home/.Game_Visualizer";
        }else{
            OS_ROOT="%appdata%/.Game_Visualizer";
        }

        if (checkAndInitializeRoutine()){
            op=optionsLoadingTask();
        }else{
            throw new AccessDeniedException("The program couldn't install its components correctly due to a folder access issue");
        }
    }

    //Here there are all the variables' instances inside the Singleton
    private String os;
    private Options op;
    private String OS_ROOT;

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
                File file = new File(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+"options.txt");

                if(!file.exists()){
                    Logger.logWithCaller(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+"options.txt file not found");
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

                    bufferedWriter.write("launcherOpenedBack: false");
                    bufferedWriter.flush();

                    bufferedWriter.close();
                    Logger.logWithCaller(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+"options.txt file just created");
                }else{
                    Logger.logWithCaller(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+"options.txt file found");
                }

            }else{
                Logger.logWithCaller(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+": the routine was unable to complete its task!");
                isSuccessful=false;
            }
        }else{
            Logger.logWithCaller(OS_ROOT+": the routine was unable to complete its task!");
            isSuccessful=false;
        }

        return isSuccessful;
    }

    public Options optionsLoadingTask() throws IOException {
        File optFile;

        optFile = new File(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+"options.txt");

        String line;
        boolean isLauncherOpenedBack = false;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(optFile));

        while((line = bufferedReader.readLine()) != null){
            if(line.contains("launcherOpenedBack")){
                isLauncherOpenedBack = Boolean.parseBoolean(line.split(":")[1].trim());
            }
        }

        Options op = new Options(isLauncherOpenedBack);

        bufferedReader.close();

        return op;
    }

    public boolean isWindows(){
        return os.contains("windows");
    }
    public boolean isUnix(){
        return os.contains("unix");
    }
}
