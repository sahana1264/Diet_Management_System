package ui.views;

import dao.MealTrackingDAO;
import models.MealTracking;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class MealTrackingUI extends JFrame {
    private int userId;

    public MealTrackingUI(int userId) {
        this.userId = userId;
        setTitle("Track Your Meal");
        setSize(350, 200);
        setLayout(new GridLayout(4, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel mealLabel = new JLabel("Select meal:");
        String[] meals = {"1 - Breakfast", "2 - Lunch", "3 - Dinner", "4 - Snacks"};
        JComboBox<String> mealDropdown = new JComboBox<>(meals);

        JLabel statusLabel = new JLabel("Select status:");
        String[] statuses = {"Eaten", "Skipped", "Partial"};
        JComboBox<String> statusDropdown = new JComboBox<>(statuses);

        JButton trackButton = new JButton("Track");

        add(mealLabel);
        add(mealDropdown);
        add(statusLabel);
        add(statusDropdown);
        add(new JLabel()); // empty cell
        add(trackButton);

        trackButton.addActionListener(e -> {
            String mealString = (String) mealDropdown.getSelectedItem();
            int mealId = Integer.parseInt(mealString.split(" ")[0]);
            String status = (String) statusDropdown.getSelectedItem();

            MealTracking tracking = new MealTracking(userId, mealId, new Date(), status);
            boolean success = new MealTrackingDAO().trackMeal(tracking);

            if (success) {
                JOptionPane.showMessageDialog(this, "Meal tracked successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to track meal.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setLocationRelativeTo(null);
    }
}
