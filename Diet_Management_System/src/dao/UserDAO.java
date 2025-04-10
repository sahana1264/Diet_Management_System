package dao;

import models.User;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final Connection conn;

    public UserDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // Insert User
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (name, email, password_hash, age, gender, height, weight, activity_level, health_issues, dietary_preference, budget) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setInt(4, user.getAge());
            stmt.setString(5, user.getGender());
            stmt.setDouble(6, user.getHeight());
            stmt.setDouble(7, user.getWeight());
            stmt.setString(8, user.getActivityLevel());
            stmt.setString(9, user.getHealthIssues());
            stmt.setString(10, user.getDietaryPreference());
            stmt.setDouble(11, user.getBudget());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get User by Email
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getDouble("height"),
                        rs.getDouble("weight"),
                        rs.getString("activity_level"),
                        rs.getString("health_issues"),
                        rs.getString("dietary_preference"),
                        rs.getDouble("budget")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
