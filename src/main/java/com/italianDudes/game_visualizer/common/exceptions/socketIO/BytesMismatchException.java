package com.italianDudes.game_visualizer.common.exceptions.socketIO;

import java.io.IOException;

@SuppressWarnings("unused")
public class BytesMismatchException extends IOException {
    public BytesMismatchException(){
        super("Mismatch between bytes red and bytes expected.");
    }
}
