package models;

public class MealPlan {
    private int mealId;
    private int planId;
    private String mealType;
    private double calories;
    private double protein;
    private double carbs;
    private double fats;
    private String ingredients;

    public MealPlan(int mealId, int planId, String mealType, double calories, double protein, double carbs, double fats, String ingredients) {
        this.mealId = mealId;
        this.planId = planId;
        this.mealType = mealType;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.ingredients = ingredients;
    }

    // Getters and Setters
    public int getMealId() { return mealId; }
    public void setMealId(int mealId) { this.mealId = mealId; }
    public int getPlanId() { return planId; }
    public void setPlanId(int planId) { this.planId = planId; }
    public String getMealType() { return mealType; }
    public void setMealType(String mealType) { this.mealType = mealType; }
    public double getCalories() { return calories; }
    public void setCalories(double calories) { this.calories = calories; }
    public double getProtein() { return protein; }
    public void setProtein(double protein) { this.protein = protein; }
    public double getCarbs() { return carbs; }
    public void setCarbs(double carbs) { this.carbs = carbs; }
    public double getFats() { return fats; }
    public void setFats(double fats) { this.fats = fats; }
    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }
}
