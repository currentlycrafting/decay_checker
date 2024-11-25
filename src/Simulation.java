import java.time.LocalDate;
import java.util.Random;

public class Simulation {

    private CircularFoodQueue foodQueue;
    private FoodStorage foodStorage;
    private int time; // Simulation time in arbitrary units (e.g., days)

    // Constructor
    public Simulation(CircularFoodQueue foodQueue, FoodStorage foodStorage) {
        this.foodQueue = foodQueue;
        this.foodStorage = foodStorage;
        this.time = 0; // Initialize time to zero
    }

    // Run the simulation
    public void runSimulation(int duration) {
        Random random = new Random();
        for (int t = 0; t < duration; t++) {
            System.out.println("\n--- Time: Day " + (time + 1) + " ---");
            time++;

            // Randomly enqueue or dequeue food items
            if (random.nextBoolean()) {
                addRandomFood();
            } else {
                removeExpiredFood();
            }

            // Display the current state of the queue
            foodQueue.display();

            // Optional: Simulate multiple queues in the storage
            foodStorage.displayAllQueues();
        }

        System.out.println("\nSimulation complete.");
    }

    // Simulate adding a random food item
    private void addRandomFood() {
        LocalDate currentDate = LocalDate.now().plusDays(time);
        LocalDate expirationDate = currentDate.plusDays((int) (Math.random() * 10 + 1)); // 1 to 10 days validity
        FoodItem newItem = new FoodItem("Food" + time, currentDate, expirationDate);

        if (foodQueue.enqueue(newItem)) {
            System.out.println("Added: " + newItem);
        } else {
            System.out.println("Failed to add food. Queue is full.");
        }
    }

    // Simulate removing expired food items
    private void removeExpiredFood() {
        LocalDate currentDate = LocalDate.now().plusDays(time);

        CircularFoodQueue tempQueue = new CircularFoodQueue(foodQueue.length()); // Temporary queue to hold non-expired items
        boolean removed = false;

        while (!foodQueue.isEmpty()) {
            FoodItem item = foodQueue.dequeue();
            if (item.getExpirationDate().isBefore(currentDate)) {
                System.out.println("Removed expired item: " + item);
                removed = true;
            } else {
                tempQueue.enqueue(item);
            }
        }

        // Restore the remaining items back to the original queue
        while (!tempQueue.isEmpty()) {
            foodQueue.enqueue(tempQueue.dequeue());
        }

        if (!removed) {
            System.out.println("No expired items to remove.");
        }
    }
}
