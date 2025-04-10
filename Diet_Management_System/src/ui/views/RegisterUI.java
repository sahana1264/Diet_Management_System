package ui.views;

import dao.UserDAO;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterUI extends JFrame {
    private JTextField nameField, emailField, passwordField;
    private JComboBox<String> genderComboBox;

    public RegisterUI() {
        setTitle("Register - Diet Management System");
        setSize(400, 400);
        setLayout(new GridLayout(6, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JTextField(20);
        JLabel genderLabel = new JLabel("Gender:");
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});

        JButton registerButton = new JButton("Register");

        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(genderLabel);
        add(genderComboBox);
        add(registerButton);

        registerButton.addActionListener(new RegisterActionListener());

        setLocationRelativeTo(null);
    }

    private class RegisterActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String gender = (String) genderComboBox.getSelectedItem();

            User newUser = new User(0, name, email, password, 25, gender, 175, 70, "Moderate", "None", "Vegetarian", 3000.00);

            UserDAO userDAO = new UserDAO();
            if (userDAO.registerUser(newUser)) {
                JOptionPane.showMessageDialog(null, "✅ Registration Successful!");
                new LoginUI().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "❌ Registration Failed!");
            }
        }
    }
}
