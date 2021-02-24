package model;

// Represents an account with a website, password, username, and email
public class Account {

    private static int nextAccountId = 1;
    private int id;
    private String password;
    private String username;
    private String email;
    private String website;


    // EFFECTS: constructs an account with an website, password, username, and email
    public Account(String website, String password, String username, String email) {
        this.id = nextAccountId++;
        this.password = password;
        this.username = username;
        this.email = email;
        this.website = website;
    }

    // EFFECTS: returns the account position in the list, 1 being the first position
    public int getId() {
        return id;
    }

    // EFFECTS: returns the account website
    public String getWebsite() {
        return website;
    }

    // EFFECTS: returns the account password
    public String getPassword() {
        return password;
    }

    // EFFECTS: returns the account username
    public String getUsername() {
        return username;
    }

    // EFFECTS: returns the account email
    public String getEmail() {
        return email;
    }

    // MODIFIES: this
    // EFFECTS: sets the account's position
    public void setId(int i) {
        id = i;
    }

    // MODIFIES: this
    // EFFECTS: sets the account's website
    public void setWebsite(String s) {
        website = s;
    }

    // MODIFIES: this
    // EFFECTS: sets the account's password
    public void setPassword(String s) {
        password = s;
    }

    // MODIFIES: this
    // EFFECTS: sets the account's username
    public void setUsername(String s) {
        username = s;
    }

    // MODIFIES: this
    // EFFECTS: sets the account's email
    public void setEmail(String s) {
        email = s;
    }
}
