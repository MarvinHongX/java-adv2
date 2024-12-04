package chatEx.server;

import chatEx.ConnectionManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import static util.MyLogger.log;

public class ServerConnectionManager implements ConnectionManager {
    private final int port;
    private final SessionManager sessionManager;
    private final ExecutorService threadPool;
    private ServerSocket serverSocket;

    public ServerConnectionManager(int port, SessionManager sessionManager, ExecutorService threadPool) {
        this.port = port;
        this.sessionManager = sessionManager;
        this.threadPool = threadPool;
    }

    @Override
    public void connect() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            this.serverSocket = serverSocket;
            log("Server is listening on port " + port);

            while (true) {
                acceptClient();
            }
        }
    }

    // 클라이언트 연결 수락 및 처리
    private void acceptClient() {
        try {
            Socket clientSocket = serverSocket.accept();
            log("Client connected: " + clientSocket);

            Session session = new Session(clientSocket, sessionManager);
            threadPool.execute(session); // 세션을 스레드 풀에 제출
        } catch (IOException e) {
            log("Error accepting client: " + e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        sessionManager.closeAll();

        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }
    }
}
