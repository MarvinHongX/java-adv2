package network.tcp.v4;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class SessionV4 implements Runnable {
    private final Socket socket;

    public SessionV4(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        DataInputStream input = null;
        DataOutputStream output = null;
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

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
        } finally {
            log("socket connection terminating..." + socket);
            SocketCloseUtil.closeAll(socket, input, output);
        }
    }
}
