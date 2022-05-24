package com.italianDudes.game_visualizer.server.lists;

import com.italianDudes.game_visualizer.common.Credential;
import com.italianDudes.game_visualizer.server.classes.ServerDefs;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unused")
public final class RegisteredUserListHandler {

    //Attributes
    private static ArrayList<Credential> registeredUserList;

    //Constructors
    private RegisteredUserListHandler() {
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //Methods
    public static void initList(){
        registeredUserList = new ArrayList<>();
        readRegisteredUsers();
    }
    public static void readRegisteredUsers(){

        File registeredClientsFile = new File(ServerDefs.SERVER_REGISTERED_USERS_LIST_FILEPATH);

        if(registeredClientsFile.exists() && registeredClientsFile.isFile()) {

            Scanner inFile;
            try {
                inFile = new Scanner(registeredClientsFile);
            }catch (FileNotFoundException ioException){
                inFile = null;
                System.err.println("Cannot read registered users list!");
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
                RegisteredUserListHandler.addUser(new Credential(username,password,false));
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
                System.exit(ServerDefs.CANNOT_CLOSE_SERVER_REGISTERED_USERS_LIST_FILE);
            }
        }

    }
    public static void writeRegisteredUsers(){

        File registeredClientsFile = new File(ServerDefs.SERVER_REGISTERED_USERS_LIST_FILEPATH);

        FileWriter outFile;
        try {
            outFile = new FileWriter(registeredClientsFile);
        }catch (IOException ioException){
            outFile = null;
            System.err.println("Cannot write registered user list file!");
            System.exit(ServerDefs.CANNOT_WRITE_SERVER_REGISTERED_USERS_LIST_FILE);
        }
        try {
            outFile.write(registeredUserList.size()+"\n");
            for (Credential credential : registeredUserList) {
                outFile.write(credential.getUsername() + "\n");
                outFile.write(credential.getPassword() + "\n");
            }
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

    }
    public static boolean unregisterUser(String username){
        for(int i=0;i<registeredUserList.size();i++){
            if(registeredUserList.get(i).getUsername().equals(username)){
                registeredUserList.remove(i);
                return true;
            }
        }
        return false;
    }
    public static boolean registerUser(Credential user){
        for (Credential credential : registeredUserList) {
            if (credential.equals(user)) {
                return false;
            }
        }
        registeredUserList.add(user);
        return true;
    }
    public static boolean contains(Credential credential){
        return registeredUserList.contains(credential);
    }
    public synchronized static void clearList(){
        registeredUserList.clear();
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
    public static boolean addUser(Credential user){
        for (Credential credential : registeredUserList) {
            if (credential.equals(user))
                return false;
        }
        registeredUserList.add(user);
        return true;
    }
    public static Credential getUser(int index){
        return registeredUserList.get(index);
    }
    public static int size(){
        return registeredUserList.size();
    }
}
