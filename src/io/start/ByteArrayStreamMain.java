package io.start;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayStreamMain {
    public static void main(String[] args) throws IOException {
        byte[] outputBuffer = {1, 2, 3};

        // 메모리에 쓰기
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(outputBuffer);


        // 메모리에서 읽기
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        byte[] inputBuffer = bis.readAllBytes();

        for (int i = 0; i < inputBuffer.length; i++) {
            System.out.print(inputBuffer[i]);
        }
        bos.close();
        bis.close();

    }
}
