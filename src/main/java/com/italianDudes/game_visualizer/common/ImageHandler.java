package com.italianDudes.game_visualizer.common;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@SuppressWarnings("unused")
public final class ImageHandler {

    private ImageHandler(){
        throw new UnsupportedOperationException("Can't instantiate this class!");
    }

    //Methods
    public static String getImageExtension(String imagePath){

        if (imagePath != null) {
            for (int i = imagePath.length() - 1; i >= 0; i--) {
                if (imagePath.charAt(i) == '.') {
                    return imagePath.substring(i + 1);
                }
            }
        }
        return null;

    }

    public static void sendImage(OutputStream out, String imagePath, final int DIM_BUFFER) throws IOException {

        FileInputStream in = new FileInputStream(imagePath);

        byte[] buffer = new byte[DIM_BUFFER];
        int numLetti = in.read(buffer);
        while( numLetti > 0 ){
            out.write(buffer,0,numLetti);
            numLetti = in.read(buffer);
        }

        in.close();

    }

    public static void sendImage(OutputStream out, String imagePath) throws IOException {

        FileInputStream in = new FileInputStream(imagePath);

        ByteArrayOutputStream outByte = new ByteArrayOutputStream();

        ImageIO.write(readImage(imagePath),getImageExtension(imagePath),outByte);

        DataOutputStream outStream = new DataOutputStream(out);

        outStream.writeInt(outByte.size());
        out.write(outByte.toByteArray(),0,outByte.size());

        in.close();
    }

    public static void sendImage(OutputStream out, File image) throws IOException {

        ByteArrayOutputStream outByte = new ByteArrayOutputStream();

        ImageIO.write(ImageIO.read(image),getImageExtension(image.getName()),outByte);

        DataOutputStream outStream = new DataOutputStream(out);

        outStream.writeInt(outByte.size());
        out.write(outByte.toByteArray(),0,outByte.size());
    }

    public static BufferedImage receiveImage(InputStream in) throws IOException {

        DataInputStream inStream = new DataInputStream(in);

        int size = inStream.readInt();

        byte[] bytes = new byte[size];

        int readedBytes = in.read(bytes,0,size);

        if ( readedBytes == size ) {
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } else {
            return null;
        }

    }

    public static void writeImage(String destinationPath, BufferedImage bufferedImage) throws IOException{

        ImageIO.write(bufferedImage,getImageExtension(destinationPath),new File(destinationPath));

    }

    public static BufferedImage readImage(String imagePath) throws IOException{

        return ImageIO.read(new File(imagePath));
    }

}
