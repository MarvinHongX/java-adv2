package io.file.text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ReadTextFileV1 {
    public static final String FILE_NAME = "temp/hello2.txt";

    // io.text.ReaderWriterMainV1.java 와 비교
    public static void main(String[] args) throws IOException {
        String writeString = "JAVA\n자바";
        System.out.println("== writeString ==");
        System.out.println(writeString);

        // write file
        Files.writeString(Path.of(FILE_NAME), writeString, UTF_8);

        // read file
        String readString = Files.readString(Path.of(FILE_NAME), UTF_8);

        System.out.println("== readString ==");
        System.out.println(readString);
    }
}