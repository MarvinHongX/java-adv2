package chatEx.server;

import chatEx.ConnectionManager;
import chatEx.NetworkComponent;
import chatEx.ShutdownHookManager;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static chatEx.ChatConst.*;
import static util.MyLogger.log;

public class Server implements NetworkComponent {
    private final ExecutorService threadPool;
    private final SessionManager sessionManager;
    private final ConnectionManager connectionManager;
    private final ShutdownHookManager shutdownHookManager;

    public Server(int port, int threadPoolSize) {
        this.threadPool = Executors.newFixedThreadPool(threadPoolSize);
        this.sessionManager = new SessionManager();
        this.connectionManager = new ServerConnectionManager(port, sessionManager, threadPool);
        this.shutdownHookManager = new ShutdownHookManager(connectionManager);
    }

    @Override
    public void start() {
        log("Server started on port " + PORT);

        // 종료 시 클린업을 위한 Shutdown Hook 등록
        shutdownHookManager.add();

        try {
            connectionManager.connect();
        } catch (IOException e) {
            log("Server error: " + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void close() {
        shutdownHookManager.run();
        log("Server terminated");
    }

    public static void main(String[] args) {
        int threadPoolSize = 2; // 스레드 풀 크기
        Server server = new Server(PORT, threadPoolSize);
        server.start();
    }
}
