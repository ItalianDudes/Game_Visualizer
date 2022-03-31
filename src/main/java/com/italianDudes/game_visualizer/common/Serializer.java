package com.italianDudes.game_visualizer.common;

import java.io.*;

public final class Serializer {

    private Serializer(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //Public Definitions (Invokers)
    public static boolean sendInt(Client client, int number){
        return writeInt(client,number,false);
    }
    public static boolean sendInt(Client client, int number, boolean advancedLog){
        return writeInt(client,number,advancedLog);
    }
    public static boolean sendLong(Client client, long longNumber){
        return writeLong(client,longNumber,false);
    }
    public static boolean sendLong(Client client, long longNumber, boolean advancedLog){
        return writeLong(client,longNumber,advancedLog);
    }
    public static boolean sendFloat(Client client, float floatNumber){
        return writeFloat(client,floatNumber,false);
    }
    public static boolean sendFloat(Client client, float floatNumber, boolean advancedLog){
        return writeFloat(client,floatNumber,advancedLog);
    }
    public static boolean sendDouble(Client client, double doubleNumber){
        return writeDouble(client,doubleNumber,false);
    }
    public static boolean sendDouble(Client client, double doubleNumber, boolean advancedLog){
        return writeDouble(client,doubleNumber,advancedLog);
    }
    public static boolean sendBoolean(Client client, boolean state){
        return writeBoolean(client,state,false);
    }
    public static boolean sendBoolean(Client client, boolean state, boolean advancedLog){
        return writeBoolean(client,state,advancedLog);
    }
    public static boolean sendObject(Client client, Object obj){
        return writeObject(client,obj,false);
    }
    public static boolean sendObject(Client client, Object obj, boolean advancedLog){
        return writeObject(client,obj,advancedLog);
    }

    //Private Definitions
    //Output
    private static boolean writeInt(Client client, int integerNumber, boolean advancedLog) {
        if(isOutputStreamInvalid(client,advancedLog)){
            return false;
        }else{
            DataOutputStream outStream;
            try {
                outStream = new DataOutputStream(client.getClientSocket().getOutputStream());
            }catch (IOException e){
                if(advancedLog)
                    e.printStackTrace();
                return false;
            }
            try{
                outStream.writeInt(integerNumber);
                outStream.flush();
            }catch (IOException e){
                if(advancedLog)
                    e.printStackTrace();
                return false;
            }
            return true;
        }
    }
    private static boolean writeLong(Client client, long longNumber, boolean advancedLog) {
        if(isOutputStreamInvalid(client,advancedLog)){
            return false;
        }else{
            DataOutputStream outStream;
            try {
                outStream = new DataOutputStream(client.getClientSocket().getOutputStream());
            }catch (IOException e){
                if(advancedLog)
                    e.printStackTrace();
                return false;
            }
            try{
                outStream.writeLong(longNumber);
                outStream.flush();
            }catch (IOException e){
                if(advancedLog)
                    e.printStackTrace();
                return false;
            }
            return true;
        }
    }
    private static boolean writeFloat(Client client, float floatNumber, boolean advancedLog) {
        if(isOutputStreamInvalid(client,advancedLog)){
            return false;
        }else{
            DataOutputStream outStream;
            try {
                outStream = new DataOutputStream(client.getClientSocket().getOutputStream());
            }catch (IOException e){
                if(advancedLog)
                    e.printStackTrace();
                return false;
            }
            try{
                outStream.writeFloat(floatNumber);
                outStream.flush();
            }catch (IOException e){
                if(advancedLog)
                    e.printStackTrace();
                return false;
            }
            return true;
        }
    }
    private static boolean writeDouble(Client client, double doubleNumber, boolean advancedLog) {
        if(isOutputStreamInvalid(client,advancedLog)){
            return false;
        }else{
            DataOutputStream outStream;
            try {
                outStream = new DataOutputStream(client.getClientSocket().getOutputStream());
            }catch (IOException e){
                if(advancedLog)
                    e.printStackTrace();
                return false;
            }
            try{
                outStream.writeDouble(doubleNumber);
                outStream.flush();
            }catch (IOException e){
                if(advancedLog)
                    e.printStackTrace();
                return false;
            }
            return true;
        }
    }
    private static boolean writeBoolean(Client client, boolean state, boolean advancedLog) {
        if(isOutputStreamInvalid(client,advancedLog)){
            return false;
        }else{
            DataOutputStream outStream;
            try {
                outStream = new DataOutputStream(client.getClientSocket().getOutputStream());
            }catch (IOException e){
                if(advancedLog)
                    e.printStackTrace();
                return false;
            }
            try{
                outStream.writeBoolean(state);
                outStream.flush();
            }catch (IOException e){
                if(advancedLog)
                    e.printStackTrace();
                return false;
            }
            return true;
        }
    }
    private static boolean writeObject(Client client, Object obj, boolean advancedLog) {
        if(isOutputStreamInvalid(client,advancedLog)){
            return false;
        }else{
            ObjectOutputStream outStream;
            try {
                outStream = new ObjectOutputStream(client.getClientSocket().getOutputStream());
            }catch (IOException e){
                if(advancedLog)
                    e.printStackTrace();
                return false;
            }
            try{
                outStream.writeObject(obj);
                outStream.flush();
            }catch (IOException e){
                if(advancedLog)
                    e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    //Check Streams Integrity
    private static boolean isOutputStreamInvalid(Client client, boolean advancedLog){
        if(client==null)
            return true;
        OutputStream outStream;
        try{
            outStream = client.getClientSocket().getOutputStream();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            return true;
        }
        return outStream == null;
    }
    private static boolean isInputStreamInvalid(Client client, boolean advancedLog){
        if(client==null)
            return true;
        InputStream inStream;
        try{
            inStream = client.getClientSocket().getInputStream();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            return true;
        }
        return inStream == null;
    }
}
