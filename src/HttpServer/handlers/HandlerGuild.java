package HttpServer.handlers;

import HttpServer.db.DBGuilds;
import HttpServer.db.pojo.Guild;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class HandlerGuild extends Handler {

    @Override
    public void doPost(HttpExchange exchange) {
        System.out.println("POST: " + exchange.getRequestURI());
        Headers requestHeaders = exchange.getRequestHeaders();

        Guild request = null;
        try {
            String s = new String(exchange.getRequestBody().readAllBytes());
            System.out.println(s);
            request = new Gson().fromJson(s, Guild.class);
            new DBGuilds().update(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String response = new Gson().toJson(new Guild("Infinity Tsukuyomi", "810591912107966504"));
            OutputStream outputStream = exchange.getResponseBody();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_CREATED, response.getBytes().length);

            outputStream.write(response.getBytes());
            outputStream.flush();
            outputStream.close();
            exchange.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("close connection...");
    }
}