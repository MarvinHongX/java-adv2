package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class StreamStartMain4 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        byte[] outputBuffer = {74, 65, 86, 65};
        fos.write(outputBuffer);
        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        byte[] inputBuffer = fis.readAllBytes();

        System.out.println(Arrays.toString(inputBuffer));
        for (int i = 0; i < inputBuffer.length; i++) {
            System.out.print((char) inputBuffer[i]);
        }
        fis.close();
    }
}
