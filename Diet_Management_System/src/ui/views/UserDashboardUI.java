// No package changes
package ui.views;

import dao.MealTrackingDAO;
import dao.UserDietDAO;
import dao.DietPlanDAO;
import models.MealTracking;
import models.DietPlan;
import models.User;
import models.UserDiet;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public class UserDashboardUI extends JFrame {
    private final User user;

    public UserDashboardUI(User user) {
        this.user = user;
        setTitle("Dashboard - Welcome " + user.getName());
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getName() + "!");
        JButton viewPlansButton = new JButton("📋 View Diet Plans");
        JButton trackMealButton = new JButton("🍽️ Track Meals");
        JButton progressButton = new JButton("📊 View Progress");

        add(progressButton);
        add(welcomeLabel);
        add(viewPlansButton);
        add(trackMealButton);

        viewPlansButton.addActionListener(e -> showDietPlans());
        trackMealButton.addActionListener(e -> trackMeal());
        progressButton.addActionListener(e -> showProgress());

        setLocationRelativeTo(null);
    }

    private void assignDietPlan(int userId, int planId) {
        UserDietDAO userDietDAO = new UserDietDAO();

        if (userDietDAO.hasActivePlan(userId)) {
            JOptionPane.showMessageDialog(this, "⚠️ You already have an active diet plan!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        UserDiet userDiet = new UserDiet(userId, planId, new Date(System.currentTimeMillis()), Date.valueOf("2026-05-22"), 0.00);

        if (userDietDAO.assignDietPlan(userDiet)) {
            JOptionPane.showMessageDialog(this, "🥗 Diet plan assigned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Failed to assign diet plan.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showDietPlans() {
        DietPlanDAO planDAO = new DietPlanDAO();
        List<DietPlan> plans = planDAO.getAllDietPlans();

        if (plans.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ No diet plans available.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder planList = new StringBuilder("Available Diet Plans:\n");
        for (DietPlan plan : plans) {
            planList.append(plan.getPlanId()).append(". ").append(plan.getName()).append(" - $").append(plan.getPrice()).append("\n");
        }

        JOptionPane.showMessageDialog(this, planList.toString(), "Diet Plans", JOptionPane.INFORMATION_MESSAGE);

        String selectedPlan = JOptionPane.showInputDialog(this, "Enter the number of the diet plan to select:", "Select Diet Plan", JOptionPane.QUESTION_MESSAGE);

        if (selectedPlan != null && !selectedPlan.isEmpty()) {
            try {
                int planId = Integer.parseInt(selectedPlan);
                boolean validPlan = plans.stream().anyMatch(p -> p.getPlanId() == planId);

                if (validPlan) {
                    assignDietPlan(user.getUserId(), planId);
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Invalid plan selected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "❌ Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void trackMeal() {
        MealTrackingDAO mealTrackingDAO = new MealTrackingDAO();
        Map<String, Integer> mealMap = mealTrackingDAO.getMealsForUser(user.getUserId());

        if (mealMap.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ No meals found for your diet plan.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] mealNames = mealMap.keySet().toArray(new String[0]);
        String selectedMeal = (String) JOptionPane.showInputDialog(this, "Select a meal to track:", "Track Meal", JOptionPane.QUESTION_MESSAGE, null, mealNames, mealNames[0]);

        if (selectedMeal != null) {
            String[] statusOptions = {"Consumed", "Missed"};
            String selectedStatus = (String) JOptionPane.showInputDialog(this, "Did you consume the meal?", "Meal Status", JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

            if (selectedStatus != null) {
                int mealId = mealMap.get(selectedMeal);
                recordMealTracking(user.getUserId(), mealId, selectedStatus);
            }
        }
    }

    private void recordMealTracking(int userId, int mealId, String status) {
        MealTrackingDAO mealTrackingDAO = new MealTrackingDAO();
        Date mealDate = new Date(System.currentTimeMillis());

        MealTracking mealTrack = new MealTracking(userId, mealId, mealDate, status);
        if (mealTrackingDAO.trackMeal(mealTrack)) {
            JOptionPane.showMessageDialog(this, "✅ Meal tracked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Failed to track meal.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showProgress() {
        MealTrackingDAO mealTrackingDAO = new MealTrackingDAO();
        List<MealTracking> mealList = mealTrackingDAO.getMealTrackingData(user.getUserId());

        int consumed = 0, missed = 0, todayConsumed = 0;
        Date today = new Date(System.currentTimeMillis());

        for (MealTracking meal : mealList) {
            if ("Consumed".equalsIgnoreCase(meal.getStatus())) {
                consumed++;
                if (meal.getMealDate().equals(today)) {
                    todayConsumed++;
                }
            } else if ("Missed".equalsIgnoreCase(meal.getStatus())) {
                missed++;
            }
        }

        int total = consumed + missed;
        double adherence = total > 0 ? (double) consumed / total * 100 : 0.0;

        String message = String.format("📊 Your Progress:\n\n✅ Meals Consumed: %d\n❌ Meals Missed: %d\n📅 Meals Consumed Today: %d\n📈 Adherence Rate: %.2f%%", consumed, missed, todayConsumed, adherence);
        JOptionPane.showMessageDialog(this, message, "Your Progress", JOptionPane.INFORMATION_MESSAGE);
    }
}
