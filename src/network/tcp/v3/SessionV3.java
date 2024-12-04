package network.tcp.v3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class SessionV3 implements Runnable {
    private final Socket socket;

    public SessionV3(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String received = input.readUTF(); //클라이언트의 연결이 종료되는 등의 원인으로 EOFException 이 발생하면 자원이 정리하지 않고 바로 catch 로 점프할 수 있는 잠재적인 문제가 있음.
                log("receive: " + received);

                if (received.equals("exit")) {
                    break;
                }

                String toSend = received + ", World!";
                output.writeUTF(toSend);
                log("send: " + toSend);
            }

            log("socket connection terminating..." + socket);
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
