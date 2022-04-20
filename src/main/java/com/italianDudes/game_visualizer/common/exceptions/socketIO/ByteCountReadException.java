package com.italianDudes.game_visualizer.common.exceptions.socketIO;

import java.io.IOException;

public class ByteCountReadException extends IOException {
    public ByteCountReadException(Throwable cause){
        super("Reading numBytes on InputStream failed.",cause);
    }
}
