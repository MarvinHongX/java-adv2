package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectTimeoutMain1 {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        try {
            Socket socket = new Socket("192.168.1.250", 8080);
        } catch (ConnectException e) {
            // OS 기본 대기 시간(연결 타임아웃)만큼 기다리고 예외 발생
            e.printStackTrace(); // ConnectException: Operation timed out
        }

        long endTime = System.currentTimeMillis();
        System.out.println("elapsedTime = " + (endTime - startTime));
    }
}
