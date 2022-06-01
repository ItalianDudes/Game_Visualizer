/*
 *  Copyright (C) 2022 ItalianDudes
 *  Software distributed under the GPLv3 license
 */
package com.italianDudes.game_visualizer.common;

import com.italianDudes.game_visualizer.Game_Visualizer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
public final class DirectoryHandler { //TODO: Finish DirectoryHandler

    //Constructors
    private DirectoryHandler(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //Methods
    public static boolean createDirectory(String directoryName){
        File directoryPointer = new File(directoryName);
        if(!directoryPointer.exists())
            return directoryPointer.mkdir();
        else
            return false;
    }
    public static boolean deleteDirectory(String directoryPath){
        if(directoryPath==null)
            return false;
        File directoryPointer = new File(directoryPath);
        return deleteDirectory(directoryPointer);
    }
    public static boolean deleteDirectory(File directory){
        if(directory==null)
            return false;
        if(directory.exists() && directory.isDirectory()){
            try{
                FileUtils.deleteDirectory(directory);
                return true;
            }catch (IOException e){
                return false;
            }
        }else {
            return false;
        }
    }
    public static boolean createExtensionsFolder(){
        return createDirectory(Game_Visualizer.Defs.EXTENSIONS_DIR);
    }
    public static boolean createExtensionDirectory(String extensionID){
        return createDirectory(Game_Visualizer.Defs.EXTENSIONS_DIR+extensionID+"/");
    }
    public static boolean deleteExtensionDirectory(String extensionID){
        return deleteDirectory(Game_Visualizer.Defs.EXTENSIONS_DIR+extensionID+"/");
    }


}
