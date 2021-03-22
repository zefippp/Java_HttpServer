package HttpServer.handlers;

import HttpServer.db.DBGuilds;
import HttpServer.db.pojo.Guild;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import kotlin.text.Charsets;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HandlerGuild extends Handler {

    @Override
    public void doPost(HttpExchange exchange) throws IOException {
        System.out.println(exchange.getRequestURI());
        Headers requestHeaders = exchange.getRequestHeaders();

        int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));
        String s = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Map<String, String> map = new Gson().fromJson(s, HashMap.class);
        //new DBGuilds().insert(new Guild(map.get("name"), map.get("id")));
        new DBGuilds().remove(new Guild(map.get("name"), map.get("id")));

        byte[] data = new byte[contentLength];

        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, contentLength);

        OutputStream os = exchange.getResponseBody();

        os.write(data);
        os.flush();
        exchange.close();
    }
}