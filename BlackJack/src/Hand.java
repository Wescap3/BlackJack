import java.util.ArrayList;

public class Hand extends GroupOfCards {
    private int handValue;

    public Hand() {
        super(10);  // max hand size of 10, more than enough for blackjack
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
        calculateHandValue();
    }

    public int getHandValue() {
        return handValue;
    }

    private void calculateHandValue() {
        handValue = 0;
        int aces = 0;
        //calculate non-ace cards
        for (Card card : cards) {
            int cardValue = ((BlackjackCard) card).getValue();
            if (cardValue == 1) {
                aces++;
            }
            else {
                handValue += cardValue;
            }
        }
        //add all aces as 1 then decide if an ace should be 11
        //only 1 ace would ever be 11 so it's only checked once
        handValue += aces;
        if (aces > 0 && handValue + 10 <= 21) {
            handValue += 10;
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean isBusted() {
        return handValue > 21;
    }

    @Override
    public String toString() {
        String s = new String();
        for (Card card : cards) {
            s += card.toString() + ", ";
        }
        s += "(Hand Total: " + handValue + ")";
        return s;
    }
}