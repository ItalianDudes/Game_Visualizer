package com.italianDudes.game_visualizer.server.clientHandler;

import com.italianDudes.game_visualizer.common.Credential;
import com.italianDudes.game_visualizer.server.classes.ServerDefs;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RegisteredUserListHandler { //TODO: Test RegisteredUserListHandler

    //Attributes
    private static ArrayList<Credential> registeredUserList;

    //Methods
    public static void initList(){
        registeredUserList = new ArrayList<>();
        readRegisteredUsers();
    }
    private static void readRegisteredUsers(){ //TODO: test readRegisteredUsers()

        File registeredClientsFile = new File(ServerDefs.SERVER_REGISTERED_USERS_FILEPATH);

        if(registeredClientsFile.exists() && registeredClientsFile.isFile()) {

            Scanner inFile;
            try {
                inFile = new Scanner(registeredClientsFile);
            }catch (FileNotFoundException ioException){
                inFile = null;
                System.err.println("Cannot registered users list!");
                System.exit(ServerDefs.CANNOT_READ_SERVER_REGISTERED_USERS_LIST_FILE);
            }

            int numUsers = 0;
            try{
                numUsers = Integer.parseInt(inFile.nextLine());
            }catch (NumberFormatException ignored){}

            String username;
            String password;

            for(int i=0;i<numUsers;i++){
                username = inFile.nextLine();
                password = inFile.nextLine();
                RegisteredUserListHandler.addUser(new Credential(username,password));
            }

            inFile.close();

        }else{
            FileWriter outFile;
            try {
                outFile = new FileWriter(registeredClientsFile);
            }catch (IOException ioException){
                outFile = null;
                System.err.println("Cannot write registered user list file!");
                System.exit(ServerDefs.CANNOT_WRITE_SERVER_REGISTERED_USERS_LIST_FILE);
            }
            try {
                outFile.write("0");
                outFile.flush();
            }catch (IOException ioException){
                System.err.println("Error during writing registered users list file!");
                try {
                    outFile.close();
                }catch (IOException closeIOException){
                    System.err.println("Error during writing registered users list file!");
                    System.exit(ServerDefs.CANNOT_CLOSE_SERVER_REGISTERED_USERS_LIST_FILE);
                }
                System.exit(ServerDefs.CANNOT_WRITE_SERVER_REGISTERED_USERS_LIST_FILE);
            }
            try {
                outFile.close();
            }catch (IOException closeIOException){
                System.err.println("Cannot close registered users list file!");
                System.exit(ServerDefs.CANNOT_CLOSE_SERVER_CONFIG_FILE);
            }
        }

    }
    public static boolean contains(Credential credential){
        return registeredUserList.contains(credential);
    }
    public synchronized static void clearList(){
        registeredUserList.clear();
    }
    public static void removeUser(Credential user){
        for(int i = 0; i< registeredUserList.size(); i++){
            if(registeredUserList.get(i).equals(user)){
                registeredUserList.set(i,null);
                break;
            }
        }
    }
    public static void printClientList(){
        for (Credential user : registeredUserList) System.out.println(user);
    }
    public static boolean isEmpty(){
        return registeredUserList.isEmpty();
    }
    public static boolean containsUsername(String username){
        for (Credential credential : registeredUserList) {
            if (credential.getUsername().equals(username))
                return true;
        }
        return false;
    }
    public static void addUser(Credential user){
        registeredUserList.add(user);
    }
    public static Credential getUser(int index){
        return registeredUserList.get(index);
    }
    public static int size(){
        return registeredUserList.size();
    }
}
