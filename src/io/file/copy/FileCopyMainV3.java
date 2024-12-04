package io.file.copy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static io.file.copy.CopyConst.*;

public class FileCopyMainV3 {
    public static void main(String[] args) throws IOException {
        Path source = Path.of(FILE_NAME);
        Path target = Path.of(NEW_FILE_NAME);
        long startTime = System.currentTimeMillis();

        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING); // 자바 메모리에 불러오지 않고, OS 수준에서 파일을 복사

        long endTime = System.currentTimeMillis();
        System.out.println("file name = " + FILE_NAME);
        System.out.println("file size = " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("time taken = " + (endTime - startTime) + "ms");
    }
}
