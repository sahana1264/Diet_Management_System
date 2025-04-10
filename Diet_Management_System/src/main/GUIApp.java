package main;

import ui.views.LoginUI;

import javax.swing.*;

public class GUIApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginUI loginUI = new LoginUI();
            loginUI.setVisible(true);
        });
    }
}
