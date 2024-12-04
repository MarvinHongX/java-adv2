package network.tcp.v4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static network.tcp.SocketCloseUtil.closeAll;
import static network.tcp.TcpConst.HOST;
import static network.tcp.TcpConst.PORT;
import static util.MyLogger.log;

public class ClientV4 {

    public static void main(String[] args) throws IOException {
        log("client start");

        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;

        try {
            socket = new Socket(HOST, PORT);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
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

        } catch (IOException e) {
            log(e);
        } finally {
            log("socket connection terminating..." + socket);
            closeAll(socket, input, output);
        }

        log("client end");
    }
}
