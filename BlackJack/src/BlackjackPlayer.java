public class BlackjackPlayer extends Player {
    private Hand hand;

    public BlackjackPlayer(String name) {
        super(name);
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }

    public void hit(Dealer dealer) {
        dealer.dealCard(hand);
    }

    @Override
    public void play() {
        // doesn't make sense to use this as I want to just do user input in BJGame class
        // placeholder for abstract method since im forced to use provided code
    }

    public boolean isBusted() {
        return hand.isBusted();
    }
}