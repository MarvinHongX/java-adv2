package network.tcp.v5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Path;
import java.util.Scanner;

import static network.tcp.SocketCloseUtil.closeAll;
import static network.tcp.TcpConst.HOST;
import static network.tcp.TcpConst.PORT;
import static util.MyLogger.log;

public class ClientV5 {

    public static void main(String[] args) throws IOException {
        log("client start");

        try (Socket socket = new Socket(HOST, PORT);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {
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
        }

        log("client end");
    }
}
