package com.italianDudes.game_visualizer.client.utility;

import com.italianDudes.game_visualizer.GVSingleton;
import com.italianDudes.game_visualizer.Game_Visualizer;
import com.italianDudes.gvedk.common.Logger;

import java.io.IOException;

public class ExtDescriptorTask {

    private boolean isExecuting;
    private final String extInternalName;
    private final int extInternalId;

    private final ExtLaunchTask extLaunchTask;

    public ExtDescriptorTask(String extInternalName, int extInternalId, String extPathToMain, String jarName, String OS_ROOT) throws IOException {
        this.extInternalName=extInternalName;
        this.extInternalId=extInternalId;
        this.extLaunchTask=new ExtLaunchTask(extInternalName,extPathToMain,jarName, OS_ROOT);
    }

    public String getExtInternalName(){
        return extInternalName;
    }
    public int getExtInternalId(){
        return extInternalId;
    }

    public void launchTask() throws InterruptedException {
        if(isExecuting){
            Logger.logWithCaller("Launch Task of "+extInternalName+" Extension is already running. Can't instantiate another Extension's launch task");
        }else{
            try{
                extLaunchTask.start();
                isExecuting=true;
                GVSingleton.getInstance().incExecutingTasks();
                Logger.logWithCaller("The Extension has been launched");

                extLaunchTask.join();
                isExecuting=false;
                GVSingleton.getInstance().decExecutingTasks();
                Logger.logWithCaller("The Extension has finished its execution");
            }catch(Exception e){
                Logger.logWithCaller("For unknown reasons, the Extension couldn't be launched");
            }
        }
    }
    public void requestToBeKilled(){
        if(isExecuting){
            extLaunchTask.interrupt();
            Logger.logWithCaller("The Extension "+extInternalName+" has just been interrupted");
        }else{
            Logger.logWithCaller("The Extension "+extInternalName+" was already interrupted. No need to be killed");
        }
    }
}
