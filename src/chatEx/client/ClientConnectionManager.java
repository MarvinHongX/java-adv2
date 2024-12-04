package chatEx.client;

import chatEx.ConnectionManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ClientConnectionManager implements ConnectionManager {
    private final String host;
    private final int port;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public ClientConnectionManager(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void connect() throws IOException {
        log("Connecting to server...");
        try (Socket socket = new Socket()) {
            this.socket = socket;
            socket.connect(new InetSocketAddress(host, port), 2000);
            log("Connected to server at " + host + ":" + port);

            try (DataInputStream input = new DataInputStream(socket.getInputStream());
                 DataOutputStream output = new DataOutputStream(socket.getOutputStream());) {
                this.input = input;
                this.output = output;

                // 메시지 수신을 위한 리스너를 별도 쓰레드로 실행
                new Thread(new MessageListener()).start();

                interactWithServer();
            }
        }
    }

    // 서버와 메시지를 송수신하는 메서드
    private void interactWithServer() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                String message = scanner.nextLine();

                // 서버로 메시지 전송
                output.writeUTF(message);

                // exit 명령어 처리
                if ("exit".equalsIgnoreCase(message)) {
                    log("Exiting...");
                    break;
                }
            }

        } catch (IOException e) {
            log("Error during communication: " + e.getMessage());
        }
    }

    // 서버로부터 메시지를 비동기적으로 수신하는 리스너 클래스
    private class MessageListener implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    String response = input.readUTF();

                    if (response == null) {
                        log("Connection closed by server.");
                        break;
                    }

                    log(response);
                    System.out.print("> ");

                }
            } catch (IOException e) {
                log("Error in receiving message: " + e.getMessage());
            } finally {
                close();
            }
        }
    }

    @Override
    public void close() {
        cleanupResources();
    }

    private void cleanupResources() {
        try {
            if (input != null) input.close();
        } catch (IOException e) {
            log("Error closing input stream: " + e.getMessage());
        }

        try {
            if (output != null) output.close();
        } catch (IOException e) {
            log("Error closing output stream: " + e.getMessage());
        }

        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                log("Socket closed.");
            }
        } catch (IOException e) {
            log("Error closing socket: " + e.getMessage());
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
