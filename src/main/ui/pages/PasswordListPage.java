package ui.pages;

import javax.swing.*;

// Represents the password list page in the application
public class PasswordListPage extends JPanel {

    // MODIFIES: this
    // EFFECTS: renders the password list page
    public PasswordListPage(JFrame frame) {
        frame.add(this);
        setVisible(true);

        setBounds(185, 0, frame.getWidth(), frame.getHeight());
        setBackground(SavePasswordPage.BACKGROUND_COLOUR);
    }

}
