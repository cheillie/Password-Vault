package ui;

import model.Account;
import model.User;

import java.util.Scanner;

public class PasswordVault {

    private int login;
    private Scanner input;
    private User user;
    private boolean keepGoing = true;
    private boolean isLogin = true;
    boolean stillRunning = true;

    // EFFECTS: runs the teller application
    public PasswordVault() {
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
    // EFFECTS: creates a while loop for the login page
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

    // EFFECTS: creates a new user with a login password
    private void loginMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Create your password");
        System.out.println("\t2 -> Enter existing password (doesn't work yet, will implement in the future)");
        System.out.println("\t3 -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processLogin(String command) {
        if (command.equals("1")) {
            init();
        } else if (command.equals("2")) {
            System.out.println("try again later :)..");
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes user
    private void init() {
        user = new User(createLogin());
    }

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

    // EFFECTS:
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
        System.out.println("\t4 -> view account(s)");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            saveAccount();
        } else if (command.equals("2")) {
            stillRunning = true;
            editAccountOption();
        } else if (command.equals("3")) {
            deleteAccount();
        } else if (command.equals("4")) {
            viewAccount();
        } else if (command.equals("r")) {
            cheat();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void cheat() {

    }

    // MODIFIES: this
    // EFFECTS: allows user to save an account
    private void saveAccount() {
        System.out.println("Enter website:");
        String website = input.next();

        System.out.println("Enter password:");
        String password = input.next();

        System.out.println("Enter username:");
        String username = input.next();

        System.out.println("Enter email:");
        String email = input.next();

        Account account = new Account(website, password, username,email);
        user.addAccount(account);

        System.out.println("Account saved successfully!");
    }

    // EFFECTS: allow users to edit existing accounts
    private void editAccountOption() {
        if (viewAll()) {
            System.out.println("Enter the account ID you wish to edit:");

            for (Account a : user.getAccountList()) {
                if (a.getID() == Integer.parseInt(input.next())) {    // !!! fix so that it takes in numbers only
                    printInfo(a);
                    editAccount(a);
                } else {
                    System.out.println("Cannot find account ID");
                }
            }
        }
    }

    // EFFECTS:
    private void editAccount(Account a) {
        while (stillRunning) {
            System.out.println("\nSelect from:");
            System.out.println("\t1 -> Edit Website");
            System.out.println("\t2 -> Edit Password");
            System.out.println("\t3 -> Edit Username");
            System.out.println("\t4 -> Edit Email");
            System.out.println("\t5 -> back");

            String command = input.next();

            processEditAccount(command, a);
        }
    }

    // MODIFIES: this
    // EFFECTS:
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
    // EFFECTS:
    private void editWebsite(Account a) {
        System.out.println("Enter new Website:");
        a.setWebsite(input.next());
        System.out.println("Website has been changed successfully!");
        printInfo(a);
    }

    // MODIFIES: this
    // EFFECTS:
    private void editPassword(Account a) {
        System.out.println("Enter new Password:");
        a.setPassword(input.next());
        System.out.println("Password has changed successfully!");
        printInfo(a);
    }

    // MODIFIES: this
    // EFFECTS:
    private void editUsername(Account a) {
        System.out.println("Enter new Username:");
        a.setUsername(input.next());
        System.out.println("Username has changed successfully!");
        printInfo(a);
    }

    // MODIFIES: this
    // EFFECTS:
    private void editEmail(Account a) {
        System.out.println("Enter new Email:");
        a.setEmail(input.next());
        System.out.println("Email has changed successfully!");
        printInfo(a);
    }

    // MODIFIES: this
    // EFFECTS: allow users to delete existing accounts
    private void deleteAccount() {
        if (viewAll()) {
            System.out.println("Enter the account ID you wish to delete");

            for (Account a : user.getAccountList()) {
                if (a.getID() == Integer.parseInt(input.next())) {
                    printInfo(a);
                    user.removeAccount(a);
                    System.out.println("Account has been removed successfully!");
                    break;
                }
            }
            System.out.println("Cannot find account ID");
        }
    }


    // EFFECTS: allow users to view a particular account in detail
    private void viewAccount() {
        if (viewAll()) {
            String s = input.next();
            System.out.println("Enter the ID you wish to view");
            for (Account a : user.getAccountList()) {
                if (a.getID() == Integer.parseInt(s)) {
                    printInfo(a);
                    break;
                }
            }
            System.out.println("Cannot find account ID");
        }
    }

    // EFFECTS:
    private boolean viewAll() {
        if (user.size() <= 0) {
            System.out.println("You don't have any accounts saved yet!");
            return false;
        } else {
            for (Account a : user.getAccountList()) {
                System.out.println("ID:" + a.getID());
                System.out.println("WEBSITE:" + a.getWebsite());
                System.out.println("PASSWORD:" + a.getPassword());
                System.out.println("---------------------");
            }
            return true;
        }
    }

    private void printInfo(Account a) {
        System.out.println("WEBSITE:" + a.getWebsite());
        System.out.println("PASSWORD:" + a.getPassword());
        System.out.println("USERNAME:" + a.getUsername());
        System.out.println("EMAIL:" + a.getEmail());
        System.out.println("---------------------");
    }

}
