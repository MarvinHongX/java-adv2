package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ConnectTimeoutMain2 {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("192.168.1.250", 8080), 1000);
        } catch (SocketTimeoutException e) {
            // 1초 기다리고 예외 발생
            e.printStackTrace(); // SocketTimeoutException: Connect timed out
        }

        long endTime = System.currentTimeMillis();
        System.out.println("elapsedTime = " + (endTime - startTime));
    }
}
