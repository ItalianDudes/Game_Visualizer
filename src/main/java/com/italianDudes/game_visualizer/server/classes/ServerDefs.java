package com.italianDudes.game_visualizer.server.classes;

public class ServerDefs {

    //Server Config Field
    public static final String CONFIG_PORT_FIELD = "port";

    //Default Server Configs
    public static final int DEFAULT_SERVER_PORT = 45800;

    //PATHS
    public static final String SERVER_DIRECTORY_PATH = "server/";
    public static final String SERVER_REGISTERED_USERS_FILEPATH = SERVER_DIRECTORY_PATH+"registeredUsers.txt";
    public static final String SERVER_CONFIG_FILEPATH = SERVER_DIRECTORY_PATH+"config.cfg";

    //Return Values
    public static final int NO_ERR = 0;
    public static final int PORT_OUT_OF_BOUNDS = 2451; //Provided port in configs out of bounds (0-65535)
    public static final int IMPOSSIBLE_TO_BIND_PORT = 4154; //Cannot bind port, probably because is already binded
    public static final int CANNOT_CREATE_SERVER_DIRECTORY = 2111;
    public static final int CANNOT_WRITE_SERVER_CONFIG_FILE = 2155;
    public static final int CANNOT_CLOSE_SERVER_CONFIG_FILE = 2151;
    public static final int CANNOT_READ_SERVER_CONFIG_FILE = 2123;
    public static final int CANNOT_READ_SERVER_REGISTERED_USERS_LIST_FILE = 4514;
    public static final int CANNOT_WRITE_SERVER_REGISTERED_USERS_LIST_FILE = 4516;
    public static final int CANNOT_CLOSE_SERVER_REGISTERED_USERS_LIST_FILE = 4515;
}
