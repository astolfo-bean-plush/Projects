import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class blackJack {
    private Random rand = new Random();
    Scanner sc = new Scanner(System.in);
    BubbleSortCards bs = new BubbleSortCards();
    private int playerVal = 0;
    private int firstplayerVal = 0;
    private int oppVal = 0;
    private int ranNum = 0;
    private String dealer = "";
    private Card[] deck = {Card.two, Card.two, Card.two, Card.two, Card.three, Card.three, Card.three, Card.three,
            Card.four, Card.four, Card.four, Card.four, Card.five, Card.five, Card.five, Card.five,
            Card.six, Card.six, Card.six, Card.six, Card.seven, Card.seven, Card.seven, Card.seven,
            Card.eight, Card.eight, Card.eight, Card.eight, Card.nine, Card.nine, Card.nine, Card.nine,
            Card.ten, Card.ten, Card.ten, Card.ten, Card.jack, Card.jack, Card.jack, Card.jack, Card.queen,
            Card.queen, Card.queen, Card.queen, Card.king, Card.king, Card.king, Card.king, Card.king,
            Card.ace, Card.ace, Card.ace, Card.ace};
    private String[] dealers = {"Paul", "Andres", "Jeremy", "Chris", "Isaac", "Elijah Kennedy",
                        "Tommy", "Frederick", "Jeffery", "Andre", "Tlaloc"};

    void setDealer() {
        dealer = dealers[rand.nextInt(dealers.length)];
    }

    String getDealer() {
        return dealer;
    }
    ArrayList<Card> generateHand() {
        ArrayList<Card> Hand = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ranNum = rand.nextInt(deck.length);
            Hand.add(deck[ranNum]);
        }
        return Hand;
    }
    int printHand(ArrayList<Card> Hand, int type) {
        if (type == 1) {
            bs.bubbleSortCards(Hand);
            System.out.println("Your hand: ");
            for (Card c : Hand) {
                System.out.print(c + " ");
                playerVal += c.value;
            }
            System.out.println("");
            System.out.println("Value: " + playerVal);
            System.out.println("");
            return playerVal;
        }
        else {
            bs.bubbleSortCards(Hand);
            System.out.println("Your Hand: ");
            for (Card c : Hand) {
                System.out.print(c + " ");
            }
            return oppVal;
        }
    }
    int printOppHand(ArrayList<Card> Hand, int type) {
        if (type == 1) {
            bs.bubbleSortCards(Hand);
            System.out.println(getDealer() + " reveals 1 card: ");
            System.out.println(Hand.get(0));
            for (Card c : Hand) {
                oppVal += c.value;
            }
            System.out.println("");
            return oppVal;
        }
        else if (type == 2) {
            for (Card c: Hand) {
                oppVal += c.value;
            }
            return oppVal;
        }
        else {
            bs.bubbleSortCards(Hand);
            System.out.println(getDealer() + "'s hand: ");
            for (Card c : Hand) {
                System.out.print(c + " ");
            }
            return oppVal;
        }
    }

    void hit(ArrayList<Card> Hand, int turn) {
        if (turn == 1) {
            ranNum = rand.nextInt(deck.length);
            Hand.add(deck[ranNum]);
            System.out.println("You're Dealt a/an: " + deck[ranNum].toString());
            playerVal = 0;
        }
        else {
            ranNum = rand.nextInt(deck.length);
            Hand.add(deck[ranNum]);
            System.out.println(getDealer() + "'s Dealt a/an: " + deck[ranNum].toString());
            oppVal = 0;
        }
    }
    int opponentAlgorithm(int fpVal, int dVal) {
        if (dVal >= 17) {
            return 1;
        }
        else if (dVal >= 13 && dVal <= 16) {
            if (fpVal <= 6) {
                return 1;
            }
            return 2;
        }
        else if (dVal == 12) {
            if (fpVal == 2 || fpVal == 3) {
                return 2;
            }
            else if (fpVal >= 4 && fpVal <= 6) {
                return 1;
            }
            return 2;
        }
        else {
            return 2;
        }
    }

    boolean bustChecker(int Val, int turn) {
        if (turn == 1) {
            if (Val > 21) {
                System.out.println("--------------------------------------------");
                System.out.println("");
                System.out.println("Busted, you lose");
                return true;
            }
        }
        else {
            if (Val > 21) {
                System.out.println("--------------------------------------------");
                System.out.println("");
                System.out.println(getDealer() +" busted, you win.");
                return true;
            }
        }
        return false;
    }
    void result(int pVal, ArrayList<Card> pHand, int oVal, ArrayList<Card> oHand) {
        System.out.println("Results: ");
        if (pVal > oVal) {
            System.out.println("You won");
            System.out.println("");
        }
        else if(pVal < oVal) {
            System.out.println("You lost");
            System.out.println("");
        }
        else {
            System.out.println("You tied");
            System.out.println("");

        }
        printOppHand(oHand, 3);
        System.out.println("");
        System.out.println("Value: " + oppVal);
        System.out.println("");
        printHand(pHand, 2);
        System.out.println("");
        System.out.println("Value: " + pVal);
    }
}
// TODO: IF ACE IS IN YOUR HAND,
// TODO: 1. You consider it a 1 if your sum >= 11
// TODO: 2. You consider it an 11 if your sum <= 10
// TODO: MAYBE MODIFY HAND IN BUST STATE
