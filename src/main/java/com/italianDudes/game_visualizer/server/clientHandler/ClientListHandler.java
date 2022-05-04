/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.server.clientHandler;

import com.italianDudes.game_visualizer.common.Peer;

import java.io.IOException;
import java.util.ArrayList;

public final class ClientListHandler { //TODO: rework of ClientListHandler

    private ClientListHandler(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //List
    private static ArrayList<Peer> clientList;

    //Methods
    public static void initList(ArrayList<Peer> initClientList){
        clientList = initClientList;
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
    public synchronized static void removeClient(Peer client) throws IOException{

        for(int i=0;i<clientList.size();i++){
            if(clientList.get(i).equals(client)){
                clientList.get(i).getPeerSocket().close();
                clientList.set(i,null);
                break;
            }
        }
    }
    public static void printClientList(){
        for (Peer client : clientList) System.out.println(client);
    }
    public static boolean isEmpty(){
        return clientList.isEmpty();
    }
    public synchronized static void addClient(Peer client){
        clientList.add(client);
    }
    public static Peer getClient(int index){
        return clientList.get(index);
    }
    public static int size(){
        return clientList.size();
    }
}
