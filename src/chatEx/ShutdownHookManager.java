package chatEx;

import static util.MyLogger.log;

public class ShutdownHookManager {

    private final ConnectionManager connectionManager;

    public ShutdownHookManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    // 종료 시 호출될 Shutdown Hook 등록
    public void add() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log("Shutdown hook triggered");
            run();
        }, "ShutdownThread"));
    }

    // shutdown 실행
    public void run() {
        try {
            log("Shutting down...");
            connectionManager.close();
            log("Shutdown complete");
        } catch (Exception e) {
            log("Error during shutdown: " + e.getMessage());
        }
    }
}
