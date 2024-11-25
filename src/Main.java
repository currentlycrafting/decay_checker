
public class Main {
    public static void main(String[] args) {

        int amountOfQueues = 5;
        int sizeOfEachQueue = 10;

        FoodStorage foodQueues = new FoodStorage(amountOfQueues,sizeOfEachQueue);
        UserInterface ui = new UserInterface(5,foodQueues);
        ui.start();

    }
}