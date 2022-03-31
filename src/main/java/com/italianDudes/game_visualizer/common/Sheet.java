package com.italianDudes.game_visualizer.common;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public abstract class Sheet implements Serializable {

    //Attributes
    private final Credential userCredential;

    //Constructors
    public Sheet(Credential userCredential){
        this.userCredential = userCredential;
    }

    //Methods
    public Credential getUserCredential(){
        return userCredential;
    }

    //Abstract Methods
    public abstract Sheet readSheet(String path);
    public abstract void writeSheet(String path);
    public abstract Sheet receiveSheet(InputStream in);
    public abstract void sendSheet(OutputStream out);
}