package ui;

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

    JFrame frame;
    JPanel leftPanel;
    JButton newPasswordButton;

    //MODIFIES: this
    //EFFECTS: creates PasswordVaultUI, loads PasswordVault appliances, displays sidebar and tabs
    public PasswordVaultUI() {
        frame = new JFrame("Password Vault");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                // exit program
        frame.getContentPane().setBackground(LoginScreen.BACKGROUND_COLOUR);
        frame.setResizable(false);                                          // can't drag to resize
        frame.setSize(850, 500);                               // set window size
        frame.setLayout(null);                                              // sets default layout to null
        frame.setLocationRelativeTo(null);                                  // centers window

        createLeftPanel();

        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the left panel
    private void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setBounds(-3, -3, 190, frame.getHeight());
        leftPanel.setBackground(PANEL_COLOUR);
        leftPanel.setBorder(BorderFactory.createLineBorder(BORDER_COLOUR, 2));
        createNewPasswordButton();
        frame.add(leftPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates the new password button in left panel
    private void createNewPasswordButton() {
        newPasswordButton = new JButton("Save New Password");
        newPasswordButton.setBounds(17, 100, 150, 60);               // set location of button
        newPasswordButton.setBackground(BUTTON_COLOUR);     // set background of button
        newPasswordButton.setForeground(Color.white);                                 // font colour
        newPasswordButton.setFont(new Font("Barlow", Font.BOLD, 13));       // font size and font
        //newPasswordButton.setBorder(new LineBorder(Color.BLACK));                  // button border
        newPasswordButton.setBorder(BorderFactory.createEtchedBorder());
        newPasswordButton.setFocusable(false);                                   // no more white outline around font

        frame.add(newPasswordButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newPasswordButton) {
            new SavePasswordPage();
        }
    }
}


