package com.italianDudes.game_visualizer.client;

import java.io.*;
import java.nio.file.AccessDeniedException;

import com.italianDudes.game_visualizer.common.Defs;

public class ClientInitializationTask {
    private static final String REGEX = ":";
    public static boolean isOpenedBackground=false;

    public static void initialize() throws IOException {

        File clientDirectory = new File(Defs.CLIENT_DIR);

        if(!clientDirectory.isDirectory() || !clientDirectory.exists()){
            if(!clientDirectory.mkdir()){
                throw new AccessDeniedException(Defs.CLIENT_DIR,"Can't create directory","Insufficient permissions");
            }
        }

        File optionsFile = new File(Defs.OPTIONS);

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
