import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginPanel extends JPanel {
    private final JLabel userLabel;
    private final JLabel passLabel;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final LoginService loginService;
    private int loginTries;

    public LoginPanel(LoginService loginService) {
        this.loginService = loginService;
        this.userLabel = new JLabel("Username:");
        this.passLabel = new JLabel("Password:");
        this.usernameField = new JTextField(20);
        this.passwordField = new JPasswordField(20);
        this.loginButton = new JButton("Login");
        this.loginTries = 0;
        setupLayout();
        setupLoginHandler();
    }

    private void setupLayout() {
        setLayout(new GridLayout(3, 2));
        add(userLabel);
        add(usernameField);
        add(passLabel);
        add(passwordField);
        add(loginButton);
    }

    private void setupLoginHandler() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                boolean isValid = loginService.isValidLogin(username, password);
                if (isValid) {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Login successful!");
                } else {
                    loginTries++;
                    if (loginTries < 3) {
                        JOptionPane.showMessageDialog(LoginPanel.this, "Incorrect login details. Try again.");
                    } else {
                        JOptionPane.showMessageDialog(LoginPanel.this, "Maximum login tries exceeded. Please contact support.");
                        System.exit(0);
                    }
                }
            }
        });
    }
}

interface LoginService {
    boolean isValidLogin(String username, String password);
}

class SimpleLoginService implements LoginService {
    @Override
    public boolean isValidLogin(String username, String password) {
        return username.equals("admin") && password.equals("password");
    }
}

public class LoginApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new LoginPanel(new SimpleLoginService()));
        frame.pack();
        frame.setVisible(true);
    }
}
