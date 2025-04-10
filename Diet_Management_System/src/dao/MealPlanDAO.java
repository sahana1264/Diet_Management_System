package dao;

import models.MealPlan;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealPlanDAO {
    private final Connection conn;

    public MealPlanDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // Add Meal Plan
    public boolean addMealPlan(MealPlan meal) {
        String sql = "INSERT INTO meal_plan (plan_id, meal_type, calories, protein, carbs, fats, ingredients) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, meal.getPlanId());
            stmt.setString(2, meal.getMealType());
            stmt.setDouble(3, meal.getCalories());
            stmt.setDouble(4, meal.getProtein());
            stmt.setDouble(5, meal.getCarbs());
            stmt.setDouble(6, meal.getFats());
            stmt.setString(7, meal.getIngredients());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get Meals by Plan ID
    public List<MealPlan> getMealsByPlanId(int planId) {
        List<MealPlan> meals = new ArrayList<>();
        String sql = "SELECT * FROM meal_plan WHERE plan_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, planId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MealPlan meal = new MealPlan(
                        rs.getInt("meal_id"),
                        rs.getInt("plan_id"),
                        rs.getString("meal_type"),
                        rs.getDouble("calories"),
                        rs.getDouble("protein"),
                        rs.getDouble("carbs"),
                        rs.getDouble("fats"),
                        rs.getString("ingredients")
                );
                meals.add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }
}
