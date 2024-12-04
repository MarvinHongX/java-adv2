package network.exception.close.normal;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static network.tcp.TcpConst.*;
import static util.MyLogger.log;

public class NormalCloseServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket = serverSocket.accept();
        log("socket connected: " + socket);

        Thread.sleep(1000);
        socket.close(); // 클라이언트에 FIN 패킷을 보낸다.
        serverSocket.close();
        log("socket closed");
    }
}
