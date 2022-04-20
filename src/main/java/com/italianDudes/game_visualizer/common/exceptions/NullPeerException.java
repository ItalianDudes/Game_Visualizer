/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.common.exceptions;

public class NullPeerException extends NullPointerException{
    public NullPeerException(){
        super("The peer is null.");
    }
}
