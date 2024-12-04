package network.tcp.v3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static network.tcp.TcpConst.PORT;
import static util.MyLogger.log;

public class ServerV3 {
    public static void main(String[] args) throws IOException {
        log("server start");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("serverSocket listening on " + PORT);

        while (true) {
            Socket socket = serverSocket.accept();
            log("socket connected: " + socket);

            SessionV3 session = new SessionV3(socket);
            Thread thread = new Thread(session);
            thread.start();
        }
//        serverSocket.close();
//        log("server end");
    }
}
