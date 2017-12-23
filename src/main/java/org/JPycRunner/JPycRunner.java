package org.JPycRunner;

import sun.security.provider.SHA;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class JPycRunner {
    int MaxBytes = 1024 * 256;
    public static void main(String[] args) {
        try {
            LoadPycFile("test.pyc");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void LoadPycFile(String filename) throws IOException {

        File pyc = new File(filename);
        long length = pyc.length();
        System.out.printf("File Length: %d\n", length);
        try {
            DataInputStream in = new DataInputStream(new BufferedInputStream(
                    new FileInputStream(pyc)));
            byte[] content = new byte[(int)length];
            FileInputStream fileInputStream = new FileInputStream(pyc);
            fileInputStream.read(content);
            fileInputStream.close();
            LoadCodeObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void LoadCodeObject(byte [] content) {
        try {
            byte[] magic = Arrays.copyOfRange(content, 0, 4);
            StringBuffer result = new StringBuffer();
            for (byte b : magic) {
                result.append(String.format("%02X ", b));
                result.append(" "); // delimiter
            }
            System.out.println(result);
            byte[] timestamp = Arrays.copyOfRange(content, 4, 8);
            result = new StringBuffer();
            for (byte b : timestamp) {
                result.append(String.format("%02X ", b));
                result.append(" "); // delimiter
            }
            System.out.println(result);
            long epoch_time = ( ((timestamp[0] & 0xff) |
                    ((timestamp[1]&0xff) << 8) |
                    ((timestamp[2]&0xff) << 16) |
                    ((timestamp[3]&0xff) << 24)));

            System.out.println(epoch_time);
            Date date = new Date(epoch_time*1000);
            DateFormat formatter = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
            String dateFormatted = formatter.format(date);
            System.out.println(dateFormatted);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}



