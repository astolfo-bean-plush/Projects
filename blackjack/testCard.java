import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class testCard {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        BubbleSortCards bs = new BubbleSortCards();
        blackJack b = new blackJack();
        b.setDealer();
        int playerVal = 0;
        int firstplayerVal = 0;
        int oppVal = 0;
        int userInput = 0;
        int oppChoice = 0;
        String dealer = b.getDealer();
        boolean bustState = false;
        // generate a hand and print
        ArrayList<Card> myHand = b.generateHand();
        ArrayList<Card> oppHand = b.generateHand();
        playerVal = b.printHand(myHand, 1);
        oppVal = b.printOppHand(oppHand, 1);

        // if hand doesn't = 21, allow hit or sit
        while (!(playerVal >= 21)) {
            System.out.println("Choose: ");
            System.out.println("1. Sit");
            System.out.println("2. Hit");
            userInput = sc.nextInt();
            System.out.println("");
            // user hits
            if (userInput == 2) {
                System.out.println("--------------------------------------------");
                b.hit(myHand, 1);
                playerVal = b.printHand(myHand, 1);
                // make sure hit doesn't exceed 21 or lose
                bustState = b.bustChecker(playerVal, 1);
                if (bustState) {
                    System.out.println("");
                    b.printOppHand(oppHand, 3);
                    System.out.println("");
                    b.printHand(myHand, 2);
                    System.exit(0);
                }
                System.out.println(dealer +"'s revealed card: ");
                System.out.println(oppHand.get(0));
                System.out.println("");
                }
            // user sits
            else {
                System.out.println("You sit");
                System.out.println("--------------------------------------------");
                break;
            }
        }

        System.out.println(dealer +" uses big brain...");
        TimeUnit.SECONDS.sleep(1);
        while (!(oppVal >= 21)) {
            System.out.println("");
            // decide what ai will do based on players first card
            firstplayerVal = myHand.get(0).value;
            oppChoice = b.opponentAlgorithm(firstplayerVal, oppVal);
            if (oppChoice == 2) {
                System.out.println(dealer +" hits");
                b.hit(oppHand, 2);
                TimeUnit.SECONDS.sleep(1);
                oppVal = b.printOppHand(oppHand, 2);
                bustState = b.bustChecker(oppVal, 2);
                if (bustState) {
                    b.printOppHand(oppHand, 3);
                    System.out.println("");
                    b.printHand(myHand, 3);
                    System.exit(0);
                }
            }
            else {
                System.out.println(dealer +" Sits");
                TimeUnit.SECONDS.sleep(1);
                break;
            }
        }
        System.out.println("--------------------------------------------");
        b.result(playerVal, myHand, oppVal, oppHand);

    }
}
