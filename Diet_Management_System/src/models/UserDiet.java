package models;

import java.sql.Date; // ✅ Correct Date class for SQL

public class UserDiet {
    private int userId;
    private int planId;
    private Date startDate;
    private Date endDate;
    private double adherenceRate;

    public UserDiet(int userId, int planId, Date startDate, Date endDate, double adherenceRate) {
        this.userId = userId;
        this.planId = planId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.adherenceRate = adherenceRate;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getAdherenceRate() {
        return adherenceRate;
    }

    public void setAdherenceRate(double adherenceRate) {
        this.adherenceRate = adherenceRate;
    }
}
