package HttpServer.db;

import HttpServer.db.pojo.Guild;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kotlin.text.Charsets;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DBGuilds implements DB {
    @Override
    public void insert(Guild guild) throws IOException {
        File file = new File("api/guilds/" + guild.getId() + ".json");
        FileWriter fileWriter = new FileWriter(file.getCanonicalPath());
        fileWriter.write(new Gson().toJson(guild));
        fileWriter.flush();
        fileWriter.close();
    }

    @Override
    public void remove(Guild guild) {
        File file = new File("api/guilds/" + guild.getId() + ".json");
        file.delete();
    }

    @Override
    public void getAll() {

    }

    @Override
    public Map<String, String> get(String id) throws IOException {
        String string = Files.readString(Path.of("api/guilds/" + id + ".json"), Charsets.UTF_8);

        return new Gson().fromJson(string, HashMap.class);
    }

    @Override
    public void update(Guild _guild) {
        File file = new File("api/guilds/" + _guild.getId() + ".json");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file.getCanonicalPath());
            JsonObject jsonObject = (JsonObject) new JsonParser().parse(new FileReader(file.getCanonicalPath()));
            System.out.println((String) jsonObject.get("name"));
            ArrayList<Guild> guilds = new Gson().fromJson(Files.readAllLines(Path.of("api/guilds/" + _guild.getId() + ".json")), ArrayList.class);
            for (Guild guild : guilds) {
                System.out.println(guild.getName());
                if (guild.getId().equals(_guild.getId())) {
                    System.out.println("equals");
                    guilds.remove(guild);
                    guilds.add(_guild);
                }
            }
            System.out.println(Files.readAllLines(Path.of("api/guilds/" + _guild.getId() + ".json")));
            fileWriter.write(new Gson().toJson(guilds));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
