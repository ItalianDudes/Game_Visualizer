package com.italianDudes.game_visualizer.client;

import com.italianDudes.game_visualizer.common.Defs;

import java.io.*;

public class ClientInitializationTask {
    private static final String regex = ":";
    private static final String options= Defs.PATH_RESOURCES+"client/clientOptions.txt";
    public static boolean isOpenedBackground=false;

    public static void initialize() throws IOException {
        File optionsFile = new File(options);

        if(optionsFile.exists()){
            BufferedReader optionsBuffRd = new BufferedReader(new FileReader(optionsFile));

            isOpenedBackground= Boolean.parseBoolean(optionsBuffRd.readLine().split(regex)[1].trim());
        }else{
            if(optionsFile.createNewFile()){
                BufferedWriter optionsBuffWr = new BufferedWriter(new FileWriter(optionsFile));

                optionsBuffWr.write("#launcherOpenedBackground: "+isOpenedBackground);
            }else{
                System.err.println("Cannot correctly create the OptionsFile. It may already exists!");
                throw new FileNotFoundException();
            }
        }
    }
}
