package network.tcp.v5;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class SessionV5 implements Runnable {
    private final Socket socket;

    public SessionV5(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try (socket;
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            while (true) {
                String received = input.readUTF();
                log("receive: " + received);

                if (received.equals("exit")) {
                    break;
                }

                String toSend = received + ", World!";
                output.writeUTF(toSend);
                log("send: " + toSend);
            }
        } catch (IOException e) {
            log(e);
        }

        log("socket is closed: " + socket + ", closed: " + socket.isClosed());
    }
}
