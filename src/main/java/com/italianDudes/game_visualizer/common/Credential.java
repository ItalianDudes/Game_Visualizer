/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.common;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.util.Arrays;

public class Credential implements Serializable {

    //Attributes
    private final String username;
    private final String password;

    //Builders
    public Credential(){
        this.username = null;
        this.password = null;
    }
    public Credential(String username, String password){
        this.username = username;
        this.password = DigestUtils.sha512Hex(password);
    }
    public Credential(String username, char[] password){
        this.username = username;
        this.password = DigestUtils.sha512Hex(passwordCharArrayToString(password));
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
        if(!(o instanceof Credential))
            return false;
        Credential credential = (Credential) o;
        return credential.username.equals(this.username) && this.password.equals(credential.password);
    }

    private String passwordCharArrayToString(char[] passwordCharArray){
        return Arrays.toString(passwordCharArray);
    }
}