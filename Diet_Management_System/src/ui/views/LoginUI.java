package ui.views;

import dao.UserDAO;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginUI() {
        setTitle("Login - Diet Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(new LoginActionListener());
        registerButton.addActionListener(e -> {
            new RegisterUI().setVisible(true);
            dispose(); // Close login window
        });

        setLocationRelativeTo(null);
    }

    private class LoginActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByEmail(email);

            if (user != null && user.getPasswordHash().equals(password)) {
                JOptionPane.showMessageDialog(null, "✅ Login Successful!");
                new UserDashboardUI(user).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "❌ Invalid credentials!");
            }
        }
    }
}
