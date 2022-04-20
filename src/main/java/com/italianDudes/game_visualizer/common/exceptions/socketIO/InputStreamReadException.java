/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.common.exceptions.socketIO;

import java.io.IOException;

public class InputStreamReadException extends IOException {
    public InputStreamReadException(Throwable cause){
        super("Reading on InputStream failed.",cause);
    }
}
