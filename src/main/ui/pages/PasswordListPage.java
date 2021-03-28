package ui.pages;

import model.Account;
import model.User;
import ui.PasswordVaultUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents the password list page in the application
public class PasswordListPage extends JPanel {

    private User user;
    private int id;
    private ArrayList<JButton> deleteButtonList;
    private PasswordVaultUI frame;

    // MODIFIES: this
    // EFFECTS: renders the password list page
    public PasswordListPage(PasswordVaultUI frame) {
        this.frame = frame;
        deleteButtonList = new ArrayList();
        id = 0;
        user = frame.getUser();
        setLayout(null);
        setBounds(185, 0, frame.getWidth(), frame.getHeight());
        setBackground(SavePasswordPage.BACKGROUND_COLOUR);

        scrollPanel();

        setVisible(true);
        frame.add(this);
    }

    // MODIFIES: this
    // EFFECTS: creates the grid bag layout for the school panel
    public void scrollPanel() {
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panelWithGridBagLayout = new JPanel();
        panelWithGridBagLayout.setLayout(new GridBagLayout());
        panelWithGridBagLayout.setBackground(SavePasswordPage.BACKGROUND_COLOUR);

        addAccountPanel(panelWithGridBagLayout, gbc);

        JScrollPane scroll = new JScrollPane(panelWithGridBagLayout, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(0, 0, 660, this.getHeight() - 27);

        add(scroll);
    }

    // MODIFIES: this, Account
    // EFFECTS: creates a big panels for each account in user
    public void addAccountPanel(JPanel panelWithGridBagLayout, GridBagConstraints gbc) {
        if (!user.getAccountList().isEmpty()) {
            for (Account a : user.getAccountList()) {
                a.setId(id);
                JPanel accountPanel = new JPanel();
                accountPanel.setLayout(null);
                accountPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
                accountPanel.setPreferredSize(new Dimension(this.getWidth(), 150));
                gbc.gridy++;
                accountPanel.setBackground(new Color(210, 182, 142));
                addAccount(accountPanel, a);
                addDeleteButton(accountPanel, a);
                id++;
                panelWithGridBagLayout.add(accountPanel, gbc);
            }
        } else {
            JLabel label = new JLabel("No accounts added yet");
            label.setFont(new Font("Barlow", Font.BOLD, 13));
            label.setForeground(new Color(62, 62, 62));
            panelWithGridBagLayout.add(label);
        }
    }

    // EFFECTS: creates smaller panels for each account detail
    public void addAccount(JPanel accountPanel, Account account) {
        JPanel containAccountPanel = new JPanel();
        containAccountPanel.setBackground(SavePasswordPage.BACKGROUND_COLOUR);
        containAccountPanel.setLayout(new GridLayout(4, 0, 0, 2));
        containAccountPanel.setBounds(120, 24, 300, 100);

        accountPanel.add(containAccountPanel);

        String s0 = "Website: " + account.getWebsite();
        String s1 = "Password: " + account.getPassword();
        String s2 = "Username: " + account.getUsername();
        String s3 = "Email: " + account.getEmail();

        ArrayList<String> infoList = new ArrayList();
        infoList.add(s0);
        infoList.add(s1);
        infoList.add(s2);
        infoList.add(s3);

        for (String s : infoList) {
            addLabelToContainAccountPanel(s, containAccountPanel);
        }
    }

    // MODIFIES: creates the delete account button
    public void addDeleteButton(JPanel accountPanel, Account account) {
        JButton button = new JButton("Delete");
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(229, 51, 51));
        button.setFont(new Font("Barlow", Font.BOLD, 13));
        button.setBounds(500, 55, 75, 35);
        button.setFocusable(false);

        button.addActionListener(new DeleteButtonHandler());
        button.setActionCommand(Integer.toString(account.getId()));

        deleteButtonList.add(button);
        accountPanel.add(button);
    }

    // EFFECTS: renders the account detail
    public void addLabelToContainAccountPanel(String s, JPanel containAccountPanel) {
        JLabel label = new JLabel(s);
        label.setFont(new Font("Barlow", Font.BOLD, 13));
        containAccountPanel.add(label);
    }

    // EFFECTS: gets account ID
    public int getID(JButton b) {
        return Integer.parseInt(b.getActionCommand());
    }

    // EFFECTS: the handler for delete account button
    public class DeleteButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();

            for (JButton b : deleteButtonList) {
                if (choice.equals(b.getActionCommand())) {
                    deleteAccount(user.getAccountList().get(getID(b)));
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes an account from the PasswordListPage
    public void deleteAccount(Account account) {
        user.removeAccount(account);
        frame.setPasswordListPage(new PasswordListPage(frame));
        this.setVisible(false);
        frame.showPasswordVaultUIPanels();
    }


}
