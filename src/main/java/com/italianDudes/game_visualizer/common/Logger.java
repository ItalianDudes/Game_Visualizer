package com.italianDudes.game_visualizer.common;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({"UnusedReturnValue","unused"})
public final class Logger {

    //Attributes
    private static final int MAX_QUEUE_SIZE = 5;
    private static final File latestLogFilePointer = new File(Defs.LOG_LATEST_FILE);
    private static BufferedWriter logger = null;
    private static ExecutorService queue = null;
    private final static LocalDateTime startTime = LocalDateTime.now();

    //Classes
    private static class LogWriter implements Runnable {

        //Attributes
        private final String message;

        //Constructors
        public LogWriter(String message){
            this.message = message;
        }

        //Methods
        @Override
        public void run() {
            System.out.println(message);
            System.out.flush();
            try {
                writeMessageIntoLogFile(message);
            }catch (IOException e){
                System.err.println("Can't write log message into log file!");
            }
        }
    }

    //Methods
    public static boolean initLogger() throws IOException {
        return initLogger(MAX_QUEUE_SIZE);
    }
    public static boolean initLogger(int size) throws IOException {
        File logDirectory = new File(Defs.LOG_DIR);
        if(!logDirectory.exists() || !logDirectory.isDirectory()) {
            if (!logDirectory.mkdir()) {
                System.err.println("Can't create log directory!");
                return false;
            }
        }
        queue = Executors.newFixedThreadPool(size);
        return createLogFile();
    }
    public static void log(String message){
        queue.submit(new LogWriter(message));
    }
    private static boolean saveLog() throws IOException{
        String date = startTime.format(DateTimeFormatter.BASIC_ISO_DATE)+"_"+startTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
        date = date.replaceAll(":","-");
        date = date.split("\\.")[0];
        File newLogDestination = new File(Defs.LOG_DIR+date+".log");
        FileUtils.copyFile(latestLogFilePointer,newLogDestination);
        return true;
    }
    public static boolean closeLogger() throws IOException {

        boolean result;
        queue.shutdown();
        try {
            result = queue.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }catch (InterruptedException e){
            System.err.println("An error has occurred during queue termination");
            result = false;
        }
        if(!result) {
            System.err.println("An error has occurred during queue termination");
        }
        try{
            logger.close();
        }catch (IOException e){
            System.err.println("Can't close log file!");
            throw e;
        }
        return saveLog();
    }
    private static boolean createLogFile() throws IOException {
        try {
            logger = new BufferedWriter(new FileWriter(latestLogFilePointer));
        }catch (IOException e){
            System.err.println("Can't initialize logger!");
            throw e;
        }
        return true;
    }
    private synchronized static void writeMessageIntoLogFile(String message) throws IOException {
        try {
            logger.append("[").append(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).append("] ").append(message).append("\n");
        }catch (IOException e){
            System.err.println("Can't write message into log file!");
            throw e;
        }
        try {
            logger.flush();
        }catch (IOException e){
            System.err.println("Can't flush log file!");
            throw e;
        }
    }
}
