public class Dealer extends Player {
    private Deck deck;
    private Hand hand;

    public Dealer() {
        super("Dealer");
        this.deck = new Deck();
        this.hand = new Hand();
    }

    public void dealCard(Hand targetHand) {
        BlackjackCard card = deck.dealCard();
        targetHand.addCard(card);
    }
    public void dealCard(BlackjackPlayer targetPlayer) {
        BlackjackCard card = deck.dealCard();
        targetPlayer.getHand().addCard(card);
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public void play() {
        // hit until hand value is at least 17
        while (hand.getHandValue() < 17) {
            dealCard(hand);
        }
    }

    public boolean shouldHit() {
        return hand.getHandValue() < 17;
    }

    public String showOneCard() {
        return hand.getCards().get(0).toString();
    }
}