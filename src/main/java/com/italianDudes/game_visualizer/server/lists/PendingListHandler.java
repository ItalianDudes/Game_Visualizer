package com.italianDudes.game_visualizer.server.lists;

import com.italianDudes.game_visualizer.common.Credential;
import com.italianDudes.game_visualizer.server.classes.ServerDefs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unused")
public final class PendingListHandler {

    //Attributes
    private static ArrayList<Credential> pendingUserList;

    //Constructors
    private PendingListHandler(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //Methods
    public static void initList(){
        pendingUserList = new ArrayList<>();
        readPendingUsers();
    }
    public static void readPendingUsers(){

        File pendingClientsFile = new File(ServerDefs.SERVER_PENDING_USERS_LIST_FILEPATH);

        if(pendingClientsFile.exists() && pendingClientsFile.isFile()) {

            Scanner inFile;
            try {
                inFile = new Scanner(pendingClientsFile);
            }catch (FileNotFoundException ioException){
                inFile = null;
                System.err.println("Cannot read pending users list!");
                System.exit(ServerDefs.CANNOT_READ_SERVER_PENDING_USERS_LIST_FILE);
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
                PendingListHandler.addUser(new Credential(username,password,false));
            }

            inFile.close();

        }else{
            FileWriter outFile;
            try {
                outFile = new FileWriter(pendingClientsFile);
            }catch (IOException ioException){
                outFile = null;
                System.err.println("Cannot write pending user list file!");
                System.exit(ServerDefs.CANNOT_WRITE_SERVER_PENDING_USERS_LIST_FILE);
            }
            try {
                outFile.write("0");
                outFile.flush();
            }catch (IOException ioException){
                System.err.println("Error during writing pending users list file!");
                try {
                    outFile.close();
                }catch (IOException closeIOException){
                    System.err.println("Error during writing pending users list file!");
                    System.exit(ServerDefs.CANNOT_CLOSE_SERVER_PENDING_USERS_LIST_FILE);
                }
                System.exit(ServerDefs.CANNOT_WRITE_SERVER_PENDING_USERS_LIST_FILE);
            }
            try {
                outFile.close();
            }catch (IOException closeIOException){
                System.err.println("Cannot close pending users list file!");
                System.exit(ServerDefs.CANNOT_CLOSE_SERVER_PENDING_USERS_LIST_FILE);
            }
        }

    }
    public static void writePendingUsers(){

        File pendingClientsFile = new File(ServerDefs.SERVER_PENDING_USERS_LIST_FILEPATH);

        FileWriter outFile;
        try {
            outFile = new FileWriter(pendingClientsFile);
        }catch (IOException ioException){
            outFile = null;
            System.err.println("Cannot write pending user list file!");
            System.exit(ServerDefs.CANNOT_WRITE_SERVER_PENDING_USERS_LIST_FILE);
        }
        try {
            outFile.write(pendingUserList.size()+"\n");
            for (Credential credential : pendingUserList) {
                outFile.write(credential.getUsername() + "\n");
                outFile.write(credential.getPassword() + "\n");
            }
            outFile.flush();
        }catch (IOException ioException){
            System.err.println("Error during writing pending users list file!");
            try {
                outFile.close();
            }catch (IOException closeIOException){
                System.err.println("Error during writing pending users list file!");
                System.exit(ServerDefs.CANNOT_CLOSE_SERVER_PENDING_USERS_LIST_FILE);
            }
            System.exit(ServerDefs.CANNOT_WRITE_SERVER_PENDING_USERS_LIST_FILE);
        }

    }
    public synchronized static void clearList(){
        pendingUserList.clear();
    }
    public static boolean allowUser(String username){
        for(int i=0;i<pendingUserList.size();i++){
            if(pendingUserList.get(i).getUsername().equals(username)){
                RegisteredUserListHandler.addUser(pendingUserList.get(i));
                pendingUserList.remove(i);
                return true;
            }
        }
        return false;
    }
    public static boolean denyUser(String username){
        for(int i=0;i<pendingUserList.size();i++){
            if(pendingUserList.get(i).getUsername().equals(username)){
                pendingUserList.remove(i);
                return true;
            }
        }
        return false;
    }
    public static void printClientList(){
        for (Credential user : pendingUserList) System.out.println(user);
    }
    public static boolean isEmpty(){
        return pendingUserList.isEmpty();
    }
    public static boolean contains(String username){
        for (Credential credential : pendingUserList) {
            if (credential.getUsername().equals(username))
                return true;
        }
        return false;
    }

    public static boolean addUser(Credential user){
        for (Credential credential : pendingUserList) {
            if (credential.equals(user))
                return false;
        }
        return pendingUserList.add(user);
    }
    public static Credential getUser(int index){
        return pendingUserList.get(index);
    }
    public static int size(){
        return pendingUserList.size();
    }

}
