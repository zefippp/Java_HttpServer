package HttpServer.handlers;

import HttpServer.db.DBGuilds;
import HttpServer.db.pojo.Guild;
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
                try {
                    System.out.println(new DBGuilds().get("810591912107966504").get("name"));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                String content = Files.readString(Path.of("api" + exchange.getRequestURI().getPath() + ".json"), Charsets.UTF_8);
                System.out.println("content:\n" + content);
                Map<String, String> args = parseGetRequest(exchange);
                System.out.println(Arrays.toString(new Map[]{args}));
                handleResponse(exchange, content);
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

    public void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<body style=\"background-color: #111\">");
        htmlBuilder.append("<h1 style=\"font-family: monospace;color: #ffffff;\">");
        htmlBuilder.append(requestParamValue);
        htmlBuilder.append("</h1>");
        htmlBuilder.append("</body>");

        httpExchange.sendResponseHeaders(200, htmlBuilder.toString().length());

        outputStream.write(htmlBuilder.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
