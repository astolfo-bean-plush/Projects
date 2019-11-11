public class Card {
    private String face;
    public int value;
    public int value2;
    public Card(String face, int value) {
        this.face = face;
        this.value = value;
    }
    public Card(String face, int value, int value2) {
        this.face = face;
        this.value = value;
        this.value2 = value2;
    }
    public static Card ace = new Card("A", 11, 1);
    public static Card jack = new Card("J", 10);
    public static Card queen = new Card("Q", 10);
    public static Card king = new Card("K", 10);
    public static Card two = new Card("2", 2);
    public static Card three = new Card("3", 3);
    public static Card four = new Card("4", 4);
    public static Card five = new Card("5", 5);
    public static Card six = new Card("6", 6);
    public static Card seven = new Card("7", 7);
    public static Card eight = new Card("8", 8);
    public static Card nine = new Card("9", 9);
    public static Card ten = new Card("10", 10);

    public int compareTo(Card c2) {
        if(this.value > c2.value) {
            return 1;
        }
        if(this.value < c2.value) {
            return -1;
        }
        else {
            return 0;
        }
    }
    public String toString() {
        return face;
    }
}
