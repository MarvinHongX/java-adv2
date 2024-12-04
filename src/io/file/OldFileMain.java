package io.file;

import java.io.File;
import java.io.IOException;

public class OldFileMain {
    public static void main(String[] args) throws IOException {
        File file = new File("temp/example.txt");
        File directory = new File("temp/exampleDir");

        System.out.println("file.exists() = " + file.exists());
        System.out.println("file.createNewFile() = " + file.createNewFile());

        System.out.println("directory.mkdir() = " + directory.mkdir());
        System.out.println("file.isFile() = " + file.isFile());
        System.out.println("directory.isDirectory() = " + directory.isDirectory());

        System.out.println("file.getName() = " + file.getName());
        System.out.println("file.length() = " + file.length());

        File newFile = new File("temp/newExample.txt");
        System.out.println("file.renameTo(newFile) = " + file.renameTo(newFile));

        System.out.println("newFile.lastModified() = " + newFile.lastModified());

        System.out.println("newFile.delete() = " + newFile.delete());


    }
}
