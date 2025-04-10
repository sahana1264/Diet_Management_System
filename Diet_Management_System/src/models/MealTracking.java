package models;

import java.util.Date;

public class MealTracking {
    private int trackingId;
    private int userId;
    private int mealId;
    private int planId;
    private String mealType;
    private Date mealDate;
    private String status;

    public MealTracking() {}

    public MealTracking(int userId, int mealId, Date mealDate, String status) {
        this.userId = userId;
        this.mealId = mealId;
        this.mealDate = mealDate;
        this.status = status;
    }

    // Getters and setters
    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public Date getMealDate() {
        return mealDate;
    }

    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
