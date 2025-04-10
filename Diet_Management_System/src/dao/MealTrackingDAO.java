package dao;

import database.DatabaseConnection;
import models.MealTracking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealTrackingDAO {
    private final Connection conn;

    public MealTrackingDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // 🍽️ Track User Meal
    public boolean trackMeal(MealTracking mealTrack) {
        String sql = "INSERT INTO user_meal_tracking (user_id, meal_id, consumed_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mealTrack.getUserId());
            stmt.setInt(2, mealTrack.getMealId());
            stmt.setDate(3, new Date(mealTrack.getMealDate().getTime()));
            stmt.setString(4, mealTrack.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 📊 Get Meal Tracking Data for Progress
    public List<MealTracking> getMealTrackingData(int userId) {
        List<MealTracking> meals = new ArrayList<>();
        String sql = "SELECT * FROM user_meal_tracking WHERE user_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MealTracking meal = new MealTracking();
                meal.setTrackingId(rs.getInt("tracking_id"));
                meal.setUserId(rs.getInt("user_id"));
                meal.setMealId(rs.getInt("meal_id"));
                meal.setMealDate(rs.getDate("consumed_date"));
                meal.setStatus(rs.getString("status"));
                meals.add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }

    // 🥘 Get Meals from Diet Plan
    public java.util.Map<String, Integer> getMealsForUser(int userId) {
        java.util.Map<String, Integer> meals = new java.util.HashMap<>();
        String sql = """
           SELECT mp.meal_id, mp.meal_type, mp.calories, mp.protein, mp.carbs
                   FROM meal_plan mp
                   INNER JOIN user_diet ud ON mp.plan_id = ud.plan_id
                   WHERE ud.user_id = ? AND CURRENT_DATE BETWEEN ud.start_date AND ud.end_date;
      
        """ ;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                meals.put(rs.getString("meal_type"), rs.getInt("meal_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return meals;
    }
}
