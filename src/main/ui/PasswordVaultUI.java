package ui;

import model.User;
import ui.pages.PasswordListPage;
import ui.pages.SavePasswordPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the PasswordVault application
public class PasswordVaultUI extends JFrame implements ActionListener {

    protected static Color PANEL_COLOUR = new Color(231, 225, 199);
    protected static Color BORDER_COLOUR = new Color(142, 141, 138);
    protected static Color BUTTON_COLOUR = new Color(219, 149, 58, 255);

    private JPanel leftPanel;
    private JButton newPasswordButton;
    private SavePasswordPage savePasswordPage;
    private PasswordListPage passwordListPage;
    private User user;

    //MODIFIES: this
    //EFFECTS: creates PasswordVaultUI, loads PasswordVault appliances, displays sidebar and tabs
    public PasswordVaultUI(User user) {
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
        newPasswordButton = new JButton("Save New Password");
        newPasswordButton.setBounds(17, 101, 150, 60);             // set location of button
        newPasswordButton.setBackground(BUTTON_COLOUR);     // set background of button
        newPasswordButton.setForeground(Color.white);                                // font colour
        newPasswordButton.setFont(new Font("Barlow", Font.BOLD, 13));      // font size and font
        //newPasswordButton.setBorder(new LineBorder(Color.BLACK));                  // button border
        newPasswordButton.setBorder(BorderFactory.createEtchedBorder());
        newPasswordButton.setFocusable(false);                              // no more white outline around font
        newPasswordButton.addActionListener(this);

        this.add(newPasswordButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newPasswordButton) {
            //makeSavePasswordPage();
            savePasswordPage = new SavePasswordPage(this);
            newPasswordButton.setVisible(false);
            leftPanel.setVisible(false);
            passwordListPage.setVisible(false);
        }
    }

    // EFFECTS: shows the passwordVaultUI page
    public void showPasswordVaultUIPanels() {
        newPasswordButton.setVisible(true);
        leftPanel.setVisible(true);
        passwordListPage.setVisible(true);
    }

    // EFFECTS: returns the user
    public User getUser() {
        return user;
    }


//    // MODIFIES: this
//    // EFFECTS: renders the save password page
//    public void makeSavePasswordPage() {
//        savePasswordPage = new JPanel();
//        this.add(savePasswordPage);
//
//        savePasswordPage.setBounds(0, 0, this.getWidth(), this.getHeight());
//        savePasswordPage.setBackground(LoginScreen.BACKGROUND_COLOUR);
//        savePasswordPage.setVisible(true);
//
//        newPasswordButton.setVisible(false);
//        leftPanel.setVisible(false);
//    }


}


