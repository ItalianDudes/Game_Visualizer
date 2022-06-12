package com.italianDudes.game_visualizer;

import com.italianDudes.game_visualizer.client.utility.ExtDescriptorTask;
import com.italianDudes.gvedk.common.*;
import com.italianDudes.gvedk.common.error.os.UnsupportedOSError;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Objects;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

/**
 * This Singleton contains all the information shared by the whole launcher. It is thread-safe and, following the Singleton logic,
 * can be instantiated only once.
 * It contains:
 *
 * - the options' loading task;
 * - the launcher's options;
 */
public class GVSingleton {

    //This is the Singleton's instance
    private static GVSingleton instance;

    private GVSingleton() throws IOException {

        OS_ROOT = System.getProperty("user.home")+"/";
        if(OSUtils.isLinux()){
            OS_ROOT+=".Game_Visualizer/";
        }else if(OSUtils.isWindows()){
            OS_ROOT+="AppData/Roaming/.Game_Visualizer/";
        }else{
            throw new UnsupportedOSError("This OS is not supported yet");
        }

        if (checkAndInitializeRoutine()){
            configLoadingTask();
        }else{
            throw new AccessDeniedException("The program couldn't install its components correctly due to a folder access issue");
        }

        executingTasks=0;
        taskDescriptors=new ArrayList<>();
        extLoadingTask();
        Logger.logWithCaller("The ExtLoadingTask has just finished its routine");
        Logger.logWithCaller("No Extension has yet began to run");
    }

    //Here there are all the variables' instances inside the Singleton
    private String OS_ROOT;
    private ArrayList<Property> configs;
    private File optionsFile;
    private ArrayList<JarFile> exts;
    private ArrayList<ExtDescriptorTask> taskDescriptors;

    private ArrayList<Attributes> extsAttributes;
    private int executingTasks;

    synchronized public static GVSingleton getInstance() throws IOException {
        if(instance==null){
            instance=new GVSingleton();
        }
        return instance;
    }

    /**
     * This method checks the integrity of the Game Visualizer's folder system and repairs it if needed.
     *
     * @return it returns whether the method was able to complete its routine.
     * @throws IOException
     */
    public boolean checkAndInitializeRoutine() throws IOException {
        boolean isSuccessful = true;


        if(DirectoryHandler.createDirectory(OS_ROOT) || DirectoryHandler.directoryExist(new File(OS_ROOT))){
            Logger.logWithCaller(OS_ROOT+" either has just been created or already existed");

            //Checks the test dir
            if(DirectoryHandler.createDirectory(OS_ROOT+Game_Visualizer.Defs.TEST_DIR) || DirectoryHandler.directoryExist(new File(OS_ROOT+Game_Visualizer.Defs.TEST_DIR))){
                Logger.logWithCaller(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+" either has just been created or already existed");
                optionsFile = new File(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+"options.cfg");

                if(!optionsFile.exists()){
                    Logger.logWithCaller(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+optionsFile.getName()+" file not found");
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(optionsFile));

                    bufferedWriter.write("launcherOpenedBack=false");
                    bufferedWriter.flush();

                    bufferedWriter.close();
                    Logger.logWithCaller(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+optionsFile.getName()+" file just created");
                }else{
                    Logger.logWithCaller(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+optionsFile.getName()+" file found");
                }

            }else{
                Logger.logWithCaller(OS_ROOT+Game_Visualizer.Defs.TEST_DIR+": the routine was unable to complete its task!");
                isSuccessful=false;
            }

            if(isSuccessful && (!DirectoryHandler.createDirectory(OS_ROOT+Game_Visualizer.Defs.EXTENSIONS_DIR) && !DirectoryHandler.directoryExist(new File(OS_ROOT+Game_Visualizer.Defs.EXTENSIONS_DIR)))){
                isSuccessful=false;
            }
        }else{
            Logger.logWithCaller(OS_ROOT+": the routine was unable to complete its task!");
            isSuccessful=false;
        }

