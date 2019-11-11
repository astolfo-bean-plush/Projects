import java.util.ArrayList;

public class BubbleSortCards {
    void bubbleSortCards(ArrayList<Card> card) {
        for (int i = 0; i < card.size() - 1; i++) {
            for (int j = 0; j < card.size() - i - 1; j++) {
                if (card.get(j).compareTo(card.get(j + 1)) > 0) {
                    Card temp = card.get(j);
                    card.set(j, card.get(j + 1));
                    card.set(j + 1, temp);
                }
            }
        }
    }
}
