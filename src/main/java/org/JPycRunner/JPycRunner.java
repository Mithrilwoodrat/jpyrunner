package org.JPycRunner;

import java.io.*;

public class JPycRunner {
    static int MaxBytes = 1024 * 256;

    public static void main(String[] args) {
        try {
            LoadPycFile("test.pyc");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void LoadPycFile(String filename) throws IOException {
        File f = new File(filename);
        long length = f.length();
        System.out.printf("File Length: %d\n", length);
        try {
            DataInputStream in = new DataInputStream(new BufferedInputStream(
                    new FileInputStream(f)));
            byte[] content = new byte[(int)length];
            FileInputStream fileInputStream = new FileInputStream(f);
            fileInputStream.read(content);
            fileInputStream.close();
            PycFile pyc = new PycFile(content);
            pyc.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



