package com.italianDudes.game_visualizer.common;

import com.italianDudes.game_visualizer.common.exceptions.NullPeerException;
import com.italianDudes.game_visualizer.common.exceptions.socketIO.InputStreamReadException;
import com.italianDudes.game_visualizer.common.exceptions.socketIO.OutputStreamWriteException;
import com.italianDudes.game_visualizer.common.exceptions.socketIO.SpecializedStreamInstancingException;
import com.italianDudes.game_visualizer.common.exceptions.socketIO.ValidatingStreamException;

import java.io.*;

@SuppressWarnings("unused")
public final class Serializer {

    private Serializer(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //Public Definitions (Invokers): Sender
    public static void sendInt(Peer peer, int number) throws OutputStreamWriteException, SpecializedStreamInstancingException, ValidatingStreamException {
        writeInt(peer,number,false);
    }
    public static void sendInt(Peer peer, int number, boolean advancedLog) throws OutputStreamWriteException, SpecializedStreamInstancingException, ValidatingStreamException {
        writeInt(peer,number,advancedLog);
    }
    public static void sendLong(Peer peer, long longNumber) throws OutputStreamWriteException, SpecializedStreamInstancingException, ValidatingStreamException {
        writeLong(peer,longNumber,false);
    }
    public static void sendLong(Peer peer, long longNumber, boolean advancedLog) throws OutputStreamWriteException, SpecializedStreamInstancingException, ValidatingStreamException {
        writeLong(peer,longNumber,advancedLog);
    }
    public static void sendFloat(Peer peer, float floatNumber) throws OutputStreamWriteException, SpecializedStreamInstancingException, ValidatingStreamException {
        writeFloat(peer,floatNumber,false);
    }
    public static void sendFloat(Peer peer, float floatNumber, boolean advancedLog) throws OutputStreamWriteException, SpecializedStreamInstancingException, ValidatingStreamException {
        writeFloat(peer,floatNumber,advancedLog);
    }
    public static void sendDouble(Peer peer, double doubleNumber) throws OutputStreamWriteException, SpecializedStreamInstancingException, ValidatingStreamException {
        writeDouble(peer,doubleNumber,false);
    }
    public static void sendDouble(Peer peer, double doubleNumber, boolean advancedLog) throws OutputStreamWriteException, SpecializedStreamInstancingException, ValidatingStreamException {
        writeDouble(peer,doubleNumber,advancedLog);
    }
    public static void sendBoolean(Peer peer, boolean state) throws OutputStreamWriteException, SpecializedStreamInstancingException, ValidatingStreamException {
        writeBoolean(peer,state,false);
    }
    public static void sendBoolean(Peer peer, boolean state, boolean advancedLog) throws OutputStreamWriteException, SpecializedStreamInstancingException, ValidatingStreamException {
        writeBoolean(peer,state,advancedLog);
    }
    public static void sendString(Peer peer, String str) throws OutputStreamWriteException, SpecializedStreamInstancingException, ValidatingStreamException {
        writeString(peer,str,false);
    }
    public static void sendString(Peer peer, String str, boolean advancedLog) throws OutputStreamWriteException, SpecializedStreamInstancingException, ValidatingStreamException {
        writeString(peer,str,advancedLog);
    }
    public static void sendObject(Peer peer, Object obj) throws OutputStreamWriteException, SpecializedStreamInstancingException, ValidatingStreamException, NotSerializableException {
        writeObject(peer,obj,false);
    }
    public static void sendObject(Peer peer, Object obj, boolean advancedLog) throws OutputStreamWriteException, SpecializedStreamInstancingException, ValidatingStreamException, NotSerializableException {
        writeObject(peer,obj,advancedLog);
    }

    //Public Definitions (Invokers): Receiver
    public static int receiveInt(Peer peer) throws SpecializedStreamInstancingException, InputStreamReadException, ValidatingStreamException {
        return readInt(peer,false);
    }
    public static int receiveInt(Peer peer, boolean advancedLog) throws SpecializedStreamInstancingException, InputStreamReadException, ValidatingStreamException {
        return readInt(peer,advancedLog);
    }
    public static long receiveLong(Peer peer) throws SpecializedStreamInstancingException, InputStreamReadException, ValidatingStreamException {
        return readLong(peer,false);
    }
    public static long receiveLong(Peer peer, boolean advancedLog) throws SpecializedStreamInstancingException, InputStreamReadException, ValidatingStreamException {
        return readLong(peer,advancedLog);
    }
    public static float receiveFloat(Peer peer) throws SpecializedStreamInstancingException, InputStreamReadException, ValidatingStreamException {
        return readFloat(peer,false);
    }
    public static float receiveFloat(Peer peer, boolean advancedLog) throws SpecializedStreamInstancingException, InputStreamReadException, ValidatingStreamException {
        return readFloat(peer,advancedLog);
    }
    public static double receiveDouble(Peer peer) throws SpecializedStreamInstancingException, InputStreamReadException, ValidatingStreamException {
        return readDouble(peer,false);
    }
    public static double receiveDouble(Peer peer, boolean advancedLog) throws SpecializedStreamInstancingException, InputStreamReadException, ValidatingStreamException {
        return readDouble(peer,advancedLog);
    }
    public static boolean receiveBoolean(Peer peer) throws SpecializedStreamInstancingException, InputStreamReadException, ValidatingStreamException {
        return readBoolean(peer,false);
    }
    public static boolean receiveBoolean(Peer peer, boolean advancedLog) throws SpecializedStreamInstancingException, InputStreamReadException, ValidatingStreamException {
        return readBoolean(peer,advancedLog);
    }
    public static String receiveString(Peer peer) throws SpecializedStreamInstancingException, InputStreamReadException, ValidatingStreamException {
        return readString(peer,false);
    }
    public static String receiveString(Peer peer, boolean advancedLog) throws SpecializedStreamInstancingException, InputStreamReadException, ValidatingStreamException {
        return readString(peer,advancedLog);
    }
    public static Object receiveObject(Peer peer) throws SpecializedStreamInstancingException, InputStreamReadException, ValidatingStreamException, ClassNotFoundException {
        return readObject(peer,false);
    }
    public static Object receiveObject(Peer peer, boolean advancedLog) throws SpecializedStreamInstancingException, InputStreamReadException, ValidatingStreamException, ClassNotFoundException {
        return readObject(peer,advancedLog);
    }

    //Private Definitions: Output
    private static void writeInt(Peer peer, int integerNumber, boolean advancedLog) throws ValidatingStreamException, OutputStreamWriteException, SpecializedStreamInstancingException {
        try {
            checkOutputStreamValidity(peer, advancedLog);
        }catch (NullPeerException e){
            if(advancedLog)
                e.printStackTrace();
            throw e;
        }
        DataOutputStream outStream;
        try {
            outStream = new DataOutputStream(peer.getPeerSocket().getOutputStream());
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new SpecializedStreamInstancingException(e);
        }
        try{
            outStream.writeInt(integerNumber);
            outStream.flush();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new OutputStreamWriteException(e);
        }
    }
    private static void writeLong(Peer peer, long longNumber, boolean advancedLog) throws ValidatingStreamException, SpecializedStreamInstancingException, OutputStreamWriteException {
        try {
            checkOutputStreamValidity(peer, advancedLog);
        }catch (NullPeerException e){
            if(advancedLog)
                e.printStackTrace();
            throw e;
        }
        DataOutputStream outStream;
        try {
            outStream = new DataOutputStream(peer.getPeerSocket().getOutputStream());
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new SpecializedStreamInstancingException(e);
        }
        try{
            outStream.writeLong(longNumber);
            outStream.flush();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new OutputStreamWriteException(e);
        }
    }
    private static void writeFloat(Peer peer, float floatNumber, boolean advancedLog) throws ValidatingStreamException, SpecializedStreamInstancingException, OutputStreamWriteException {
        try {
            checkOutputStreamValidity(peer, advancedLog);
        }catch (NullPeerException e){
            if(advancedLog)
                e.printStackTrace();
            throw e;
        }
        DataOutputStream outStream;
        try {
            outStream = new DataOutputStream(peer.getPeerSocket().getOutputStream());
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new SpecializedStreamInstancingException(e);
        }
        try{
            outStream.writeFloat(floatNumber);
            outStream.flush();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new OutputStreamWriteException(e);
        }
    }
    private static void writeDouble(Peer peer, double doubleNumber, boolean advancedLog) throws ValidatingStreamException, SpecializedStreamInstancingException, OutputStreamWriteException {
        try {
            checkOutputStreamValidity(peer, advancedLog);
        }catch (NullPeerException e){
            if(advancedLog)
                e.printStackTrace();
            throw e;
        }
        DataOutputStream outStream;
        try {
            outStream = new DataOutputStream(peer.getPeerSocket().getOutputStream());
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new SpecializedStreamInstancingException(e);
        }
        try{
            outStream.writeDouble(doubleNumber);
            outStream.flush();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new OutputStreamWriteException(e);
        }
    }
    private static void writeBoolean(Peer peer, boolean state, boolean advancedLog) throws ValidatingStreamException, SpecializedStreamInstancingException, OutputStreamWriteException {
        try {
            checkOutputStreamValidity(peer, advancedLog);
        }catch (NullPeerException e){
            if(advancedLog)
                e.printStackTrace();
            throw e;
        }
        DataOutputStream outStream;
        try {
            outStream = new DataOutputStream(peer.getPeerSocket().getOutputStream());
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new SpecializedStreamInstancingException(e);
        }
        try{
            outStream.writeBoolean(state);
            outStream.flush();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new OutputStreamWriteException(e);
        }
    }
    private static void writeString(Peer peer, String str, boolean advancedLog) throws ValidatingStreamException, SpecializedStreamInstancingException, OutputStreamWriteException {
        try {
            checkOutputStreamValidity(peer, advancedLog);
        }catch (NullPeerException e){
            if(advancedLog)
                e.printStackTrace();
            throw e;
        }
        DataOutputStream outStream;
        try {
            outStream = new DataOutputStream(peer.getPeerSocket().getOutputStream());
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new SpecializedStreamInstancingException(e);
        }
        byte[] byteStr = str.getBytes();
        try {
            outStream.writeInt(byteStr.length);
            outStream.write(byteStr);
            outStream.flush();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new OutputStreamWriteException(e);
        }
    }
    private static void writeObject(Peer peer, Object obj, boolean advancedLog) throws ValidatingStreamException, SpecializedStreamInstancingException, OutputStreamWriteException, NotSerializableException {
        if(!(obj instanceof Serializable))
            throw new NotSerializableException(obj.getClass().getCanonicalName());
        try {
            checkOutputStreamValidity(peer, advancedLog);
        }catch (NullPeerException e){
            if(advancedLog)
                e.printStackTrace();
            throw e;
        }
        ObjectOutputStream outStream;
        try {
            outStream = new ObjectOutputStream(peer.getPeerSocket().getOutputStream());
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new SpecializedStreamInstancingException(e);
        }
        try{
            outStream.writeObject(obj);
            outStream.flush();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new OutputStreamWriteException(e);
        }
    }

    //Private Definitions: Input
    private static int readInt(Peer peer, boolean advancedLog) throws ValidatingStreamException, SpecializedStreamInstancingException, InputStreamReadException {
        try {
            checkInputStreamValidity(peer, advancedLog);
        }catch (NullPeerException e){
            if(advancedLog)
                e.printStackTrace();
            throw e;
        }
        DataInputStream inStream;
        try {
            inStream = new DataInputStream(peer.getPeerSocket().getInputStream());
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new SpecializedStreamInstancingException(e);
        }
        int integerNumber;
        try{
            integerNumber = inStream.readInt();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new InputStreamReadException(e);
        }
        return integerNumber;
    }
    private static long readLong(Peer peer, boolean advancedLog) throws ValidatingStreamException, SpecializedStreamInstancingException, InputStreamReadException {
        try {
            checkInputStreamValidity(peer, advancedLog);
        }catch (NullPeerException e){
            if(advancedLog)
                e.printStackTrace();
            throw e;
        }
        DataInputStream inStream;
        try {
            inStream = new DataInputStream(peer.getPeerSocket().getInputStream());
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new SpecializedStreamInstancingException(e);
        }
        long longNumber;
        try{
            longNumber = inStream.readLong();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new InputStreamReadException(e);
        }
        return longNumber;
    }
    private static float readFloat(Peer peer, boolean advancedLog) throws ValidatingStreamException, SpecializedStreamInstancingException, InputStreamReadException {
        try {
            checkInputStreamValidity(peer, advancedLog);
        }catch (NullPeerException e){
            if(advancedLog)
                e.printStackTrace();
            throw e;
        }
        DataInputStream inStream;
        try {
            inStream = new DataInputStream(peer.getPeerSocket().getInputStream());
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new SpecializedStreamInstancingException(e);
        }
        float floatNumber;
        try{
            floatNumber = inStream.readFloat();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new InputStreamReadException(e);
        }
        return floatNumber;
    }
    private static double readDouble(Peer peer, boolean advancedLog) throws ValidatingStreamException, SpecializedStreamInstancingException, InputStreamReadException {
        try {
            checkInputStreamValidity(peer, advancedLog);
        }catch (NullPeerException e){
            if(advancedLog)
                e.printStackTrace();
            throw e;
        }
        DataInputStream inStream;
        try {
            inStream = new DataInputStream(peer.getPeerSocket().getInputStream());
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new SpecializedStreamInstancingException(e);
        }
        double doubleNumber;
        try{
            doubleNumber = inStream.readDouble();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new InputStreamReadException(e);
        }
        return doubleNumber;
    }
    private static boolean readBoolean(Peer peer, boolean advancedLog) throws ValidatingStreamException, SpecializedStreamInstancingException, InputStreamReadException {
        try {
            checkInputStreamValidity(peer, advancedLog);
        }catch (NullPeerException e){
            if(advancedLog)
                e.printStackTrace();
            throw e;
        }
        DataInputStream inStream;
        try {
            inStream = new DataInputStream(peer.getPeerSocket().getInputStream());
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new SpecializedStreamInstancingException(e);
        }
        boolean state;
        try{
            state = inStream.readBoolean();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new InputStreamReadException(e);
        }
        return state;
    }
    private static String readString(Peer peer, boolean advancedLog) throws ValidatingStreamException, SpecializedStreamInstancingException, InputStreamReadException {
        try {
            checkInputStreamValidity(peer, advancedLog);
        }catch (NullPeerException e){
            if(advancedLog)
                e.printStackTrace();
            throw e;
        }
        DataInputStream inStream;
        try {
            inStream = new DataInputStream(peer.getPeerSocket().getInputStream());
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new SpecializedStreamInstancingException(e);
        }
        int length;
        byte[] byteStr;

        try {
            length = inStream.readInt();
            if (length > 0) {
                byteStr = new byte[length];
                inStream.readFully(byteStr, 0, byteStr.length);
                return new String(byteStr);
            }else {
                return null;
            }
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new InputStreamReadException(e);
        }
    }
    private static Object readObject(Peer peer, boolean advancedLog) throws ValidatingStreamException, SpecializedStreamInstancingException, InputStreamReadException, ClassNotFoundException {
        try {
            checkInputStreamValidity(peer, advancedLog);
        }catch (NullPeerException e){
            if(advancedLog)
                e.printStackTrace();
            throw e;
        }
        ObjectInputStream inStream;
        try {
            inStream = new ObjectInputStream(peer.getPeerSocket().getInputStream());
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new SpecializedStreamInstancingException(e);
        }
        Object obj;
        try{
            obj = inStream.readObject();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new InputStreamReadException(e);
        } catch (ClassNotFoundException e) {
            if (advancedLog)
                e.printStackTrace();
            throw e;
        }
        return obj;
    }

    //Check Streams Integrity
    private static void checkOutputStreamValidity(Peer peer, boolean advancedLog) throws NullPeerException, ValidatingStreamException {
        if(peer==null)
            throw new NullPeerException();
        try{
            peer.getPeerSocket().getOutputStream();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new ValidatingStreamException(e);
        }
    }
    private static void checkInputStreamValidity(Peer peer, boolean advancedLog) throws NullPeerException, ValidatingStreamException {
        if(peer==null)
            throw new NullPeerException();
        try{
            peer.getPeerSocket().getInputStream();
        }catch (IOException e){
            if(advancedLog)
                e.printStackTrace();
            throw new ValidatingStreamException(e);
        }
    }
}
