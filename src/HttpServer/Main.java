package HttpServer;

import HttpServer.handlers.HandlerGuild;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
        server.createContext("/guilds", new HandlerGuild());
        server.setExecutor(null);
        server.start();
    }
}