package model;

// Represents an account with password, username, email, link, and notes
public class Account {

    private static int nextAccountId = 1;
    private int id;
    private String password;
    private String username;
    private String email;
    private String website;


    // REQUIRES: password, username, email, notes are all non-empty strings
    // EFFECTS: constructs an account with password, username, email, and notes
    public Account(String website, String password, String username, String email) {
        this.id = nextAccountId++;
        this.password = password;
        this.username = username;
        this.email = email;
        this.website = website;
    }

    // EFFECTS: return the account position in the list, 1 being the first position
    public int getId() {
        return id;
    }

    // EFFECTS: return the account website
    public String getWebsite() {
        return website;
    }

    // EFFECTS: return the account password
    public String getPassword() {
        return password;
    }

    // EFFECTS: return the account username
    public String getUsername() {
        return username;
    }

    // EFFECTS: return the account email
    public String getEmail() {
        return email;
    }

    // MODIFIES: this
    // EFFECTS: set the account position
    public void setId(int i) {
        id = i;
    }

    // MODIFIES: this
    // EFFECTS: set the account website
    public void setWebsite(String s) {
        website = s;
    }

    // MODIFIES: this
    // EFFECTS: set the account password
    public void setPassword(String s) {
        password = s;
    }

    // MODIFIES: this
    // EFFECTS: set the account username
    public void setUsername(String s) {
        username = s;
    }

    // MODIFIES: this
    // EFFECTS: set the account email
    public void setEmail(String s) {
        email = s;
    }
}
