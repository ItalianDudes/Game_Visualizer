/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.common.exceptions.socketIO;

@SuppressWarnings("unused")
public class NullStreamException extends NullPointerException{
    public NullStreamException(){
        super("The stream is null.");
    }
}
