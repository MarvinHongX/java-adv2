package io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class NewFilesPathMain {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("temp/..");
        System.out.println("file = " + file);
        System.out.println("file.toAbsolutePath() = " + file.toAbsolutePath());
        System.out.println("file.toRealPath() = " + file.toRealPath());

        Stream<Path> pathStream = Files.list(file);
        List<Path> files = pathStream.toList();
        pathStream.close();

        for (Path f : files) {
            String year = new SimpleDateFormat("yyyy").format(new Date(Files.getLastModifiedTime(f).toMillis()));
            System.out.printf( "%s---------%5s %s\n", (Files.isRegularFile(f) ? "-" : "d"), year, f.getFileName());
        }

    }
}
