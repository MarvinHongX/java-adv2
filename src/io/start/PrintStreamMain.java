package io.start;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class PrintStreamMain {
    public static void main(String[] args) throws IOException {
        OutputStream stream = System.out; // OutputStream 을 상속받는다.
        byte[] bytes = "Java!\n".getBytes(UTF_8);

        ((PrintStream) stream).println("Hello"); // PrintStream 이 자체적으로 제공하는 기능
        stream.write(bytes); // OutputStream(부모클래스) 이 제공하는 기능.
    }
}
