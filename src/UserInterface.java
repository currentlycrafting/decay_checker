import java.time.LocalDate;
import java.util.Scanner;
public class UserInterface {
    private CircularFoodQueue foodQueue;
    private int size;
    private FoodStorage foodStorage;

    public UserInterface(int size, FoodStorage foodStorage) {
        this.foodStorage = foodStorage;
        this.size = size;
        foodQueue = new CircularFoodQueue(size);
    }

    public void start() { // start the UI
        Scanner input = new Scanner(System.in); // create new Scanner
        boolean running = true; // Creates the switch for running or not

        while (running) {

            System.out.println("\nOptions: ");
            // Single Queue Options
            System.out.println("1. Add Food Item");
            System.out.println("2. Remove Food Item");
            System.out.println("3. Display Current Queue");

            // Multiple Queue Options
            System.out.println("4. Add Queue to Multi-Queue Storage ");
            System.out.println("5. Display All Queues in Storage ");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    addFoodItem(input);
                    break;
                case 2:
                    removeFoodItem(input);
                    break;
                case 3:
                    foodQueue.display();
                    break;
                case 4:
                    addQueueToFoodStorage(input);
                    break;
                case 5:
                    displayQueues();
                    break;
                case 6:
                    runSimulation(input);
                    break;
                case 7:
                    running = false;
                    break;


                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void removeFoodItem(Scanner input) { // O(N) Search and Removal
            input.nextLine();
            System.out.print("Look up the Food's name: ");
            String lookupName = input.nextLine();

            System.out.print("Look up storage date (YYYY-MM-DD): ");
            LocalDate lookupStoredDate = LocalDate.parse(input.next());

            System.out.print("Look up expiration date (YYYY-MM-DD): ");
            LocalDate lookupExpirationDate = LocalDate.parse(input.next());

        FoodItem targetFood = new FoodItem(lookupName, lookupStoredDate, lookupExpirationDate); // target food
        CircularFoodQueue tempQueue = new CircularFoodQueue(size); // temp queue for moving all data to remove

        // Checks if the current dequeue is the target. If not than it'll add it to the Temp Queue.
        // If Target found it breaks. Else it'll put the rest of the elements in the temp Queue
        // Once the target is found it'll skip the target by breaking right after the target is found
        // This causes the target to lose its place in the enqueue. Then it'll loop the rest of the elements
        // not including the target inside the Queue.
        boolean itemRemoved = false;
        while (!foodQueue.isEmpty()) {
            FoodItem currentFood = foodQueue.dequeue();
            if (currentFood.equals(targetFood)) {
                System.out.println("Food item removed from queue.");
                itemRemoved = true;
                break;
            } else {
                tempQueue.enqueue(currentFood);
            }
        }


        while (!foodQueue.isEmpty()) { // Places all remaining elements in the temp Queue whether target was found or not
            tempQueue.enqueue(foodQueue.dequeue());
        }
        while (!tempQueue.isEmpty()) { // Takes all the Temp elements and places them back into the original queue
            foodQueue.enqueue(tempQueue.dequeue());
        }
        if (!itemRemoved) {
            System.out.println("Food item not found in queue."); // if no item was removed than it was not found in the queue
        }
    }

    private void addFoodItem(Scanner input) { // O(1) Constant Time
            input.nextLine();  // Clear the buffer
            System.out.print("Enter food name: ");
            String name = input.nextLine();

            System.out.print("Enter storage date (YYYY-MM-DD): ");
            LocalDate storedDate = LocalDate.parse(input.next());

            System.out.print("Enter expiration date (YYYY-MM-DD): ");
            LocalDate expirationDate = LocalDate.parse(input.next());

            // Simple enqueue that adds a new object into the current single queue

            FoodItem addedFoodItem = new FoodItem(name, storedDate, expirationDate); // creates a new food Item
            if (foodQueue.enqueue(addedFoodItem)) {
                System.out.println("Food item added to queue.");
            } else {
                System.out.println("Queue is full. Could not add item.");
            }
        }



    private void addQueueToFoodStorage(Scanner input) {

        // In main, it'll ask the amount of Queues needed in the Multi-Storage Queue
        // This will choose the index that you want to place the current single Queue in
        // Ex: If we had initialized a Queue of 5. You can choose an index from 0-4 to place
        // the current single Queue.
        System.out.println("Enter in the index for the queue placement");
        int queueIndex = input.nextInt();

        // This places the current Queue iteration inside the Queue storage

        boolean result = foodStorage.addFoodToQueue(queueIndex, foodQueue.dequeue());
        if (result) {
            System.out.println("Queue added to multi-queue storage.");
        } else {
            System.out.println("Failed to add queue to storage.");
        }

    }

    private void createSim(Scanner input) {

    }

    private void displayQueues() {
        foodStorage.displayAllQueues();  // This will display all queues with their food items
    }

    private void runSimulation(Scanner input) {
        System.out.println("Starting simulation...");
        System.out.println("Choose a simulation option:");
        System.out.println("1. Process food items (dequeue and display)");
        System.out.println("2. Check for expired food items");
        System.out.println("3. Transfer food items between queues");

        int simChoice = input.nextInt();

        switch (simChoice) {
            case 1:
                processFoodItems();
                break;
            case 2:
                checkExpiredFoodItems();
                break;
            case 3:
                transferFoodItems(input);
                break;
            default:
                System.out.println("Invalid simulation option.");
        }
    }

    // Process and display each food item in the queue
    private void processFoodItems() {
        System.out.println("Processing food items in the current queue...");
        while (!foodQueue.isEmpty()) {
            FoodItem food = foodQueue.dequeue();
            System.out.println("Processed: " + food);
        }
        System.out.println("All food items have been processed.");
    }

    // Check for expired food items
    private void checkExpiredFoodItems() {
        CircularFoodQueue tempQueue = new CircularFoodQueue(size);
        LocalDate today = LocalDate.now();

        System.out.println("Checking for expired food items...");
        while (!foodQueue.isEmpty()) {
            FoodItem food = foodQueue.dequeue();
            if (food.getExpirationDate().isBefore(today)) {
                System.out.println("Expired: " + food);
            } else {
                tempQueue.enqueue(food); // Requeue non-expired items
            }
        }

        // Restore non-expired items to the original queue
        while (!tempQueue.isEmpty()) {
            foodQueue.enqueue(tempQueue.dequeue());
        }
        System.out.println("Expired food items check complete.");
    }

    // Transfer food items between queues
    private void transferFoodItems(Scanner input) {
        System.out.print("Enter the source queue index: ");
        int sourceIndex = input.nextInt();

        System.out.print("Enter the destination queue index: ");
        int destIndex = input.nextInt();

        CircularFoodQueue sourceQueue = foodStorage.getQueue(sourceIndex);
        CircularFoodQueue destQueue = foodStorage.getQueue(destIndex);

        if (sourceQueue == null || destQueue == null) {
            System.out.println("Invalid queue index.");
            return;
        }

        System.out.println("Transferring food items...");
        while (!sourceQueue.isEmpty() && !destQueue.isFull()) {
            FoodItem food = sourceQueue.dequeue();
            destQueue.enqueue(food);
        }

        System.out.println("Food transfer complete.");
    }

}
