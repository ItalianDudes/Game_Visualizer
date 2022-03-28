package com.italianDudes.game_visualizer.common;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class Sheet {
    public abstract Sheet readSheet(String path);
    public abstract void writeSheet(String path);
    public abstract Sheet receiveSheet(InputStream in);
    public abstract void sendSheet(OutputStream out);
}