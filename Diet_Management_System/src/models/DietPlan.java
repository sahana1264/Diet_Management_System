package models;

public class DietPlan {
    private int planId;
    private String name;
    private String goal;
    private String description;
    private double price;
    private int duration;

    public DietPlan(int planId, String name, String goal, String description, double price, int duration) {
        this.planId = planId;
        this.name = name;
        this.goal = goal;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    // Getters and Setters
    public int getPlanId() { return planId; }
    public void setPlanId(int planId) { this.planId = planId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
}
