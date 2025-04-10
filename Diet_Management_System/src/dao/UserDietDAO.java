package dao;

import database.DatabaseConnection;
import models.UserDiet;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDietDAO {
    private final Connection conn;

    public UserDietDAO() {
        conn = DatabaseConnection.getConnection(); // Get database connection
    }

    // ✅ 1. Assign Diet Plan to User
    public boolean assignDietPlan(UserDiet userDiet) {
        String sql = "INSERT INTO user_diet (user_id, plan_id, start_date, end_date, adherence_rate) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userDiet.getUserId());              // User ID
            stmt.setInt(2, userDiet.getPlanId());              // Selected Plan ID
            stmt.setDate(3, userDiet.getStartDate());          // Start Date
            stmt.setDate(4, userDiet.getEndDate());            // End Date
            stmt.setDouble(5, userDiet.getAdherenceRate());    // Adherence Rate

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Return true if insertion successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean hasActivePlan(int userId) {
        boolean hasPlan = false;
        String query = "SELECT * FROM user_diet WHERE user_id = ? AND end_date >= CURDATE()";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                hasPlan = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hasPlan;
    }


    // ✅ 2. Get User's Diet Plan by User ID
    public UserDiet getUserDietByUserId(int userId) {
        String sql = "SELECT * FROM user_diet WHERE user_id = ?";
        UserDiet userDiet = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                userDiet = new UserDiet(
                        rs.getInt("user_id"),
                        rs.getInt("plan_id"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getDouble("adherence_rate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDiet;
    }

    // ✅ 3. Update Adherence Rate for User's Diet Plan
    public boolean updateAdherenceRate(int userId, double adherenceRate) {
        String sql = "UPDATE user_diet SET adherence_rate = ? WHERE user_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, adherenceRate);
            stmt.setInt(2, userId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Return true if update successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ 4. Delete Diet Plan for User
    public boolean deleteUserDiet(int userId) {
        String sql = "DELETE FROM user_diet WHERE user_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0; // Return true if deletion successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
