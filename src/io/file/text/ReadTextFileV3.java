package io.file.text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;


import static java.nio.charset.StandardCharsets.UTF_8;

public class ReadTextFileV3 {
    public static final String FILE_NAME = "temp/hello2.txt";

    public static void main(String[] args) throws IOException {
        String writeString = "JAVA\n자바";
        System.out.println("== writeString ==");
        System.out.println(writeString);

        // write file
        Files.writeString(Path.of(FILE_NAME), writeString, UTF_8);

        // read file
        StringBuilder sb = new StringBuilder();

        // try-with-resources 로 Stream 을 자동으로 닫는다.
        try (Stream<String> lineStream = Files.lines(Path.of(FILE_NAME), UTF_8)) {
            lineStream.forEach(line -> sb.append(line).append("\n"));
        }
        String readString = sb.toString();

        System.out.println("== readString ==");
        System.out.println(readString);
    }
}
