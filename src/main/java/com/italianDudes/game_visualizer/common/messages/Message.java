package com.italianDudes.game_visualizer.common.messages;

public abstract class Message extends Throwable{
    public Message(String message){
        super(message);
    }
}
