import java.time.LocalDate;

public class FoodItem {
    private String name;
    private LocalDate storedDate;  // The date the item was added to storage
    private LocalDate expirationDate;  // The expiration date of the item

    // Constructor
    public FoodItem(String name, LocalDate storedDate, LocalDate expirationDate) {
        this.name = name;
        this.storedDate = storedDate;
        this.expirationDate = expirationDate;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public LocalDate getStoredDate() {
        return storedDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    // Override toString & equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodItem foodItem = (FoodItem) o;


        return name.equals(foodItem.name) &&
                storedDate.equals(foodItem.storedDate) &&
                expirationDate.equals(foodItem.expirationDate);
    }
    @Override
    public String toString() {
        return name + ":" +
                " Stored Date: " + storedDate +
                " Expiration Data: " + expirationDate;

    }
}