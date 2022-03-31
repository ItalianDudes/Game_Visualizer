package com.italianDudes.game_visualizer.common.exceptions.socketIO;

import java.io.IOException;

public class ValidatingStreamException extends IOException {
    public ValidatingStreamException(Throwable cause){
        super("Validating stream failed.",cause);
    }
}
