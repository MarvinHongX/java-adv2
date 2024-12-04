package network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.TcpConst.*;
import static util.MyLogger.log;

public class ClientV1 {

    public static void main(String[] args) throws IOException {
        log("client start");
        Socket socket = new Socket(HOST, PORT); // 외부와 통신용
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        log("socket connected: " + socket);

        String toSend = "Hello";
        output.writeUTF(toSend);
        log("request: " + toSend);

        String received = input.readUTF();
        log("response: " + received);


        log("socket connection terminating..." + socket);
        input.close();
        output.close();
        socket.close();

        log("client end");

    }
}
