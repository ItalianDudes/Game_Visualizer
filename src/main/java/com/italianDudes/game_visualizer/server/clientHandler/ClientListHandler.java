package com.italianDudes.game_visualizer.server.clientHandler;

import com.italianDudes.game_visualizer.common.Defs;
import com.italianDudes.game_visualizer.common.StringHandler;
import com.italianDudes.game_visualizer.common.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class ClientListHandler {

    private ClientListHandler(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //List
    private static List<Client> clientList;

    //Methods
    public static void initList(List<Client> initClientList){
        clientList = initClientList;
    }
    public static void clearList() {
        for(Client client : clientList){
            if(!client.getClientSocket().isClosed()){
                try {
                    client.getClientSocket().close();
                }catch (IOException ignored){}
            }
        }
        clientList.clear();
    }
    private static boolean isAlive(Client client) {
        if(client==null)
            return false;
        try {
            StringHandler.sendString(client.getClientSocket().getOutputStream(), Defs.PROTOCOL_KEEP_ALIVE);
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
        List<Client> newClientList = new ArrayList<>();
        int removedClients = 0;

        for (Client client : clientList) {
            if (isAlive(client)) {
                newClientList.add(client);
            }else {
                removedClients++;
            }
        }

        clientList = newClientList;

        return removedClients;
    }
    public static void removeClient(Client client) throws IOException{

        for(int i=0;i<clientList.size();i++){
            if(clientList.get(i).equals(client)){
                clientList.get(i).getClientSocket().close();
                clientList.set(i,null);
                break;
            }
        }
    }
    public static void printClientList(){
        for (Client client : clientList) System.out.println(client);
    }
    public static boolean isEmpty(){
        return clientList.isEmpty();
    }
    public static void addClient(Client client){
        clientList.add(client);
    }
    public static Client getClient(int index){
        return clientList.get(index);
    }
    public static int size(){
        return clientList.size();
    }
}
