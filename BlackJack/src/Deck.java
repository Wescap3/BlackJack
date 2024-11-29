import java.util.ArrayList;

public class Deck extends GroupOfCards {
    private static final int DECK_SIZE = 52;

    public Deck() {
        super(DECK_SIZE);
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        ArrayList<Card> deck = new ArrayList<>();
        for (String suit : suits) {
            for (int i = 1; i <= 13; i++) {
                String name;
                switch (i) {
                    case 1:
                        name = "Ace";
                        break;
                    case 11:
                        name = "Jack";
                        break;
                    case 12:
                        name = "Queen";
                        break;
                    case 13:
                        name = "King";
                        break;
                    default:
                        name = Integer.toString(i);
                        break;
                }
                int value = i > 10 ? 10 : i; //face cards are 10
                deck.add(new BlackjackCard(value, name, suit));
            }
        }
        cards = deck;
    }

    public BlackjackCard dealCard() {
        if (cards.size() > 0) {
            return (BlackjackCard) cards.remove(0);
        }
        return null;
    }
}