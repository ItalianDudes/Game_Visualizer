package com.italianDudes.game_visualizer.server.classes;

import com.italianDudes.game_visualizer.common.Defs;

@SuppressWarnings("unused")
public final class ServerDefs {

    //Constructors
    private ServerDefs(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //PATHS
    public static final String SERVER_DIRECTORY_PATH = Defs.BUILT_IN_PATH_RESOURCES +"server/";
    public static final String SERVER_REGISTERED_USERS_LIST_FILEPATH = SERVER_DIRECTORY_PATH+"registeredUsersList.txt";
    public static final String SERVER_PENDING_USERS_LIST_FILEPATH = SERVER_DIRECTORY_PATH+"pendingUsersList.txt";

    //Return Values
    public static final int NO_ERR = 0;
    public static final int PORT_OUT_OF_BOUNDS = 2451; //Provided port in configs out of bounds (0-65535)
    public static final int IMPOSSIBLE_TO_BIND_PORT = 4154; //Cannot bind port, probably because is already bound
    public static final int CANNOT_CREATE_SERVER_DIRECTORY = 2111;
    public static final int CANNOT_READ_SERVER_REGISTERED_USERS_LIST_FILE = 4514;
    public static final int CANNOT_WRITE_SERVER_REGISTERED_USERS_LIST_FILE = 4516;
    public static final int CANNOT_CLOSE_SERVER_REGISTERED_USERS_LIST_FILE = 4515;
    public static final int CANNOT_READ_SERVER_PENDING_USERS_LIST_FILE = 4211;
    public static final int CANNOT_WRITE_SERVER_PENDING_USERS_LIST_FILE = 4212;
    public static final int CANNOT_CLOSE_SERVER_PENDING_USERS_LIST_FILE = 4213;
}
