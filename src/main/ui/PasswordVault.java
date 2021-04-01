package ui;

import exceptions.DoesNotExistAccount;
import exceptions.EmptyAccountListException;
import model.Account;
import model.User;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

// Represents the Password Vault application

public class PasswordVault {

    private static final String JSON_STORE = "./data/user.json";
    private int login;
    private Scanner input;
    private User user;
    private boolean keepGoing = true;
    private boolean isLogin = true;
    boolean stillRunning = true;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the Password Vault application
    public PasswordVault() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runVault();
    }

    // MODIFIES: this
    // EFFECTS: processes the user input
    public void runVault() {
        String command = null;
        input = new Scanner(System.in);

        loginLoop();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: creates a loop for the login page
    public void loginLoop() {
        String command = null;

        while (isLogin) {
            loginMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("3")) {
                isLogin = false;
                keepGoing = false;
            } else {
                processLogin(command);
            }
        }
    }

    // EFFECTS: displays menu of options to user
    private void loginMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Create your login");
        System.out.println("\t2 -> Enter existing login");
        System.out.println("\t3 -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processLogin(String command) {
        if (command.equals("1")) {
            init();
        } else if (command.equals("2")) {
            loadUser();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes user
    private void init() {
        user = new User(createLogin());
    }

    // MODIFIES: this
    // EFFECTS: crates a login password in the login menu page
    private int createLogin() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a new 4-digit password:");
        String s = input.next();

        while (!isValidLogin(s)) {
            System.out.println("4 digit number!!!");
            s = input.next();
        }

        isLogin = false;
        return Integer.parseInt(s);
    }

    // EFFECTS: checks if the given login is 4 digit numbers only
    private boolean isValidLogin(String input) {
        if (input.length() != 4) {
            return false;
        }
        try {
            Integer i = Integer.parseInt(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> add account");
        System.out.println("\t2 -> edit account");
        System.out.println("\t3 -> delete account");
        System.out.println("\t4 -> view account detail");
        System.out.println("\t5 -> view all");
        System.out.println("\t-------------------------");
        System.out.println("\ts -> save");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            saveAccount();
        } else if (command.equals("2")) {
            stillRunning = true;
            editAccountChooseId();
        } else if (command.equals("3")) {
            deleteAccount();
        } else if (command.equals("4")) {
            viewAccount();
        } else if (command.equals("5")) {
            viewAll();
        } else if (command.equals("s")) {
            saveUser();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to save an Account
    private void saveAccount() {
        System.out.println("Enter website:");
        String website = input.next();

        System.out.println("Enter password:");
        String password = input.next();

        System.out.println("Enter username:");
        String username = input.next();

        System.out.println("Enter email:");
        String email = input.next();

        Account account = new Account(website, password, username, email);
        user.addAccount(account);

        System.out.println("Account saved successfully!");
    }

    // MODIFIES: this
    // EFFECTS: allows users to choose the Account they want to edit
    private void editAccountChooseId() {
        boolean canFind = false;
        if (viewAll()) {
            System.out.println("Enter the account ID you wish to edit:");
            String s = input.next();

            for (Account a : user.getAccountList()) {
                if (matchIdAndInput(a, s)) {
                    canFind = true;
                    printInfo(a);
                    editAccountMenu(a);
                    break;
                }
            }
            if (!canFind) {
                System.out.println("Cannot find account ID");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: displays edit menu of options to user
    private void editAccountMenu(Account a) {
        while (stillRunning) {
            System.out.println("\nSelect from:");
            System.out.println("\t1 -> edit Website");
            System.out.println("\t2 -> edit Password");
            System.out.println("\t3 -> edit Username");
            System.out.println("\t4 -> edit Email");
            System.out.println("\t5 -> back");

            String command = input.next();

            processEditAccount(command, a);
        }
    }

    // MODIFIES: this
    // EFFECTS: process user command for edit account menu
    private void processEditAccount(String command, Account a) {
        if (command.equals("1")) {
            editWebsite(a);
        } else if (command.equals("2")) {
            editPassword(a);
        } else if (command.equals("3")) {
            editUsername(a);
        } else if (command.equals("4")) {
            editEmail(a);
        } else if (command.equals("5")) {
            stillRunning = false;
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to edit account website
    private void editWebsite(Account a) {
        System.out.println("Enter new Website:");
        a.setWebsite(input.next());
        System.out.println("Website changed successfully!");
        printInfo(a);
    }

    // MODIFIES: this
    // EFFECTS: allows user to edit account password
    private void editPassword(Account a) {
        System.out.println("Enter new Password:");
        a.setPassword(input.next());
        System.out.println("Password changed successfully!");
        printInfo(a);
    }

    // MODIFIES: this
    // EFFECTS: allows user to edit account username
    private void editUsername(Account a) {
        System.out.println("Enter new Username:");
        a.setUsername(input.next());
        System.out.println("Username changed successfully!");
        printInfo(a);
    }

    // MODIFIES: this
    // EFFECTS: allows user to edit account email
    private void editEmail(Account a) {
        System.out.println("Enter new Email:");
        a.setEmail(input.next());
        System.out.println("Email changed successfully!");
        printInfo(a);
    }

    // MODIFIES: this
    // EFFECTS: allows users to delete existing accounts
    private void deleteAccount() {
        boolean canFind = false;

        if (viewAll()) {
            System.out.println("Enter the account ID you wish to delete");
            String s = input.next();

            for (Account a : user.getAccountList()) {
                if (matchIdAndInput(a, s)) {
                    canFind = true;
                    try {
                        user.removeAccount(a);
                        System.out.println("Account removed successfully!");
                        break;
                    } catch (EmptyAccountListException e) {
                        System.out.println("No accounts to delete");
                    } catch (DoesNotExistAccount doesNotExistAccount) {
                        System.out.println("Can't find this account to delete");
                    }
                }
            }
            if (!canFind) {
                System.out.println("Cannot find account ID");
            }
        }
    }

    // EFFECTS: allows users to view a particular account in detail
    private void viewAccount() {
        boolean canFind = false;

        if (viewAll()) {
            System.out.println("Enter the ID you wish to view");
            String s = input.next();
            for (Account a : user.getAccountList()) {
                if (matchIdAndInput(a, s)) {
                    canFind = true;
                    printInfo(a);
                    break;
                }
            }
            if (!canFind) {
                System.out.println("Cannot find account ID");
            }
        }
    }

    // EFFECTS: allows user to preview all accounts
    private boolean viewAll() {
        if (user.size() <= 0) {
            System.out.println("You do not have any accounts saved yet!");
            return false;
        } else {
            for (Account a : user.getAccountList()) {
                System.out.println("ID:" + a.getId());
                System.out.println("WEBSITE:" + a.getWebsite());
                System.out.println("PASSWORD:" + a.getPassword());
                System.out.println("---------------------");
            }
            return true;
        }
    }

    // EFFECTS: prints the Account information
    private void printInfo(Account a) {
        System.out.println("WEBSITE:" + a.getWebsite());
        System.out.println("PASSWORD:" + a.getPassword());
        System.out.println("USERNAME:" + a.getUsername());
        System.out.println("EMAIL:" + a.getEmail());
        System.out.println("---------------------");
    }

    // EFFECTS: checks if the account ID is the same as the input s
    //          and checks if the given s is an integer
    private boolean matchIdAndInput(Account a, String s) {
        return String.valueOf(a.getId()).equals(s);
    }

    // EFFECTS: saves the user to file
    private void saveUser() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("Saved user to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads user from file if the logins match
    private void loadUser() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter login password:");
            String s = input.next();

            user = jsonReader.read();

            while (!s.equals(String.valueOf(user.getLogin()))) {
                if (s.equals("b")) {
                    return;               // if user forgot password, return to the login menu
                } else {
                    System.out.println("The login is incorrect, try again");
                    System.out.println("b -> back");
                }
                s = input.next();
            }

            System.out.println("Loaded user from " + JSON_STORE);
            isLogin = false;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

