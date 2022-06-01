/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.server.lists;

import com.italianDudes.game_visualizer.common.Peer;

import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings({"UnusedReturnValue","unused"})
public final class ClientListHandler {

    //Attributes
    private static ArrayList<Peer> clientList;

    //Constructors
    private ClientListHandler(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //Methods
    public static void initList(){
        clientList = new ArrayList<>();
    }
    public synchronized static void clearList() {
        for(Peer client : clientList){
            if(!client.getPeerSocket().isClosed()){
                try {
                    client.getPeerSocket().close();
                }catch (IOException ignored){}
            }
        }
        clientList.clear();
    }
    public synchronized static boolean removeClient(Peer client) throws IOException{

        for(int i=0;i<clientList.size();i++){
            if(clientList.get(i).equals(client)){
                clientList.get(i).getPeerSocket().close();
                clientList.set(i,null);
                return true;
            }
        }
        return false;
    }
    public static void printClientList(){
        for (Peer client : clientList) System.out.println(client);
    }
    public static boolean isEmpty(){
        return clientList.isEmpty();
    }
    public synchronized static boolean addClient(Peer client){
        if(!clientList.contains(client))
            return clientList.add(client);
        else
            return false;
    }
    public static Peer getClient(int index){
        return clientList.get(index);
    }
    public static int size(){
        return clientList.size();
    }
}
