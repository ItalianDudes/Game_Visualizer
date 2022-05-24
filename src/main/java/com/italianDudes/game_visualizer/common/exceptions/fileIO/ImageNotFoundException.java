package com.italianDudes.game_visualizer.common.exceptions.fileIO;

import java.io.FileNotFoundException;

@SuppressWarnings("unused")
public class ImageNotFoundException extends FileNotFoundException {
    public ImageNotFoundException(Exception e){
        super(e.getMessage());
    }
}
