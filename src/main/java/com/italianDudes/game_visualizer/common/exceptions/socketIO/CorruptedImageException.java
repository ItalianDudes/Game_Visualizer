package com.italianDudes.game_visualizer.common.exceptions.socketIO;

import java.io.IOException;

public class CorruptedImageException extends IOException {
    public CorruptedImageException(Throwable cause){
        super("Conversion from byte[] to BufferedImage failed.",cause);
    }
}
