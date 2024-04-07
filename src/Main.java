import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        // Menu-driven loop
        String choice;
        do {
            // Display the menu
            displayMenu();
            // Get user choice
            choice = SafeInput.getRegExString(scanner, "Enter your choice (A/D/P/Q): ", "[AaDdPpQq]");
            // Execute the user's choice
            executeChoice(choice.toUpperCase().charAt(0), list, scanner); // Convert to upper case and get the first character
        } while (!choice.equalsIgnoreCase("Q")); // Repeat until user chooses to quit

        // Close the scanner
        scanner.close();
    }

    // Method to display the menu
    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit");
    }

    // Method to execute user's choice
    private static void executeChoice(char choice, ArrayList<String> list, Scanner scanner) {
        switch (choice) {
            case 'A':
                addItem(list, scanner);
                break;
            case 'D':
                deleteItem(list, scanner);
                break;
            case 'P':
                printList(list);
                break;
            case 'Q':
                if (confirmQuit(scanner)) {
                    System.out.println("Exiting the program...");
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Method to add an item to the list
    private static void addItem(ArrayList<String> list, Scanner scanner) {
        System.out.println("Adding an item to the list...");
        System.out.print("Enter the item to add: ");
        String item = scanner.nextLine();
        list.add(item);
        System.out.println("Item added successfully.");
    }

    // Method to delete an item from the list
    private static void deleteItem(ArrayList<String> list, Scanner scanner) {
        if (list.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }
        System.out.println("Deleting an item from the list...");
        printListWithIndices(list);
        int index = SafeInput.getRangedInt(scanner, "Enter the number of the item to delete", 1, list.size()) - 1;
        String removedItem = list.remove(index);
        System.out.println("Item '" + removedItem + "' removed successfully.");
    }

    // Method to print the list
    private static void printList(ArrayList<String> list) {
        System.out.println("Printing the list...");
        if (list.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }
        for (String item : list) {
            System.out.println(item);
        }
    }

    // Method to display the list with indices
    private static void printListWithIndices(ArrayList<String> list) {
        System.out.println("Current List:");
        if (list.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    // Method to confirm quitting
    private static boolean confirmQuit(Scanner scanner) {
        return SafeInput.getYNConfirm(scanner, "Are you sure you want to quit? (Y/N)");
    }
}
