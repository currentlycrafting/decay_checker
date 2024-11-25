import javax.swing.*;

public class CircularFoodQueue {
    private FoodItem[] foodQueue;
    private int front;
    private int rear;
    private int size;
    private int count;

    // Constructor
    public CircularFoodQueue(int size) {
        this.size = size;
        this.foodQueue = new FoodItem[size];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    // Method to add a food item to the queue
    public boolean enqueue(FoodItem item) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot add more food.");
            return false;
        }

        rear = (rear + 1) % size;
        foodQueue[rear] = item;
        count++;
        return true;
    }

    // Method to remove a food item from the queue
    public FoodItem dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null; // or throw an exception
        }

        FoodItem item = foodQueue[front];
        front = (front + 1) % size;
        count--;
        return item;
    }


    // Check if the queue is full
    public boolean isFull() {
        return count == size;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (isEmpty()) {
            sb.append("Queue is empty.");
        } else {
            int i = front;
            // Loop over the circular queue
            while (i != (rear + 1) % size) {
                sb.append(foodQueue[i]).append(" ");
                i = (i + 1) % size;  // Circular increment
            }
        }

        return sb.toString().trim();  // Return the string, trimming any extra space at the end
    }


    public void display() {
        System.out.println(this);  // This will call the toString() method
    }

    public int length() {
        return size;
    }
}
