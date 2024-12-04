package io.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OldFilePathMain {
    public static void main(String[] args) throws IOException {
        File file = new File("temp/..");
        System.out.println("file.getPath() = " + file.getPath());
        System.out.println("file.getAbsoluteFile() = " + file.getAbsoluteFile());
        System.out.println("file.getCanonicalPath() = " + file.getCanonicalPath()); // 계산이 끝난 경로로 하나만 존재

        File[] files = file.listFiles();
        for (File f : files) {
            String year = new SimpleDateFormat("yyyy").format(new Date(f.lastModified()));
            System.out.printf( "%s---------%5s %s\n", (f.isFile() ? "-" : "d"), year, f.getName());
        }

    }
}
