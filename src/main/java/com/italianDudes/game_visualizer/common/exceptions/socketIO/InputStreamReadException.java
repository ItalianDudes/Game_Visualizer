package com.italianDudes.game_visualizer.common.exceptions.socketIO;

import java.io.IOException;

public class InputStreamReadException extends IOException {
    public InputStreamReadException(Throwable cause){
        super("Reading on InputStream failed.",cause);
    }
}
