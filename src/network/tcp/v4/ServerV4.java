package network.tcp.v4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static network.tcp.TcpConst.PORT;
import static util.MyLogger.log;

public class ServerV4 {
    public static void main(String[] args) throws IOException {
        log("server start");

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            log("serverSocket listening on " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                log("socket connected: " + socket);

                SessionV4 session = new SessionV4(socket);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            log(e);
        } finally {
            serverSocket.close();
        }

        log("server end");
    }
}
