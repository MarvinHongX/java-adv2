package was.v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

public class HttpRequestHandlerV3 implements Runnable {
    private final Socket socket;

    public HttpRequestHandlerV3(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            log(e);
        }
    }

    private void process() throws IOException {
        try (socket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8);
        ) {

            String requestString = requestToString(reader);

            if (requestString.contains("/favicon.ico")) {
                log("request favicon");
                return;
            }

            log("== Print HTTP request ==");
            System.out.println(requestString);

            log("Http response start");
            if (requestString.startsWith("GET /site1 ")) {
                site1(writer);
            } else if (requestString.startsWith("GET /site2 ")) {
                site2(writer);
            } else if (requestString.startsWith("GET /search")) {
                search(writer, requestString);
            } else if (requestString.startsWith("GET / ")) {
                home(writer);
            } else {
                notFound(writer);
            }

            log("Http response end");
        }
    }


    private static void home(PrintWriter writer) {
        String body = new StringBuilder().append("<h1>home</h1>\r\n")
                .append("<ul>\r\n")
                .append("   <li><a href='/site1'>site1</a></li>\r\n")
                .append("   <li><a href='/site2'>site2</a></li>\r\n")
                .append("   <li><a href='/search?q=hello'>search</a></li>\r\n")
                .append("</ul>\r\n").toString();

        int length = body.getBytes(UTF_8).length;
        StringBuilder sb = new StringBuilder();

        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html; charset=UTF-8\r\n");
        sb.append("Content-Length: " + length + "\r\n");
        sb.append("\r\n");
        sb.append(body);

        writer.println(sb);
        writer.flush();
    }

    private void site1(PrintWriter writer) {
        String body = new StringBuilder().append("<h1>site1</h1>\r\n")
                .toString();

        int length = body.getBytes(UTF_8).length;
        StringBuilder sb = new StringBuilder();

        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html; charset=UTF-8\r\n");
        sb.append("Content-Length: " + length + "\r\n");
        sb.append("\r\n");
        sb.append(body);

        writer.println(sb);
        writer.flush();
    }

    private void site2(PrintWriter writer) {
        String body = new StringBuilder().append("<h1>site2</h1>\r\n")
                .toString();

        int length = body.getBytes(UTF_8).length;
        StringBuilder sb = new StringBuilder();

        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html; charset=UTF-8\r\n");
        sb.append("Content-Length: " + length + "\r\n");
        sb.append("\r\n");
        sb.append(body);

        writer.println(sb);
        writer.flush();
    }

    private void search(PrintWriter writer, String requestString) {
        int startIndex = requestString.indexOf("q=");
        int endIndex = requestString.indexOf(" ", startIndex + 2);
        String query = requestString.substring(startIndex + 2, endIndex);
        String queryDecode = URLDecoder.decode(query, UTF_8);


        String body = new StringBuilder().append("<h1>Search</h1>\r\n")
                .append("<ul>\r\n")
                .append("   <li>query: " + queryDecode + "</li>\r\n")
                .append("</ul>\r\n").toString();

        int length = body.getBytes(UTF_8).length;
        StringBuilder sb = new StringBuilder();

        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html; charset=UTF-8\r\n");
        sb.append("Content-Length: " + length + "\r\n");
        sb.append("\r\n");
        sb.append(body);

        writer.println(sb);
        writer.flush();
    }


    private void notFound(PrintWriter writer) {
        String body = new StringBuilder().append("<h1>Not Found 404</h1>\r\n")
                .toString();

        int length = body.getBytes(UTF_8).length;
        StringBuilder sb = new StringBuilder();

        sb.append("HTTP/1.1 404 OK\r\n");
        sb.append("Content-Type: text/html; charset=UTF-8\r\n");
        sb.append("Content-Length: " + length + "\r\n");
        sb.append("\r\n");
        sb.append(body);

        writer.println(sb);
        writer.flush();
    }



    private static String requestToString(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            sb.append(line).append("\n");
        }

        String requestString = sb.toString();
        return requestString;
    }

}
