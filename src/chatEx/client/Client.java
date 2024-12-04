package chatEx.client;

import chatEx.ConnectionManager;
import chatEx.NetworkComponent;
import chatEx.ShutdownHookManager;

import java.io.IOException;

import static chatEx.ChatConst.*;
import static util.MyLogger.log;

public class Client implements NetworkComponent {
    private final ConnectionManager connectionManager;
    private final ShutdownHookManager shutdownHookManager;

    public Client(String host, int port) {
        this.connectionManager = new ClientConnectionManager(host, port);
        this.shutdownHookManager = new ShutdownHookManager(connectionManager);
    }

    @Override
    public void start() {
        log("Client started");

        // 종료 시 클린업을 위한 Shutdown Hook 등록
        shutdownHookManager.add();

        try {
            connectionManager.connect();
        } catch (IOException e) {
            log("Error: " + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void close() {
        shutdownHookManager.run();
        log("Client terminated");
    }

    public static void main(String[] args) {
        Client client = new Client(HOST, PORT);
        client.start();
    }
}
