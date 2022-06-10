/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer;

import com.italianDudes.game_visualizer.client.Client;
import com.italianDudes.gvedk.common.Logger;

import java.io.IOException;

public final class Game_Visualizer {

    //Constructors
    private Game_Visualizer(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //Methods
    public static void main(String[] args) throws IOException {
        //The program's initialization is launched
        Logger.init();
        Logger.log("Logger correctly initialized");
        GVSingleton.getInstance();
        Logger.log("Program data successfully loaded and initialized");

        Client.start();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    exitOp();
                    System.out.println("Logger shut down");
                } catch (IOException e) {
                    System.out.println("The program was unable to shut down the Logger");
                    throw new RuntimeException(e);
                }
            }
        }));
    }

    //Class Constants
    @SuppressWarnings("unused")
    public static final class Defs {

        private Defs(){
            throw new UnsupportedOperationException("Can't instantiate this class!");
        }

        //PATHS
        public static final String BUILT_IN_PATH_RESOURCES = "src/main/resources/";
        public static final String EXTENSIONS_DIR = "extensions/";

        public static final String CLIENT_DIR = "client/";
        public static final String CLIENT_OPTIONS = CLIENT_DIR +"clientOptions.txt";
        public static final String PATH_APP_ICON = BUILT_IN_PATH_RESOURCES +"icon.png";
        public static final String LOG_DIR = "logs/";
        public static final String LOG_LATEST_FILE = LOG_DIR+"latest.log";
        public static final String TEST_DIR = "test/";

        //General Data Communication
        public static final int DEFAULT_CONNECTION_TIMEOUT = 60000; //Expressed in milliseconds

        //Config Format
        public static final char CONFIG_FORMAT_COMMENT_LINE = '#';
        public static final char CONFIG_FORMAT_EQUAL_CHAR = '=';
        public static final String END_OF_CONFIG_FILE = "~FILE_END";

        //General Purpose Constants
        public static final String REGEX = "=";
        public static final int ZIP_ID = 0x504b0304;
        public static final String MANIFEST_ENTRY = "META-INF/MANIFEST.MF";

        //Manifest entries consts checks
        public static final String MANIFEST_MAIN_ENTRY = "Main-Class";
        public static final String MANIFEST_AUTH_ENTRY = "Author";
        public static final String MANIFEST_EXT_NAME_ENTRY = "Extension-Name";
        public static final String MANIFEST_DATE_ENTRY = "Date";

    }

    public static void exitOp() throws IOException {
        Logger.close();
    }
}
