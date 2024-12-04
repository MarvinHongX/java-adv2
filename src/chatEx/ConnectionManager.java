package chatEx;

import java.io.IOException;

public interface ConnectionManager {
    void connect() throws IOException;
    void close() throws IOException;
}
