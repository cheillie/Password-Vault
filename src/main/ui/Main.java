package ui;

import java.io.FileNotFoundException;

// Runs the Password Vault application
public class Main {
    public static void main(String[] args) {
        try {
            new PasswordVault();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

