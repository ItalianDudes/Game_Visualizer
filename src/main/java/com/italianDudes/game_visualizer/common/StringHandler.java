/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class StringHandler {

    private StringHandler(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    @Deprecated
    public static void sendString(OutputStream out, String str) throws IOException {

        byte[] byteStr = str.getBytes();
        DataOutputStream dataOut = new DataOutputStream(out);

        dataOut.writeInt(byteStr.length);
        dataOut.write(byteStr);
        dataOut.flush();
        out.flush();
    }

    @Deprecated
    public static String receiveString(InputStream in) throws IOException{

        DataInputStream dataIn = new DataInputStream(in);

        int length = dataIn.readInt();

        if(length>0){
            byte[] byteStr = new byte[length];
            dataIn.readFully(byteStr,0,byteStr.length);
            return new String(byteStr);
        }
        return null;
    }

    public static int getOccurrencesFromString(String str, char car){

        int occorrenze = 0;

        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==car)
                occorrenze++;
        }

        return occorrenze;
    }

    public static boolean checkUserValidity(String username){
        for(int i=0;i<username.length();){
            if(username.charAt(i)>='0' && username.charAt(i)<='9'){
                i++;
            }else if(username.charAt(i)>='A' && username.charAt(i)<='Z'){
                i++;
            }else if(username.charAt(i)>='a' && username.charAt(i)<='z') {
                i++;
            }else if(username.charAt(i)==' '){
                i++;
            }else{
                return false;
            }
        }
        return true;
    }

    public static String[] splitStringSpaceIncluded(String comando){
        List<String> commandPipeline = new ArrayList<>();

        String[] comandoAsset = comando.split(" ");

        int index=0;

        String buffer;

        while(index<comandoAsset.length){
            List<String> composition = new ArrayList<>();
            if(comandoAsset[index].contains("\"") && getOccurrencesFromString(comandoAsset[index],'\"')%2!=0){
                buffer = comandoAsset[index];
                buffer = buffer.replaceAll("\"","");
                composition.add(buffer);
                try {
                    do {
                        index++;
                        buffer = comandoAsset[index];
                        buffer = buffer.replaceAll("\"", "");
                        composition.add(buffer);
                    } while (!comandoAsset[index].contains("\""));
                }catch (ArrayIndexOutOfBoundsException outOfBoundsException){
                    System.out.println("Error during splitting string: missing \"");
                    return null;
                }
                StringBuilder str = new StringBuilder();
                for(String string : composition){
                    str.append(string).append(" ");
                }
                String newStr = str.toString();
                newStr = newStr.substring(0,newStr.length()-1);
                commandPipeline.add(newStr);
            }else if (comandoAsset[index].contains("\"") && getOccurrencesFromString(comandoAsset[index],'\"')%2==0){
                buffer = comandoAsset[index];
                buffer = buffer.replaceAll("\"","");
                commandPipeline.add(buffer);
            }else{
                commandPipeline.add(comandoAsset[index]);
            }
            index++;
        }
        for (String s : commandPipeline) {
            System.out.print(s + " ");
        }
        System.out.println();
        return commandPipeline.toArray(new String[0]);
    }

}