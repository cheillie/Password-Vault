package ui.pages;

import model.Account;
import model.User;
import ui.PasswordVault;
import ui.PasswordVaultUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the save new password page
public class SavePasswordPage extends JPanel implements ActionListener {

    protected static Color BACKGROUND_COLOUR = new Color(210, 182, 142);
    private static String SAVE = "SAVE";
    private static String BACK = "back";
    private JTextField website;
    private JTextField password;
    private JTextField username;
    private JTextField email;
    private PasswordVaultUI passwordVaultUI;


    // MODIFIES: this
    // EFFECTS: renders the panel for save password page
    public SavePasswordPage(PasswordVaultUI passwordVaultUI) {
        this.setLayout(null);
        this.passwordVaultUI = passwordVaultUI;

        this.setBounds(0, 0, passwordVaultUI.getWidth(), passwordVaultUI.getHeight());

        passwordVaultUI.add(this);
        setVisible(true);
        setBackground(BACKGROUND_COLOUR);
        savePassword();
    }

    // MODIFIES: this
    // EFFECTS: renders the page to enter information to save a password
    public void savePassword() {
        JPanel enterInfoPanel = new JPanel();

        enterInfoPanel.setBackground(BACKGROUND_COLOUR);
        enterInfoPanel.setLayout(new GridLayout(4, 0, 0, 40));
        enterInfoPanel.setBounds(165, 60,400,250);

        this.add(enterInfoPanel);

        website = makeTextFieldPanel("Website: ", enterInfoPanel);
        password = makeTextFieldPanel("Password: ", enterInfoPanel);
        username = makeTextFieldPanel("Username: ", enterInfoPanel);
        email = makeTextFieldPanel("Email: ", enterInfoPanel);

        this.add(createBackButton());
        this.add(createSaveButton());
    }

    // EFFECTS: renders each of the text panel and labels for password information
    public JTextField makeTextFieldPanel(String s, JPanel panel) {
        JPanel textFieldPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JTextField textField = new JTextField(20);
        JLabel label = new JLabel(s);

        textField.setPreferredSize(new Dimension(getPreferredSize().width, 30));

        textFieldPanel.add(label);
        textFieldPanel.add(textField);
        textFieldPanel.setBackground(BACKGROUND_COLOUR);

        label.setFont(new Font("Barlow", Font.BOLD, 13));
        label.setLabelFor(textField);

        panel.add(textFieldPanel);

        return textField;
    }


    // EFFECTS: creates the save password button
    public JButton createSaveButton() {
        JButton saveButton = new JButton("SAVE");
        saveButton.setFont(new Font("Barlow", Font.BOLD, 13));
        saveButton.setBounds(400, 350, 85, 30);
        saveButton.setFocusable(false);
        saveButton.setActionCommand(SAVE);
        saveButton.addActionListener(this);    // is waiting for the command to == ENTER

        return saveButton;
    }

    // EFFECTS: creates the back button
    public JButton createBackButton() {
        JButton backButton = new JButton("back");
        backButton.setFont(new Font("Barlow", Font.BOLD, 13));
        backButton.setBounds(15, 15, 65, 30);
        backButton.setFocusable(false);
        backButton.setActionCommand(BACK);
        backButton.addActionListener(this);    // is waiting for the command to == ENTER

        return backButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String websiteInput = website.getText();
        String passwordInput = password.getText();
        String usernameInput = username.getText();
        String emailInput = email.getText();

        String cmd = e.getActionCommand();

        if (SAVE.equals(cmd)) {
            Account account = new Account(websiteInput, passwordInput, usernameInput, emailInput);
            passwordVaultUI.getUser().addAccount(account);

            JOptionPane.showMessageDialog(this, "Account saved successfully!");
            passwordVaultUI.setPasswordListPage(new PasswordListPage(passwordVaultUI));
            this.setVisible(false);

            passwordVaultUI.showPasswordVaultUIPanels();
        }

        if (BACK.equals(cmd)) {
            this.setVisible(false);
            passwordVaultUI.showPasswordVaultUIPanels();
        }
    }
}
