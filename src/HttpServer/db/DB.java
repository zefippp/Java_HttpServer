package HttpServer.db;

import HttpServer.db.pojo.Guild;

import java.io.IOException;
import java.util.Map;

public interface DB {
    void insert(Guild guild) throws IOException;

    void remove(Guild guild);

    void getAll();

    Map<String, String> get(String id) throws IOException;

    void update(Guild guild) throws IOException;
}
