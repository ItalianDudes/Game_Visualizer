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
    public static final String PATH_APP_ICON = PATH_RESOURCES+"icon.png";

    //General Data Comunication
    public static final String PROTOCOL_KEEP_ALIVE = "keepAlive";
    public static final String I_AM_ALIVE = "imAlive";
}
