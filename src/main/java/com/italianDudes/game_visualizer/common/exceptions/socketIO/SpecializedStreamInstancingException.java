/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.common.exceptions.socketIO;

import java.io.IOException;

public class SpecializedStreamInstancingException extends IOException {
    public SpecializedStreamInstancingException(Throwable cause){
        super("Instancing specialized stream failed.",cause);
    }
}
