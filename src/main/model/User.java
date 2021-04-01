package model;

import exceptions.DoesNotExistAccount;
import exceptions.EmptyAccountListException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a user with different accounts saved and a login pin

public class User implements Writable {
    private List<Account> accountList;
    private int login;

    // REQUIRES: login is a 4 digit number only
    // EFFECTS: constructs a user with its list of saved accounts and a login password to enter the application
    public User(int login) {
        accountList = new ArrayList<>();
        this.login = login;
    }


    // EFFECTS: returns the account list
    public List<Account> getAccountList() {
        return accountList;
    }

    // EFFECTS: returns the login
    public int getLogin() {
        return login;
    }

    // MODIFIES: this
    // EFFECTS: adds an account to account list
    public void addAccount(Account account) {
        accountList.add(account);
    }

    // MODIFIES: this
    // EFFECTS: removes an account from account list
    public void removeAccount(Account account) throws EmptyAccountListException, DoesNotExistAccount {
        if (accountList.size() <= 0) {
            throw new EmptyAccountListException();
        }

        if (!accountList.contains(account)) {
            throw new DoesNotExistAccount();
        }
        accountList.remove(account);
    }

    // REQUIRES: login is a 4 digit number only
    // EFFECTS: checks if the given i is the same as the user login
    public boolean checkLogin(int i) {
        return i == login;
    }

    // EFFECTS: returns the number of items in the list
    public int size() {
        return accountList.size();
    }

    // EFFECTS: Returns true if Account is in the account list
    //          and false otherwise
    public boolean contains(Account account) {
        return accountList.contains(account);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("login", login);
        json.put("accounts", accountsToJson());
        return json;
    }

    // EFFECTS: returns accounts in this user as a JSON array
    private JSONArray accountsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Account a: accountList) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }
}
