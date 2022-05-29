/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.common;

@SuppressWarnings("unused")
public final class Defs {

    private Defs(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //PATHS
    public static final String PATH_RESOURCES = "src/main/resources/";
    public static final String EXTENSIONS_DIR = "extensions/";
    public static final String CLIENT_OPTIONS = Defs.CLIENT_DIR +"clientOptions.txt";
    public static final String CLIENT_DIR = Defs.PATH_RESOURCES+"client/";
    public static final String PATH_APP_ICON = PATH_RESOURCES+"icon.png";
    public static final String LOG_DIR = "logs/";
    public static final String LOG_LATEST_FILE = LOG_DIR+"latest.log";

    //General Data Communication
    public static final int DEFAULT_CONNECTION_TIMEOUT = 60000; //Expressed in milliseconds

    //Config Format
    public static final char CONFIG_FORMAT_COMMENT_LINE = '#';
    public static final char CONFIG_FORMAT_EQUAL_CHAR = '=';
    public static final String END_OF_CONFIG_FILE = "~FILE_END";

}
