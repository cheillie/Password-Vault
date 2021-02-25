package persistence;

import model.Account;
import model.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads User from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses User from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        int login = jsonObject.getInt("login");
        User user = new User(login);
        addAccounts(user, jsonObject);
        return user;
    }

    private void addAccounts(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addAccount(user, nextAccount);
        }
    }

    private void addAccount(User user, JSONObject jsonObject) {
        //int id = jsonObject.getInt("id");
        String website = jsonObject.getString("website");
        String password = jsonObject.getString("password");
        String username = jsonObject.getString("username");
        String email = jsonObject.getString("email");
        Account account = new Account(website, password, username, email);
        //account.setId(id);
        user.addAccount(account);
    }

}
