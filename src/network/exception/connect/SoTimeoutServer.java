package network.exception.connect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static network.tcp.TcpConst.*;

public class SoTimeoutServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Starting server...");
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket = serverSocket.accept();

        System.out.println("서버가 오랫동안 응답을 하지 않음.");
        Thread.sleep(1000000); // 오랫동안 응답을 안하는 서버를 가정

        socket.close();
        serverSocket.close();
    }
}
