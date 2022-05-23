package com.italianDudes.game_visualizer.client;

import com.italianDudes.game_visualizer.common.Defs;

import java.io.*;
import java.nio.file.AccessDeniedException;

public class ClientInitializationTask {
    private static final String REGEX = ":";
    private static final String CLIENT_DIR = Defs.PATH_RESOURCES+"client/";
    private static final String OPTIONS = CLIENT_DIR +"clientOptions.txt";
    public static boolean isOpenedBackground=false;

    public static void initialize() throws IOException {

        File clientDirectory = new File(CLIENT_DIR);

        if(!clientDirectory.isDirectory() || !clientDirectory.exists()){
            if(!clientDirectory.mkdir()){
                throw new AccessDeniedException(CLIENT_DIR,"Can't create directory","Insufficient permissions");
            }
        }

        File optionsFile = new File(OPTIONS);

        if(optionsFile.exists() && optionsFile.isFile()){
            BufferedReader optionsBuffRd = new BufferedReader(new FileReader(optionsFile));

            isOpenedBackground= Boolean.parseBoolean(optionsBuffRd.readLine().split(REGEX)[1].trim());
        }else{
                BufferedWriter optionsBuffWr = new BufferedWriter(new FileWriter(optionsFile));

                optionsBuffWr.write("#launcherOpenedBackground: "+isOpenedBackground);

                optionsBuffWr.flush();

                optionsBuffWr.close();
        }

    }
}
