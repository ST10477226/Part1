package com.mycompany.registrationandlogin;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class RegistrationAndLoginApp {

    // Simulated user database (username -> password)
    public static HashMap<String, String> userDatabase = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        });
    }

   
    // Registration Form
    
    public static class RegistrationForm extends JFrame {
        public JTextField usernameField, phoneField;
        public JPasswordField passwordField;
        public JComboBox<String> countryCodeComboBox;
        public JLabel messageLabel;

        public RegistrationForm() {
            setTitle("Registration Form");
            setSize(600, 700);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);
            getContentPane().setBackground(new Color(173, 216, 230)); // Light blue

            JButton maximizeButton = new JButton("⬜");
            maximizeButton.setBounds(500, 10, 50, 30);
            add(maximizeButton);
            maximizeButton.addActionListener(e -> setExtendedState(JFrame.MAXIMIZED_BOTH));

            JButton minimizeButton = new JButton("_");
            minimizeButton.setBounds(450, 10, 50, 30);
            add(minimizeButton);
            minimizeButton.addActionListener(e -> setState(JFrame.ICONIFIED));

            JButton closeButton = new JButton("X");
            closeButton.setBounds(550, 10, 50, 30);
            add(closeButton);
            closeButton.addActionListener(e -> System.exit(0));

            JLabel titleLabel = new JLabel("Registration Form");
            titleLabel.setBounds(200, 50, 200, 30);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            titleLabel.setForeground(new Color(255, 105, 180));
            add(titleLabel);

            JLabel usernameLabel = new JLabel("Username:");
            usernameLabel.setBounds(50, 100, 150, 25);
            add(usernameLabel);

            usernameField = new JTextField();
            usernameField.setBounds(200, 100, 300, 25);
            add(usernameField);

            JLabel phoneLabel = new JLabel("Phone Number:");
            phoneLabel.setBounds(50, 150, 150, 25);
            add(phoneLabel);

            phoneField = new JTextField();
            phoneField.setBounds(200, 150, 200, 25);
            add(phoneField);

            JLabel countryCodeLabel = new JLabel("Country Code:");
            countryCodeLabel.setBounds(50, 200, 150, 25);
            add(countryCodeLabel);

            String[] countryCodes = {"+27 (South Africa)"};
            countryCodeComboBox = new JComboBox<>(countryCodes);
            countryCodeComboBox.setBounds(200, 200, 300, 25);
            add(countryCodeComboBox);

            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setBounds(50, 250, 150, 25);
            add(passwordLabel);

            passwordField = new JPasswordField();
            passwordField.setBounds(200, 250, 300, 25);
            add(passwordField);

            JButton registerButton = new JButton("Register");
            registerButton.setBounds(250, 300, 100, 30);
            registerButton.setBackground(new Color(255, 182, 193));
            add(registerButton);

            JButton goToLoginButton = new JButton("Go to Login");
            goToLoginButton.setBounds(250, 350, 120, 30);
            goToLoginButton.setBackground(new Color(255, 182, 193));
            add(goToLoginButton);

            messageLabel = new JLabel("");
            messageLabel.setBounds(50, 400, 500, 200);
            messageLabel.setForeground(Color.RED);
            messageLabel.setVerticalAlignment(SwingConstants.TOP);
            add(messageLabel);

            registerButton.addActionListener(e -> registerUser());
            goToLoginButton.addActionListener(e -> {
                dispose();
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
            });
        }

        public void registerUser() {
            String username = usernameField.getText();
            String phone = phoneField.getText();
            String countryCode = (String) countryCodeComboBox.getSelectedItem();
            String password = new String(passwordField.getPassword());

            boolean isUsernameValid = username.matches("^[a-zA-Z0-9_]{1,5}$") && username.contains("_");
            boolean isPhoneValid = phone.matches("0\\d{9}");
            boolean isPasswordValid = password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*\\W).{8,}$");
            boolean isUsernameUnique = !userDatabase.containsKey(username);

            String usernameMessage = isUsernameValid
                    ? (isUsernameUnique ? "Username successfully captured." : "Username already exists.")
                    : "Username is not correctly formatted. Must contain '_' and be max 5 chars.";
            String phoneMessage = isPhoneValid ? "Cellphone number successfully added." : "Cellphone number is invalid.";
            String passwordMessage = isPasswordValid ? "Password successfully captured." : "Password format invalid.";

            if (isUsernameValid && isPhoneValid && isPasswordValid && isUsernameUnique) {
                userDatabase.put(username, password);
                messageLabel.setText("<html>" + usernameMessage + "<br>" + phoneMessage + "<br>" + passwordMessage + "</html>");
                messageLabel.setForeground(new Color(0, 100, 0));
            } else {
                messageLabel.setText("<html>" + usernameMessage + "<br>" + phoneMessage + "<br>" + passwordMessage + "</html>");
                messageLabel.setForeground(Color.RED);
            }
        }
    }

    // ----------------------------
    // Login Form
    // ----------------------------
    public static class LoginForm extends JFrame {
        public JTextField usernameField;
        public JPasswordField passwordField;
        public JLabel messageLabel;

        public LoginForm() {
            setTitle("Login Form");
            setSize(500, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);
            getContentPane().setBackground(new Color(173, 216, 230));

            JButton maximizeButton = new JButton("⬜");
            maximizeButton.setBounds(400, 10, 50, 30);
            add(maximizeButton);
            maximizeButton.addActionListener(e -> setExtendedState(JFrame.MAXIMIZED_BOTH));

            JButton minimizeButton = new JButton("_");
            minimizeButton.setBounds(350, 10, 50, 30);
            add(minimizeButton);
            minimizeButton.addActionListener(e -> setState(JFrame.ICONIFIED));

            JButton closeButton = new JButton("X");
            closeButton.setBounds(450, 10, 50, 30);
            add(closeButton);
            closeButton.addActionListener(e -> System.exit(0));

            JLabel titleLabel = new JLabel("Login Form");
            titleLabel.setBounds(180, 50, 200, 30);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            titleLabel.setForeground(new Color(255, 105, 180));
            add(titleLabel);

            JLabel usernameLabel = new JLabel("Username:");
            usernameLabel.setBounds(50, 100, 150, 25);
            add(usernameLabel);

            usernameField = new JTextField();
            usernameField.setBounds(200, 100, 200, 25);
            add(usernameField);

            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setBounds(50, 150, 150, 25);
            add(passwordLabel);

            passwordField = new JPasswordField();
            passwordField.setBounds(200, 150, 200, 25);
            add(passwordField);

            JButton loginButton = new JButton("Login");
            loginButton.setBounds(200, 200, 100, 30);
            loginButton.setBackground(new Color(255, 182, 193));
            add(loginButton);

            JButton createAccountButton = new JButton("Create Account");
            createAccountButton.setBounds(200, 250, 150, 30);
            createAccountButton.setBackground(new Color(255, 182, 193));
            add(createAccountButton);

            messageLabel = new JLabel("");
            messageLabel.setBounds(50, 300, 400, 50);
            messageLabel.setForeground(Color.RED);
            messageLabel.setVerticalAlignment(SwingConstants.TOP);
            add(messageLabel);

            loginButton.addActionListener(e -> loginUser());
            createAccountButton.addActionListener(e -> {
                dispose();
                RegistrationForm registrationForm = new RegistrationForm();
                registrationForm.setVisible(true);
            });
        }

        public void loginUser() {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            boolean isLoginValid = userDatabase.containsKey(username) && userDatabase.get(username).equals(password);

            String loginMessage = isLoginValid ? "Welcome " + username + ", it's great to see you again." : "Invalid username or password.";
            messageLabel.setText(loginMessage);
            messageLabel.setForeground(isLoginValid ? new Color(0, 100, 0) : Color.RED);

            }
        }
    }
