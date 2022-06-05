package com.italianDudes.game_visualizer.client;

import com.italianDudes.game_visualizer.Game_Visualizer;

import java.io.*;
import java.nio.file.AccessDeniedException;

public class ClientInitializationTask {
    private static final String REGEX = ":";
    public static boolean isOpenedBackground=false;

    public static void initialize() throws IOException {

        File clientDirectory = new File(Game_Visualizer.Defs.CLIENT_DIR);

        if(!clientDirectory.isDirectory() || !clientDirectory.exists()){
            if(!clientDirectory.mkdir()){
                throw new AccessDeniedException(Game_Visualizer.Defs.CLIENT_DIR,"Can't create directory","Insufficient permissions");
            }
        }

        File optionsFile = new File(Game_Visualizer.Defs.CLIENT_OPTIONS);

        if(optionsFile.exists() && optionsFile.isFile()){
            BufferedReader optionsBuffRd = new BufferedReader(new FileReader(optionsFile));

            isOpenedBackground= Boolean.parseBoolean(optionsBuffRd.readLine().split(REGEX)[1].trim());

            optionsBuffRd.close();
        }else{
                BufferedWriter optionsBuffWr = new BufferedWriter(new FileWriter(optionsFile));

                optionsBuffWr.write("#launcherOpenedBackground: "+isOpenedBackground);

                optionsBuffWr.flush();

                optionsBuffWr.close();
        }

    }
}
