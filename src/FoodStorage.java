import java.util.ArrayList;
import java.util.List;

public class FoodStorage {
    private List<CircularFoodQueue> queues;

    // Constructor: Initializes an empty list of queues
    public FoodStorage(int numberOfQueues, int queueSize) {
        queues = new ArrayList<>();
        for (int i = 0; i < numberOfQueues; i++) {
            queues.add(new CircularFoodQueue(queueSize));  // Initialize each queue with the given size
        }
    }

    // Add a food item to a specific queue
    public boolean addFoodToQueue(int queueIndex, FoodItem foodItem) {
        if (queueIndex >= 0 && queueIndex < queues.size()) {
            CircularFoodQueue queue = queues.get(queueIndex);
            return queue.enqueue(foodItem);
        }
        System.out.println("Invalid queue index.");
        return false;
    }

    // Remove a food item from a specific queue
    public FoodItem removeFoodFromQueue(int queueIndex) {
        if (queueIndex >= 0 && queueIndex < queues.size()) {
            CircularFoodQueue queue = queues.get(queueIndex);
            return queue.dequeue();  // Remove food item from the specified queue
        }
        System.out.println("Invalid queue index.");
        return null;
    }

    // Display all queues and their contents
    public void displayAllQueues() {
        for (int i = 0; i < queues.size(); i++) {
            System.out.println("Queue " + i + ":");
            queues.get(i).display();  // This will now display the actual contents of the queue
        }
    }

    // Get a specific queue
    public CircularFoodQueue getQueue(int queueIndex) {
        if (queueIndex >= 0 && queueIndex < queues.size()) {
            return queues.get(queueIndex);
        }
        return null;  // Return null if the index is out of bounds
    }
}
