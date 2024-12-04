package network.tcp.v1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static network.tcp.TcpConst.*;
import static util.MyLogger.log;

public class ServerV1 {
    public static void main(String[] args) throws IOException {
        log("server start");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("serverSocket listening on " + PORT);

        Socket socket = serverSocket.accept();
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        log("socket connected: " + socket);

        String received = input.readUTF();
        log("receive: " + received);

        String toSend = received + ", World!";
        output.writeUTF(toSend);
        log("send: " + toSend);

        log("socket connection terminating..." + socket);
        input.close();
        output.close();
        socket.close();
        serverSocket.close();

        log("server end");
    }
}
