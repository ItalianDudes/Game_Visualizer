package com.italianDudes.game_visualizer;

import com.italianDudes.game_visualizer.client.GraphicsAPI.utility.Options;
import com.italianDudes.game_visualizer.common.DirectoryHandler;
import sun.plugin2.util.SystemUtil;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.util.Locale;

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
        if (checkAndInitializeRoutine()){
            os=System.getProperty("os.name").toLowerCase();
            op=optionsLoadingTask();
        }else{
            throw new AccessDeniedException("The program couldn't install its components correctly due to a folder access issue");
        }
    }

    //Here there are all the variables' instances inside the Singleton
    private String os;
    private Options op;

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

        if(isWindows()){

            if(DirectoryHandler.createDirectory("C:\\Programmi\\Game_Visualizer") || new File("C:\\Programmi\\Game_Visualizer").exists()){
                if(DirectoryHandler.createDirectory("C:\\Programmi\\Game_Visualizer\\test") || new File("C:\\Programmi\\Game_Visualizer\\test").exists()){
                    File file = new File("C:\\Programmi\\Game_Visualizer\\test\\opt.txt");

                    if(!file.exists()){
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

                        bufferedWriter.write("launcherOpenedBack: false");
                        bufferedWriter.flush();

                        bufferedWriter.close();
                    }

                }else{
                    isSuccessful=false;
                }
            }else{
                isSuccessful=false;
            }

        }else if(isUnix()){

            if(DirectoryHandler.createDirectory("/home/Game_Visualizer")){
                if(DirectoryHandler.createDirectory("/home/Game_Visualizer/test")){
                    File file = new File("/home/Game_Visualizer/test/opt.txt");

                    if(!file.exists()){
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

                        bufferedWriter.write("launcherOpenedBack: false");
                        bufferedWriter.flush();

                        bufferedWriter.close();
                    }

                }else{
                    isSuccessful=false;
                }
            }else{
                isSuccessful=false;
            }
        }else{
            isSuccessful=false;
        }

        return isSuccessful;
    }

    public Options optionsLoadingTask() throws IOException {
        File optFile;

        if(isWindows()){
            optFile = new File("C:\\Programmi\\Game_Visualizer\\test\\opt.txt");
        }else{
            optFile = new File("/home/Game_Visualizer/test/opt.txt");
        }
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
        return "win".equals(os);
    }
    public boolean isUnix(){
        return "unix".equals(os);
    }
}
