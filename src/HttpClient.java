import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.*;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpClient {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://localhost:8888/guilds/810591912107966504");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("POST"); // PUT is another valid option
        urlConnection.setDoOutput(true);
        Map<String, String> arguments = new HashMap<>();
        arguments.put("name", "ilos community");
        arguments.put("id", "513111513");

        Type gsonType = new TypeToken<HashMap>() {}.getType();
        String gsonString = new Gson().toJson(arguments, gsonType);

        byte[] out = gsonString.getBytes();
        urlConnection.setFixedLengthStreamingMode(out.length);
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        urlConnection.connect();
        try {
            OutputStream os = urlConnection.getOutputStream();
            os.write(out);
            os.flush();
        } catch (Exception ignored) {

        }
    }
}
