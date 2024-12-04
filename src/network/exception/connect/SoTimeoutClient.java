package network.exception.connect;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static network.tcp.TcpConst.*;

public class SoTimeoutClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        Socket socket = new Socket(HOST, PORT);
        InputStream input = socket.getInputStream();
        try {
            socket.setSoTimeout(3000); // 소켓 타임아웃(=read 타임아웃)
            int read = input.read(); // setSoTimeout() 가 없으면 디폴트가 "무한 대기"이므로 setSoTimeout() 을 반드시 설정해야 한다.
            System.out.println("read = " + read);
        } catch (Exception e) {
            e.printStackTrace(); // SocketTimeoutException: Read timed out
        }
        socket.close();
    }

}
