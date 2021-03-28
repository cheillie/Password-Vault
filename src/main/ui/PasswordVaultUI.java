package ui;

import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.pages.PasswordListPage;
import ui.pages.SavePasswordPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Represents the PasswordVault application
public class PasswordVaultUI extends JFrame implements ActionListener {

    protected static Color PANEL_COLOUR = new Color(231, 225, 199);
    protected static Color BORDER_COLOUR = new Color(142, 141, 138);
    protected static Color BUTTON_COLOUR = new Color(219, 149, 58, 255);
    protected static Font FONT_STYLE =  new Font("Bahnschrift", Font.BOLD, 13);

    private JPanel leftPanel;
    private JButton newPasswordButton;
    private JButton saveAndQuitButton;
    private SavePasswordPage savePasswordPage;
    private PasswordListPage passwordListPage;
    private User user;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //MODIFIES: this
    //EFFECTS: creates PasswordVaultUI, loads PasswordVault appliances, displays sidebar and tabs
    public PasswordVaultUI(User user, JsonWriter jsonWriter, JsonReader jsonReader) {
        this.jsonWriter = jsonWriter;
        this.jsonReader = jsonReader;
        this.user = user;

        this.setTitle("Password Vault");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                // exit program
        this.getContentPane().setBackground(Color.RED);
        this.setResizable(false);                                          // can't drag to resize
        this.setSize(850, 500);                               // set window size
        this.setLayout(null);                                              // sets default layout to null
        this.setLocationRelativeTo(null);                                  // centers window

        createLeftPanel();
        createPasswordListPage();

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the left panel
    private void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setBounds(-3, -3, 190, this.getHeight());
        leftPanel.setBackground(PANEL_COLOUR);
        leftPanel.setBorder(BorderFactory.createLineBorder(BORDER_COLOUR, 2));
        createNewPasswordButton();
        createSaveAndQuitButton();
        this.add(leftPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates the right panel
    private void createPasswordListPage() {
        passwordListPage = new PasswordListPage(this);
    }

    // MODIFIES: this
    // EFFECTS: creates the new password button in left panel
    private void createNewPasswordButton() {
        newPasswordButton = new JButton("Save Password");
        newPasswordButton.setBounds(17, 101, 150, 60);           // set location of button
        newPasswordButton.setBackground(BUTTON_COLOUR);
        newPasswordButton.setForeground(Color.white);
        newPasswordButton.setFont(FONT_STYLE);
        newPasswordButton.setBorder(BorderFactory.createEtchedBorder());
        newPasswordButton.setFocusable(false);
        newPasswordButton.addActionListener(this);

        this.add(newPasswordButton);
    }

    // MODIFIES: this
    // EFFECTS: creates the save & quit button
    private void createSaveAndQuitButton() {
        saveAndQuitButton = new JButton("Save & Quit");
        saveAndQuitButton.setBounds(17, 250, 150, 60);             // set location of button
        saveAndQuitButton.setBackground(BUTTON_COLOUR);
        saveAndQuitButton.setForeground(Color.white);
        saveAndQuitButton.setFont(FONT_STYLE);
        saveAndQuitButton.setBorder(BorderFactory.createEtchedBorder());
        saveAndQuitButton.setFocusable(false);
        saveAndQuitButton.addActionListener(this);

        this.add(saveAndQuitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newPasswordButton) {
            savePasswordPage = new SavePasswordPage(this);
            newPasswordButton.setVisible(false);
            saveAndQuitButton.setVisible(false);
            leftPanel.setVisible(false);
            passwordListPage.setVisible(false);
        } else if (e.getSource() == saveAndQuitButton) {
            saveUser();
            System.exit(0);                                 // stops application
        }
    }

    // MODIFIES: this
    // EFFECTS: shows the passwordVaultUI page
    public void showPasswordVaultUIPanels() {
        newPasswordButton.setVisible(true);
        saveAndQuitButton.setVisible(true);
        leftPanel.setVisible(true);
        passwordListPage.setVisible(true);
    }

    // EFFECTS: returns the user
    public User getUser() {
        return user;
    }

    // MODIFIES: this
    // EFFECTS: sets the password list page
    public void setPasswordListPage(PasswordListPage passwordListPage) {
        this.passwordListPage = passwordListPage;
    }

    // EFFECTS: saves the user to file
    private void saveUser() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("Saved user to " + LoginScreen.JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + LoginScreen.JSON_STORE);
        }
    }

}


