package io.text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.*;

public class ReaderWriterMainV1 {
    public static void main(String[] args) throws IOException {
        String writeString = "JAVA";

        // UTF-8 encoding
        byte[] writeBytes = writeString.getBytes(UTF_8);
        System.out.println("writeString = " + writeString);
        System.out.println("writeBytes = " + Arrays.toString(writeBytes));

        // write file
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        fos.write(writeBytes);
        fos.close();

        // read file
        FileInputStream fis = new FileInputStream(FILE_NAME);
        byte[] readBytes = fis.readAllBytes();

        // UTF-8 decoding
        String readString = new String(readBytes, UTF_8);

        System.out.println("readBytes = " + Arrays.toString(readBytes));
        System.out.println("readString = " + readString);
        fis.close();
    }
}
