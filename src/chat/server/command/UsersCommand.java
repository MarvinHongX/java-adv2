package chat.server.command;

import chat.server.Session;
import chat.server.SessionManager;

import java.io.IOException;
import java.util.List;

public class UsersCommand implements Command {
    private final SessionManager sessionManager;

    public UsersCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {
        List<String> Users = sessionManager.getAllUsers();
        StringBuilder sb = new StringBuilder();
        sb.append("total active user: ").append(Users.size()).append("\n");
        for (String user : Users) {
            sb.append(" - ").append(user).append("\n");
        }
        session.send(sb.toString());
    }
}
