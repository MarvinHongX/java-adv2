package chat.server;

import java.io.IOException;

public interface CommandManager {
    void execute(String command, Session session) throws IOException;

}
