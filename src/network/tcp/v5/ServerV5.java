package network.tcp.v5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static network.tcp.TcpConst.PORT;
import static util.MyLogger.log;

public class ServerV5 {
    public static void main(String[] args) throws IOException {
        log("server start");

        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            log("serverSocket listening on " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                log("socket connected: " + socket);

                SessionV5 session = new SessionV5(socket);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            log(e);
        }

        log("server end");
    }
}