        return isSuccessful;
    }

    public void configLoadingTask() throws IOException {
        String line;
        boolean isLauncherOpenedBack = false;

        if(configs==null){
            Logger.logWithCaller("The settings are being loaded for the first time");
            configs = new ArrayList<>();
        }else{
            Logger.logWithCaller("Updating the settings");
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(optionsFile));

        while((line = bufferedReader.readLine()) != null){
            if(line.contains("launcherOpenedBack")){
                isLauncherOpenedBack = Boolean.parseBoolean(line.split(Game_Visualizer.Defs.REGEX)[1].trim());
                Logger.logWithCaller("The launcher will remain opened in background: "+isLauncherOpenedBack);
            }
        }

        configs.add(new Property("launcherOpenedBack",String.valueOf(isLauncherOpenedBack)));

        bufferedReader.close();
        Logger.logWithCaller("ConfigLoadingTask finished its routine");
    }

    public void extLoadingTask() throws IOException {
        Logger.logWithCaller("The ExtLoadingTask has started");
        File pointerToExtFolder = new File(OS_ROOT+Game_Visualizer.Defs.EXTENSIONS_DIR);

        File[] fileList = pointerToExtFolder.listFiles();

        if(fileList!=null){
            if(exts==null){
                Logger.logWithCaller("First time loading the Extensions");
                exts = new ArrayList<>();
                extsAttributes = new ArrayList<>();
            }else{
                Logger.logWithCaller("Extensions already loaded: must be updated");
            }
            for(int i = 0; i< Objects.requireNonNull(fileList).length; i++){
                if(isJarFile(fileList[i])){
                    JarFile tempFile = new JarFile(fileList[i].getAbsolutePath());

                    if(isExt(tempFile)){
                        exts.add(tempFile);
                        extsAttributes.add(tempFile.getManifest().getMainAttributes());

                        Logger.logWithCaller("Extension added");
                    }

                    tempFile.close();
                }
            }
            if(exts.isEmpty()){
                Logger.logWithCaller("No extension found");
            }else{
                for(int internalId=extsAttributes.size()-1;internalId>=0;internalId--){
                    taskDescriptors.add(new ExtDescriptorTask(
                            extsAttributes.get(internalId).getValue(Game_Visualizer.Defs.MANIFEST_EXT_NAME_ENTRY).trim().replaceAll(" ","_"),
                            internalId,extsAttributes.get(internalId).getValue(Game_Visualizer.Defs.MANIFEST_MAIN_ENTRY)));
                }

                Logger.logWithCaller("Available Extensions fully loaded and updated");
            }
        }else{
            Logger.logWithCaller("No Extension found in the "+OS_ROOT+Game_Visualizer.Defs.EXTENSIONS_DIR+" folder");
            Logger.logWithCaller("No Extension was loaded");
        }
    }
    public ArrayList<Attributes> getExtsAttributes() {
        return extsAttributes;
    }

    public boolean isJarFile(File file) throws IOException {
        Logger.logWithCaller("Checking whether the selected file is a Jar file or not");
        BasicFileAttributes basicFileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        if(basicFileAttributes.isDirectory()){
            Logger.logWithCaller("The selected file ("+file.getName()+") is a directory");
            return false;
        }
        if(!file.canRead()){
            Logger.logWithCaller("The selected file ("+file.getName()+") can't be read");
            return false;
        }
        if(basicFileAttributes.size() <4){
            Logger.logWithCaller("The selected file ("+file.getName()+") isn't big enough to be a JarFile");
            return false;
        }
        DataInputStream in = new DataInputStream(new BufferedInputStream(Files.newInputStream(file.toPath())));
        int test = in.readInt();
        Logger.logWithCaller("The file id has just been read");
        in.close();

        if(test==Game_Visualizer.Defs.ZIP_ID){
            ZipFile zipFile = new ZipFile(file);
            boolean existBoolean = zipFile.getEntry(Game_Visualizer.Defs.MANIFEST_ENTRY) != null;
            if(existBoolean){
                Logger.logWithCaller("The file is a JarFile");
            }else{
                Logger.logWithCaller("The file is a ZipFile");
            }
            zipFile.close();
            return existBoolean;
        }

        Logger.logWithCaller("The file is neither a JarFile nor a ZipFile");
        return false;
    }

    public boolean isExt(JarFile file) throws IOException {
        Attributes mfAt = ManifestReader.readJarManifest(file);

        Logger.logWithCaller("Manifest's attributes: "+mfAt.getValue(Game_Visualizer.Defs.MANIFEST_MAIN_ENTRY)+" "+mfAt.getValue(Game_Visualizer.Defs.MANIFEST_EXT_NAME_ENTRY)+" "+mfAt.get(Game_Visualizer.Defs.MANIFEST_AUTH_ENTRY)+" "+mfAt.getValue(Game_Visualizer.Defs.MANIFEST_DATE_ENTRY));


        if(ManifestReader.containsKey(mfAt,Game_Visualizer.Defs.MANIFEST_MAIN_ENTRY) && (ManifestReader.getValue(mfAt,Game_Visualizer.Defs.MANIFEST_MAIN_ENTRY)!=null)){
            Logger.logWithCaller("The Manifest contains the Main Entry and that entry is not null");
            if(ManifestReader.containsKey(mfAt,Game_Visualizer.Defs.MANIFEST_EXT_NAME_ENTRY) && (ManifestReader.getValue(mfAt,Game_Visualizer.Defs.MANIFEST_EXT_NAME_ENTRY)!=null)){
                Logger.logWithCaller("The Manifest contains the Extension's Name Entry and that entry is not null");
                if(ManifestReader.containsKey(mfAt,Game_Visualizer.Defs.MANIFEST_AUTH_ENTRY) && (ManifestReader.getValue(mfAt,Game_Visualizer.Defs.MANIFEST_AUTH_ENTRY)!=null)){
                    Logger.logWithCaller("The Manifest contains the Author and that entry is not null");
                    if(ManifestReader.containsKey(mfAt,Game_Visualizer.Defs.MANIFEST_DATE_ENTRY) && (ManifestReader.getValue(mfAt,Game_Visualizer.Defs.MANIFEST_DATE_ENTRY)!=null)){
                        Logger.logWithCaller("The Manifest contains the Date Entry and that entry is not null");
                        Logger.logWithCaller("The JarFile has been validated: it's an Extension");
                        return true;
                    }else{
                        Logger.logWithCaller("Debugging: mfAt.getValue(Game_Visualizer.Defs.MANIFEST_DATE_ENTRY)= "+(ManifestReader.getValue(mfAt,Game_Visualizer.Defs.MANIFEST_EXT_NAME_ENTRY)!=null ? ManifestReader.getValue(mfAt,Game_Visualizer.Defs.MANIFEST_EXT_NAME_ENTRY) : "null"));
                        Logger.logWithCaller("The Manifest either doesn't contain the Date Entry or that entry is null");
                    }
                }else{
                    Logger.logWithCaller("Debugging: mfAt.getValue(Game_Visualizer.Defs.MANIFEST_AUTH_ENTRY)= "+(ManifestReader.getValue(mfAt,Game_Visualizer.Defs.MANIFEST_AUTH_ENTRY)!=null ? ManifestReader.getValue(mfAt,Game_Visualizer.Defs.MANIFEST_AUTH_ENTRY) : "null"));
                    Logger.logWithCaller("The Manifest either doesn't contain the Author or that entry is null");
                }
            }else{
                Logger.logWithCaller("Debugging: mfAt.getValue(Game_Visualizer.Defs.MANIFEST_EXT_NAME_ENTRY)= "+(ManifestReader.getValue(mfAt,Game_Visualizer.Defs.MANIFEST_EXT_NAME_ENTRY)!=null ? ManifestReader.getValue(mfAt,Game_Visualizer.Defs.MANIFEST_EXT_NAME_ENTRY) : "null"));
                Logger.logWithCaller("The Manifest either doesn't contain the Extension's Name Entry or that entry is null");
            }
        }else{
            Logger.logWithCaller("Debugging: mfAt.getValue(Game_Visualizer.Defs.MANIFEST_MAIN_ENTRY)= "+(ManifestReader.getValue(mfAt,Game_Visualizer.Defs.MANIFEST_MAIN_ENTRY)!=null?ManifestReader.getValue(mfAt,Game_Visualizer.Defs.MANIFEST_MAIN_ENTRY):"null"));
            Logger.logWithCaller("The Manifest either doesn't contain the Main Entry or that entry is null");
        }
        Logger.logWithCaller("The JarFile has been validated: it's not an Extension");
        return false;
    }

    public void killExts(){
        if(!taskDescriptors.isEmpty()){
            for (ExtDescriptorTask taskDescriptor : taskDescriptors) {
                taskDescriptor.requestToBeKilled();
            }
            taskDescriptors=new ArrayList<>();
        }
    }

    public ArrayList<ExtDescriptorTask> getTaskDescriptors() {
        return taskDescriptors;
    }
    public int getExecutingTasks(){
        return executingTasks;
    }
    public void incExecutingTasks(){
        if(executingTasks<taskDescriptors.size()){
            executingTasks++;
            Logger.logWithCaller("Number of executing tasks increased to: "+executingTasks);
        }else{
            Logger.logWithCaller("All tasks are already running!");
        }
    }
    public void decExecutingTasks(){
        if(executingTasks>0){
            executingTasks--;
            Logger.logWithCaller("Number of executing tasks decreased to: "+executingTasks);
        }else{
            Logger.logWithCaller("Not a single task is running!");
        }
    }
}
