package HttpServer.db.pojo;

import com.google.gson.annotations.SerializedName;

public class Guild {
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;
    @SerializedName("token")
    private String token;

    public Guild(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Guild(String name, String id, String token) {
        this.name = name;
        this.id = id;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
