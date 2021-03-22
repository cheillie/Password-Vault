package ui.pages;

import model.Account;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the save new password page
public class SavePasswordPage extends JPanel implements ActionListener {

    protected static Color BACKGROUND_COLOUR = new Color(210, 182, 142);
    private static String SAVE = "SAVE";
    private JFrame controllingFrame; //needed for dialogs
    private JTextField website;
    private JTextField password;
    private JTextField username;
    private JTextField email;
    private User user;


    // MODIFIES: this
    // EFFECTS: renders the panel for save password page
    public SavePasswordPage(JFrame frame) {
        frame.add(this);
        setVisible(true);

        setBounds(0, 0, frame.getWidth(), frame.getHeight());
        setBackground(BACKGROUND_COLOUR);

        savePassword();
    }

    // MODIFIES: this
    // EFFECTS: renders the page to enter information to save a password
    public void savePassword() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        website = new JTextField(20);
        password = new JTextField(20);
        username = new JTextField(20);
        email = new JTextField(20);

        this.add(Box.createRigidArea(new Dimension(0, 60)));
        this.add(website);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(password);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(username);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(email);
        this.add(Box.createRigidArea(new Dimension(0, 60)));
        this.add(createSaveButton());

        website.setMaximumSize(new Dimension(website.getPreferredSize().width, 30));
        password.setMaximumSize(new Dimension(password.getPreferredSize().width, 30));
        username.setMaximumSize(new Dimension(username.getPreferredSize().width, 30));
        email.setMaximumSize(new Dimension(email.getPreferredSize().width, 30));

        JLabel label = new JLabel("Website: ");
        label.setFont(new Font("Barlow", Font.BOLD, 13));
        label.setLabelFor(website);
        add(label);

    }


    // EFFECTS: creates the save password button
    public JButton createSaveButton() {
        JButton saveButton = new JButton("SAVE");

        saveButton.setFont(new Font("Barlow", Font.BOLD, 13));
        saveButton.setMaximumSize(saveButton.getPreferredSize());
        //saveButton.setBounds(50, 200, 150, 150);
        //saveButton.setPreferredSize(new Dimension(70, 25));
        saveButton.setFocusable(false);
        saveButton.setActionCommand(SAVE);
        saveButton.addActionListener(this);    // is waiting for the command to == ENTER
        return saveButton;
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
            //user.addAccount(account);

            JOptionPane.showMessageDialog(this, "Account saved successfully!");
        }
    }
}
