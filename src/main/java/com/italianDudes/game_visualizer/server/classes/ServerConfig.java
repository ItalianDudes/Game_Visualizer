package com.italianDudes.game_visualizer.server.classes;

import com.italianDudes.game_visualizer.common.Defs;
import com.italianDudes.game_visualizer.common.StringHandler;

import java.io.*;
import java.util.Scanner;

public class ServerConfig {

    //Attributes
    private int port;

    //Constructors
    public ServerConfig(){
        this.port = ServerDefs.DEFAULT_SERVER_PORT;
    }
    public ServerConfig(int port){
        this.port = port;
    }

    //Methods
    public static ServerConfig readConfig(){

        File serverConfigFile = new File(ServerDefs.SERVER_CONFIG_FILEPATH);

        if(!serverConfigFile.exists() || !serverConfigFile.isFile()){

            System.err.println("MISSING CONFIG FILE!");
            System.err.flush();

            ServerConfig serverConfig = new ServerConfig();
            writeConfig(serverConfig);
            return serverConfig;

        }else{

            System.out.println("Reading existing config file...");

            Scanner inFile;
            try {
                inFile = new Scanner(serverConfigFile);
            }catch (FileNotFoundException ioException){
                inFile = null;
                System.err.println("Cannot read server config file!");
                System.exit(ServerDefs.CANNOT_READ_SERVER_CONFIG_FILE);
            }

            //Variables that should be red
            int port = ServerDefs.DEFAULT_SERVER_PORT;

            //Reading configs
            String buffer = inFile.nextLine();
            while (!buffer.equals(Defs.END_OF_CONFIG_FILE)) {
                if(!buffer.equals("") && buffer.charAt(0) != Defs.CONFIG_FORMAT_COMMENT_LINE){ //Not a comment or an empty line
                    switch(StringHandler.getStringBeforeChar(buffer,Defs.CONFIG_FORMAT_EQUAL_CHAR)){
                        case ServerDefs.CONFIG_PORT_FIELD:
                            try {
                                port = Integer.parseInt(StringHandler.getStringAfterChar(buffer, Defs.CONFIG_FORMAT_EQUAL_CHAR));
                            }catch (NumberFormatException ignored){}
                            break;
                        default:
                            break;
                    }
                }
                buffer = inFile.nextLine();
            }
            inFile.close();

            return new ServerConfig(port);
        }
    }
    public static void writeConfig(ServerConfig serverConfig){

        File serverConfigFile = new File(ServerDefs.SERVER_CONFIG_FILEPATH);

        FileWriter outFile;
        try {
            outFile = new FileWriter(serverConfigFile);
        }catch (IOException ioException){
            outFile = null;
            System.err.println("Cannot write server config file!");
            System.exit(ServerDefs.CANNOT_WRITE_SERVER_CONFIG_FILE);
        }

        try {
            outFile.write(Defs.CONFIG_FORMAT_COMMENT_LINE+"SERVER CONFIGS\n");
            outFile.write(Defs.CONFIG_FORMAT_COMMENT_LINE+"Port to listen\n");
            outFile.write(ServerDefs.CONFIG_PORT_FIELD+Defs.CONFIG_FORMAT_EQUAL_CHAR+serverConfig.port+"\n\n");

            outFile.write(Defs.END_OF_CONFIG_FILE);
            outFile.flush();
        }catch (IOException ioException){
            System.err.println("Error during writing server config file!");
            try {
                outFile.close();
            }catch (IOException closeIOException){
                System.err.println("Cannot close server config file!");
                System.exit(ServerDefs.CANNOT_CLOSE_SERVER_CONFIG_FILE);
            }
            System.exit(ServerDefs.CANNOT_WRITE_SERVER_CONFIG_FILE);
        }
        try {
            outFile.close();
        }catch (IOException closeIOException){
            System.err.println("Cannot close server config file!");
            System.exit(ServerDefs.CANNOT_CLOSE_SERVER_CONFIG_FILE);
        }

    }
    public int getPort(){
        return port;
    }
    public void setPort(int port){
        if(port>0 && port<65535){
            this.port = port;
        }
    }
    @Override
    public String toString(){
        return "port: "+port;
    }
}
