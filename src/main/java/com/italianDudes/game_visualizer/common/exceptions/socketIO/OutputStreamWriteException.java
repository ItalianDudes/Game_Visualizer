package com.italianDudes.game_visualizer.common.exceptions.socketIO;

import java.io.IOException;

public class OutputStreamWriteException extends IOException {
    public OutputStreamWriteException(Throwable cause){
        super("Writing on OutputStream failed.",cause);
    }
}
