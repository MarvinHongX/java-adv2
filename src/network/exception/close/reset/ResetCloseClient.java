package network.exception.close.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static network.tcp.TcpConst.*;
import static util.MyLogger.log;

public class ResetCloseClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket(HOST, PORT);
        log("socket connected: " + socket);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // client <- server: FIN
        Thread.sleep(1000); // 서버가 close() 호출할 때 까지 대기

        // client -> server: PUSH[1] (FIN 를 무시하고 계속 메세지를 보낸다면?)
        output.write(1);

        // client <- server : RST (해당 연결을 더 이상 사용하면 안된다는 의미)
        Thread.sleep(1000); // RST 메세지 받을 때 까지 대기

        try {
            int read = input.read(); // RST 받았는데도 read() 호출하면?
            log("read: " + read);
        } catch (SocketException e) {
            e.printStackTrace(); // SocketException: Connection reset
        }


        try {
            output.write(1); // RST 받았는데도 write() 호출하면?
        } catch (SocketException e) {
            e.printStackTrace(); // SocketException: Broken pipe
        }
    }
}
