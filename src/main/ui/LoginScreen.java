package ui;

import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Arrays;

// Represents the home screen PasswordVault application
public class LoginScreen implements ActionListener {

    protected static Color BACKGROUND_COLOUR = new Color(210, 182, 142);
    protected static final String JSON_STORE = "./data/user.json";

    private static String ENTER = "Enter";
    private static String CREATE = "Create";
    private JFrame frame;
    private JFrame controllingFrame; //needed for dialogs
    private JPasswordField loginField;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    protected User user;

    // MODIFIES: this
    // EFFECTS: creates the HomeScreen
    public LoginScreen() {
        frame = new JFrame("Password Vault");
        frame.getContentPane().setBackground(BACKGROUND_COLOUR);                // frame color
        frame.setSize(850, 500);                                    // set window size

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                   // exit program
        frame.setResizable(false);                                              // can't drag to resize
        frame.setLayout(null);                                                  // sets default layout to null
        frame.setLocationRelativeTo(null);                                      // centers window

        renderLogo();
        renderLoginBox();
        frame.setVisible(true);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: renders the logo icon on the home screen
    public void renderLogo() {
        ImageIcon imageLock = new ImageIcon(".//data//lock2.png");
        Image image = imageLock.getImage().getScaledInstance(270, 290, Image.SCALE_SMOOTH);
        imageLock = new ImageIcon(image);

        JLabel img = new JLabel();
        img.setIcon(imageLock);
        img.setBounds(290, -75, 500, 500);

        frame.add(img);
    }

    // MODIFIES: this
    // EFFECTS: renders the login box
    public void renderLoginBox() {
        loginField = new JPasswordField(14);
        loginField.setActionCommand("Enter");
        loginField.addActionListener(this);

        JLabel label = new JLabel("Enter login: ");
        label.setFont(new Font("Barlow", Font.BOLD, 13));
        label.setLabelFor(loginField);

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPanel.add(label);
        textPanel.add(loginField);

        textPanel.add(createEnterButton());

        textPanel.setBounds(180, 310, 400, 40);
        textPanel.setBackground(BACKGROUND_COLOUR);
        frame.add(textPanel);

        frame.add(createCreateNewLoginButton());
    }

    // EFFECTS: creates a enter button
    public JButton createEnterButton() {
        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Barlow", Font.BOLD, 13));
        enterButton.setPreferredSize(new Dimension(70, 25));
        enterButton.setFocusable(false);
        enterButton.setActionCommand(ENTER);
        enterButton.addActionListener(this);    // is waiting for the command to == ENTER
        return enterButton;
    }

    // EFFECTS: create a create new login button
    public JButton createCreateNewLoginButton() {
        JButton createNewLoginButton = new JButton("Create new login");
        createNewLoginButton.setFont(new Font("Barlow", Font.BOLD, 13));
        createNewLoginButton.setPreferredSize(new Dimension(70, 25));
        createNewLoginButton.setBounds(345, 350, 150, 30); // sets location of create button
        createNewLoginButton.setFocusable(false);
        createNewLoginButton.setActionCommand(CREATE);      // in order for this button to work, the action listener
        createNewLoginButton.addActionListener(this);    // is waiting for the command to == ENTER
        return createNewLoginButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (ENTER.equals(cmd)) {
            char[] input = loginField.getPassword();

            if (isPasswordCorrect(input)) {
                this.frame.setVisible(false);

                System.out.println("Password is correct");
                try {
                    user = jsonReader.read();
                    System.out.println("Loaded user from " + JSON_STORE);
                    JFrame frame = new PasswordVaultUI(user, jsonWriter, jsonReader);
                    frame.setVisible(true);

                } catch (IOException ioException) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
            } else {
                JOptionPane.showMessageDialog(controllingFrame,
                        "Invalid password. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (CREATE.equals(cmd)) {
            createNewLogin(e);
        }
    }

    // EFFECTS: checks if the login entered matches with the saved login
    public boolean isPasswordCorrect(char[] input) {
        boolean isCorrect = true;
        try {
            user = jsonReader.read();
            System.out.println("File loaded successfully");
        } catch (IOException e) {
            System.out.println("File cannot be found");
        }
        char[] correctPassword = ("" + user.getLogin()).toCharArray();

        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals(input, correctPassword);
        }

        Arrays.fill(correctPassword, '0');
        return isCorrect;
    }

    // EFFECTS: renders the create new login pop up dialog
    public void createNewLogin(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (CREATE.equals(cmd)) {
            String input = JOptionPane.showInputDialog("Enter your new 4 digit login: ");

            if (input != null && input.length() == 4) {
                try {
                    Integer.parseInt(input);
                    JOptionPane.showMessageDialog(controllingFrame, "Login created successfully",
                            "Login created",
                            JOptionPane.INFORMATION_MESSAGE);

                    this.frame.setVisible(false);
                    JFrame frame = new PasswordVaultUI(new User(Integer.parseInt(input)), jsonWriter, jsonReader);
                    frame.setVisible(true);

                } catch (NumberFormatException ex) {
                    messageDialogErrorMsg();
                }
            } else if (input == null) {
                System.out.println("Action cancelled");
            } else {
                messageDialogErrorMsg();
            }
        }
    }

    // EFFECTS: show message dialog for an invalid login error message
    public void messageDialogErrorMsg() {
        JOptionPane.showMessageDialog(controllingFrame, "Not a 4 digit login. Try again.",
                "Error Message",
                JOptionPane.ERROR_MESSAGE);
    }

}

