package dao;

import models.DietPlan;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DietPlanDAO {
    private final Connection conn;

    public DietPlanDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // Add Diet Plan
    public boolean addDietPlan(DietPlan plan) {
        String sql = "INSERT INTO diet_plans (name, goal, description, price, duration) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, plan.getName());
            stmt.setString(2, plan.getGoal());
            stmt.setString(3, plan.getDescription());
            stmt.setDouble(4, plan.getPrice());
            stmt.setInt(5, plan.getDuration());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get All Diet Plans
    public List<DietPlan> getAllDietPlans() {
        List<DietPlan> plans = new ArrayList<>();
        String sql = "SELECT * FROM diet_plans";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DietPlan plan = new DietPlan(
                        rs.getInt("plan_id"),
                        rs.getString("name"),
                        rs.getString("goal"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("duration")
                );
                plans.add(plan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plans;
    }
}
