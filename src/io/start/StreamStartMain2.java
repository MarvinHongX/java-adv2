package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain2 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        fos.write(74); // ASCII J
        fos.write(65); // ASCII A
        fos.write(86); // ASCII V
        fos.write(65); // ASCII A
        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        int byteData;
        while ((byteData = fis.read()) != -1) {
            System.out.print((char) byteData);
        }
        fis.close();
    }
}
