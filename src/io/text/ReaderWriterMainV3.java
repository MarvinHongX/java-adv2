package io.text;

import java.io.*;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV3 {
    public static void main(String[] args) throws IOException {
        String writeString = "JAVA";
        System.out.println("writeString = " + writeString);

        // write file
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8);

        fw.write(writeString);
        fw.close();

        // read file
        FileReader fr = new FileReader(FILE_NAME, UTF_8);

        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = fr.read()) != -1) {
            sb.append((char) ch);
        }
        fr.close();

        String readString = sb.toString();
        System.out.println("readString = " + readString);

    }
}
