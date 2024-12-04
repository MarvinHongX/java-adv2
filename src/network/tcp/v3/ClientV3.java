package network.tcp.v3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static network.tcp.TcpConst.HOST;
import static network.tcp.TcpConst.PORT;
import static util.MyLogger.log;

public class ClientV3 {

    public static void main(String[] args) throws IOException {
        log("client start");
        Socket socket = new Socket(HOST, PORT); // 외부와 통신용
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        log("socket connected: " + socket);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String toSend = scanner.nextLine();

            output.writeUTF(toSend);
            log("request: " + toSend);

            if (toSend.equals("exit")) {
                break;
            }

            String received = input.readUTF();
            log("response: " + received);
        }

        log("socket connection terminating..." + socket);
        input.close();
        output.close();
        socket.close();

        log("client end");
    }
}
