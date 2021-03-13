package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PasswordVaultUI {

    JFrame frame;
    JPanel leftPanel;
    JButton newPasswordButton;

    //MODIFIES: this
    //EFFECTS: creates PasswordVaultUI, loads PasswordVault appliances, displays sidebar and tabs
    public PasswordVaultUI() {
        frame = new JFrame("Password Vault");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                // exit program
        frame.getContentPane().setBackground(new Color(200,200,200));
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
        leftPanel.setBackground(new Color(150,150,150));
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        createNewPasswordButton();
        frame.add(leftPanel);
    }

    private void createNewPasswordButton() {
        newPasswordButton = new JButton("Save New Password");
        newPasswordButton.setBounds(16, 100, 150,60);
        newPasswordButton.setBackground(Color.blue);
        newPasswordButton.setForeground(Color.red);    // font colour
        newPasswordButton.setBorder(new LineBorder(Color.BLACK));

        frame.add(newPasswordButton);
    }
}
