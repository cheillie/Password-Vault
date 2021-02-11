package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Account> accountList;
    private int login;

    // REQUIRES: login is a 4 digit number only
    // EFFECTS: constructs a user with its list of saved accounts and a login password to enter the application
    public User(int login) {
        accountList = new ArrayList<>();
        this.login = login;
    }

    // MODIFIES: this
    // EFFECTS: add an account to account list
    public void addAccount(Account account) {
        accountList.add(account);
    }

    // REQUIRES: account list is not empty and account is an element of the account list
    // MODIFIES: this
    // EFFECTS: remove an account from account list
    public void removeAccount(Account account) {
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
    // and false otherwise
    public boolean contains(Account account) {
        return accountList.contains(account);
    }
}
