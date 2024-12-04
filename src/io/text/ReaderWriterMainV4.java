package io.text;

import java.io.*;

import static io.text.TextConst.BUFFER_SIZE;
import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV4 {
    public static void main(String[] args) throws IOException {
        String writeString = "JAVA\n자바";
        System.out.println("== writeString ==");
        System.out.println(writeString);

        // write file
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
        BufferedWriter bw = new BufferedWriter(fw, BUFFER_SIZE);
        bw.write(writeString);
        bw.close();

        // read file
        FileReader fr = new FileReader(FILE_NAME, UTF_8);
        BufferedReader br = new BufferedReader(fr, BUFFER_SIZE);
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();

        String readString = sb.toString();

        System.out.println("== readString ==");
        System.out.println(readString);
    }
}
