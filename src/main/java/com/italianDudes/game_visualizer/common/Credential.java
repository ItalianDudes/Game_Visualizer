package com.italianDudes.game_visualizer.common;

import java.io.Serializable;

public class Credential implements Serializable {

    //Attributes
    private final String username;
    private final String password;

    //Builders
    public Credential(String username, String password){
        this.username = username;
        this.password = password;
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
        return credential.username.equals(this.password) && credential.password.equals(this.password);
    }
}