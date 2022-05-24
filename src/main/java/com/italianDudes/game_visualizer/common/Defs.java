/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.common;

public final class Defs {

    private Defs(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //Starting Arguments
    public static final String ARGUMENTS_START_AS_SERVER = "-server";

    //PATHS
    public static final String PATH_RESOURCES = "src/main/resources/";
    public static final String OPTIONS = Defs.CLIENT_DIR +"clientOptions.txt";
    public static final String CLIENT_DIR = Defs.PATH_RESOURCES+"client/";
    public static final String PATH_APP_ICON = PATH_RESOURCES+"icon.png";

    //General Data Communication
    public static final int DEFAULT_CONNECTION_TIMEOUT = 60000; //Expressed in milliseconds
    public static final String PROTOCOL_KEEP_ALIVE = "keepAlive";
    public static final String I_AM_ALIVE = "imAlive";

    //PreAuth Protocols
    public static final int PREAUTH_PROTOCOL_LOGIN = 1;
    public static final int PREAUTH_PROTOCOL_REGISTER = 2;

    //Config Format
    public static final char CONFIG_FORMAT_COMMENT_LINE = '#';
    public static final char CONFIG_FORMAT_EQUAL_CHAR = '=';
    public static final String END_OF_CONFIG_FILE = "~FILE_END";

}
