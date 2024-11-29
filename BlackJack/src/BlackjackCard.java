public class BlackjackCard extends Card {
    private int value;
    private String name;
    private String suit;
    private boolean faceUp;

    public BlackjackCard(int value, String name, String suit) {
        this.value = value;
        this.suit = suit;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void flip() {
        faceUp = !faceUp;
    }

    @Override
    public String toString() {
        return name + " of " + suit;
    }
}