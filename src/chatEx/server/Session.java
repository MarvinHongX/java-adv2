package chatEx.server;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

import static chatEx.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class Session implements Runnable {
    private final Socket socket;
    private final SessionManager manager;
    private final MessageProcessor messageProcessor;
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private String username;

    public Session(Socket socket, SessionManager manager) throws IOException {
        this.socket = socket;
        this.manager = manager;
        this.manager.add(this);
        this.messageProcessor = new MessageProcessor(socket, this); // Pass the session reference
    }

    @Override
    public void run() {
        try {
            messageProcessor.startProcessing();
        } catch (IOException e) {
            log("Error during message processing: " + e.getMessage());
        } finally {
            close();
        }
    }

    public void close() {
        if (closed.compareAndSet(false, true)) {
            manager.remove(this);
            closeAll(socket, messageProcessor.getInputStream(), messageProcessor.getOutputStream());
            log("Session closed: " + socket);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void broadcastMessage(String message) {
        try {
            messageProcessor.out.writeUTF(message);
        } catch (IOException e) {
            log("Error broadcasting message: " + e.getMessage());
        }
    }

    private static class MessageProcessor {
        private final DataInputStream in;
        private final DataOutputStream out;
        private final Session session; // Reference to the Session object

        public MessageProcessor(Socket socket, Session session) throws IOException {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.session = session; // Store the reference
        }

        public void startProcessing() throws IOException {
            String received;
            while ((received = in.readUTF()) != null) {
                log("Received: " + received);

                if (received.startsWith("/")) {
                    if ("/exit".equalsIgnoreCase(received)) {
                        break;
                    } else if (received.startsWith("/join|")) {
                        String name = received.substring(6); // /join| 부분 제외
                        handleJoin(name);
                    } else if (received.equals("/users")) {
                        handleUsers();
                    } else {
                        out.writeUTF("Unknown command: " + received);
                        log("Sent: Unknown command");
                    }
                } else {
                    handleMessage(received);
                }
            }
        }

        private void handleJoin(String name) throws IOException {
            session.setUsername(name); // Set username in the session object
            out.writeUTF("Welcome " + name + "!");

            String fullMessage = "\'" + session.getUsername() + "\' has joined!";
            session.manager.broadcastMessage(session, fullMessage); // Broadcasting the message
            log("User joined: " + name);
        }

        private void handleMessage(String message) throws IOException {
            if (session.getUsername() == null || session.getUsername().isEmpty()) {
                out.writeUTF("Please join first using /join|{name}");
                return;
            }

            String fullMessage = "[" + session.getUsername() + "] " + message;
            session.manager.broadcastMessage(session, fullMessage); // Broadcasting the message
            log("Sent to all: " + fullMessage);
        }

        private void handleUsers() throws IOException {
            StringBuilder userList = new StringBuilder("Users online: ");
            for (Session s : session.manager.getSessions()) {
                if (s.getUsername() != null) {
                    userList.append(s.getUsername()).append(", ");
                }
            }
            out.writeUTF(userList.toString());
        }

        public DataInputStream getInputStream() {
            return in;
        }

        public DataOutputStream getOutputStream() {
            return out;
        }
    }
}
