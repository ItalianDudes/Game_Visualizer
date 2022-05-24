/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.common;

import org.apache.commons.codec.digest.DigestUtils;
import java.io.Serializable;
import java.util.Arrays;

@SuppressWarnings("unused")
public class Credential implements Serializable {

    //Attributes
    private final String username;
    private final String password;

    //Builders
    public Credential(){
        this.username = null;
        this.password = null;
    }
    public Credential(String username, String password, boolean encryptPassword){
        this.username = username;
        if(encryptPassword)
            this.password = DigestUtils.sha512Hex(password);
        else
            this.password = password;
    }
    public Credential(String username, char[] password, boolean encryptPassword){
        this.username = username;
        if(encryptPassword)
            this.password = DigestUtils.sha512Hex(passwordCharArrayToString(password));
        else
            this.password = passwordCharArrayToString(password);
    }
    public Credential(String username, String password){
        this(username,password,true);
    }
    public Credential(String username, char[] password){
        this(username,password,true);
    }

    //Methods
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Credential))
            return false;
        Credential credential = (Credential) o;
        return credential.username.equals(this.username) && this.password.equals(credential.password);
    }
    @Override
    public String toString(){
        return "Username: "+username+"\nPassword: "+password;
    }

    private String passwordCharArrayToString(char[] passwordCharArray){
        return Arrays.toString(passwordCharArray);
    }
}