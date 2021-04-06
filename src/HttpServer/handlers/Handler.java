package HttpServer.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import kotlin.text.Charsets;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public abstract class Handler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Serving the request");
        switch (exchange.getRequestMethod()) {
            case "POST" -> doPost(exchange);
            case "GET" -> {
                handleGet(exchange);
                doGet(exchange);
            }
        }
    }

    public void doGet(HttpExchange exchange) throws IOException {

    }

    public void doPost(HttpExchange exchange) throws IOException {

    }

    public static Map<String, String> parseGetRequest(HttpExchange httpExchange) {
        Map<String, String> args = new HashMap<>();
        for (String s : httpExchange.getRequestURI().toString().split("\\?")[1].split("&")) {
            args.put(s.split("=")[0], s.split("=")[1]);
        }
        return args;
    }

    public void handleGet(HttpExchange exchange) throws IOException {
        String content = Files.readString(Path.of("api" + exchange.getRequestURI().getPath() + ".json"), Charsets.UTF_8);
        OutputStream outputStream = exchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<body style=\"background-color: #111\">");
        htmlBuilder.append("<h1 style=\"font-family: monospace;color: #ffffff;\">");
        htmlBuilder.append(content);
        htmlBuilder.append("</h1>");
        htmlBuilder.append("</body>");

        exchange.sendResponseHeaders(200, htmlBuilder.toString().length());

        outputStream.write(htmlBuilder.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
