package com.italianDudes.game_visualizer.common.exceptions.socketIO;

public class NullStreamException extends NullPointerException{
    public NullStreamException(){
        super("The stream is null.");
    }
}
