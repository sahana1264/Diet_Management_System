package models;

public class User {
    private int userId;
    private String name;
    private String email;
    private String passwordHash;
    private int age;
    private String gender;
    private double height;
    private double weight;
    private String activityLevel;
    private String healthIssues;
    private String dietaryPreference;
    private double budget;

    public User(int userId, String name, String email, String passwordHash, int age,
                String gender, double height, double weight, String activityLevel,
                String healthIssues, String dietaryPreference, double budget) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.healthIssues = healthIssues;
        this.dietaryPreference = dietaryPreference;
        this.budget = budget;
    }

    // Getters & Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public String getActivityLevel() { return activityLevel; }
    public void setActivityLevel(String activityLevel) { this.activityLevel = activityLevel; }
    public String getHealthIssues() { return healthIssues; }
    public void setHealthIssues(String healthIssues) { this.healthIssues = healthIssues; }
    public String getDietaryPreference() { return dietaryPreference; }
    public void setDietaryPreference(String dietaryPreference) { this.dietaryPreference = dietaryPreference; }
    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }
}
