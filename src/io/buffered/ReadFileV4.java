package io.buffered;

import java.io.FileInputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

public class ReadFileV4 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();

        byte[] bytes = fis.readAllBytes();
        fis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("file name = " + FILE_NAME);
        System.out.println("file size = " + bytes.length / 1024 / 1024 + "MB");
        System.out.println("time taken = " + (endTime - startTime) + "ms");
    }
}
