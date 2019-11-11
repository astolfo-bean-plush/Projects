import java.util.Scanner;
import java.util.ArrayList;
public class LinearSearchUserInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // vertically can only have 5
        // horizontal can have n
        System.out.println("Enter up to 5 integers, followed by a non-number: ");
        ArrayList<Integer> arr = new ArrayList<>();
        String userInput = "";
        userInput = sc.next();
        Scanner inp = new Scanner(userInput);
        int userSearch = 0;
        try {
            while (inp.hasNextInt()) {
                if (arr.size() == 4) {
                    arr.add(Integer.parseInt(userInput));
                    System.out.println("Maximum number of elements (5) reached, discarded excess.");
                    break;
                }
                arr.add(Integer.parseInt(userInput));
                userInput = sc.next();
            }
        } catch (Exception e) {
            System.out.println("");
        }
        System.out.println("Enter an integer to search for: ");
        sc.nextLine();
        try {
            userSearch = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Entry, search stopped!");
        }
        if (arr.size() == 0) {
            System.out.println("Did not find the element.");
        }
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == userSearch) {
                System.out.println("Found at position: " + i);
            }
            if (i == arr.size() + 1) {
                System.out.println("Did not find the element.");
            }
        }
    }
}