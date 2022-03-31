package com.italianDudes.game_visualizer.server.clientHandler;

import com.italianDudes.game_visualizer.common.Defs;
import com.italianDudes.game_visualizer.common.Serializer;
import com.italianDudes.game_visualizer.common.Peer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class ClientListHandler {

    private ClientListHandler(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //List
    private static List<Peer> clientList;

    //Methods
    public static void initList(List<Peer> initClientList){
        clientList = initClientList;
    }
    public static void clearList() {
        for(Peer client : clientList){
            if(!client.getPeerSocket().isClosed()){
                try {
                    client.getPeerSocket().close();
                }catch (IOException ignored){}
            }
        }
        clientList.clear();
    }
    private static boolean isAlive(Peer client) {
        if(client==null)
            return false;
        try {
            Serializer.sendString(client, Defs.PROTOCOL_KEEP_ALIVE);
            KeepAliveRequest keepAliveRequest = new KeepAliveRequest(client);
            Thread keepAliveRequestThread = new Thread(keepAliveRequest);
            keepAliveRequestThread.start();
            TimeUnit.SECONDS.sleep(1);
            keepAliveRequestThread.interrupt();
            if(keepAliveRequest.actualKeepAlive!=null){
                return true;
            }
        }catch (IOException | InterruptedException e){
            return false;
        }
        return false;
    }
    public static int removeDisconnectedClients() {
        List<Peer> newClientList = new ArrayList<>();
        int removedClients = 0;

        for (Peer client : clientList) {
            if (isAlive(client)) {
                newClientList.add(client);
            }else {
                removedClients++;
            }
        }

        clientList = newClientList;

        return removedClients;
    }
    public static void removeClient(Peer client) throws IOException{

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
    public static void addClient(Peer client){
        clientList.add(client);
    }
    public static Peer getClient(int index){
        return clientList.get(index);
    }
    public static int size(){
        return clientList.size();
    }
}
