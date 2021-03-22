package ui.pages;

import model.Account;
import ui.PasswordVaultUI;

import javax.swing.*;
import java.awt.*;

// Represents the password list page in the application
public class PasswordListPage extends JPanel {

    private PasswordVaultUI passwordVaultUI;

    // MODIFIES: this
    // EFFECTS: renders the password list page
    public PasswordListPage(JFrame frame) {
        frame.add(this);
        setVisible(true);

        setBounds(185, 0, frame.getWidth(), frame.getHeight());
        setBackground(SavePasswordPage.BACKGROUND_COLOUR);


        scrollPage();
    }

    public void scrollPage() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().getView().setBackground(SavePasswordPage.BACKGROUND_COLOUR);
        scrollPane.getViewport().getView().setForeground(Color.RED);


//        for (Account a : passwordVaultUI.getUser().getAccountList()) {
//            JLabel id = new JLabel("ID:" + a.getId());
//            JLabel website = new JLabel("WEBSITE:" + a.getWebsite());
//            JLabel password = new JLabel("PASSWORD:" + a.getPassword());
//
//            panel.add(id);
//            panel.add(website);
//            panel.add(password);
//        }

        for (int i = 0; i < 20; i++) {
            panel.add(new JButton("Button"));
        }

        scrollPane.setPreferredSize(new Dimension(this.getWidth() - 100, this.getHeight() - 60));


        add(scrollPane, BorderLayout.WEST);

    }



}
