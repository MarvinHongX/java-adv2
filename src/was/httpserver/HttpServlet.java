package was.httpserver;

import java.io.IOException;

public interface HttpServlet {
    // HTTP , Server, Applet
    void service(HttpRequest request, HttpResponse response) throws IOException;
}
