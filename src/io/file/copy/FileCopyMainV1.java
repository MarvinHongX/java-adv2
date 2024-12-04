package io.file.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.file.copy.CopyConst.*;

public class FileCopyMainV1 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        FileOutputStream fos = new FileOutputStream(NEW_FILE_NAME);
        long startTime = System.currentTimeMillis();

        byte[] buffer = fis.readAllBytes();
        fos.write(buffer);

        fis.close();
        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("file name = " + FILE_NAME);
        System.out.println("file size = " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("time taken = " + (endTime - startTime) + "ms");
    }
}
