package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class StreamStartMain3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        byte[] outputBuffer = {74, 65, 86, 65};
        fos.write(outputBuffer);
        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        byte[] inputBuffer = new byte[10];
        int readCount = fis.read(inputBuffer, 0, 10);
        System.out.println("readCount = " + readCount);
        System.out.println(Arrays.toString(inputBuffer));
        for (int i = 0; i < readCount; i++) {
            System.out.print((char) inputBuffer[i]);
        }
        fis.close();
    }
}
