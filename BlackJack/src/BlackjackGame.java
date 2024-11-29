import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGame extends Game {
    private Dealer dealer;
    private ArrayList<BlackjackPlayer> blackjackPlayers;
    private Scanner scanner;

    public BlackjackGame() {
        super("Blackjack");
        this.dealer = new Dealer();
        this.blackjackPlayers = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addPlayer(BlackjackPlayer player) {
        blackjackPlayers.add(player);
    }

    private void dealInitialCards() {
        // deal 2 cards to each player and dealer
        for (int i = 0; i < 2; i++) {
            for (BlackjackPlayer player : blackjackPlayers) {
                dealer.dealCard(player.getHand());
            }
            dealer.dealCard(dealer.getHand());
        }
        // show one of dealer's cards
        System.out.println("Dealer shows one of their cards: " + dealer.showOneCard());
    }

    @Override
    public void declareWinner() {
        System.out.println("\n---- Results ----");
        System.out.println("Dealers hand: " + dealer.getHand().toString());
        int dealerScore = dealer.getHand().getHandValue();
        for (BlackjackPlayer player : blackjackPlayers) {
            System.out.println(player.getName() + "'s hand: " + player.getHand().toString());
            int playerScore = player.getHand().getHandValue();

            if (player.isBusted()) {
                System.out.println(player.getName() + " lost");
            } else if (dealerScore > 21 || playerScore > dealerScore) {
                System.out.println(player.getName() + " wins!");
            } else if (playerScore == dealerScore) {
                System.out.println(player.getName() + " ties");
            } else {
                System.out.println(player.getName() + " lost");
            }
        }
    }

    public void setupPlayers() {
        System.out.println("Welcome to Blackjack!");
        int numPlayers;

        // get # of players
        while (true) {
            try {
                System.out.print("Enter number of players (1-4): ");
                numPlayers = scanner.nextInt();
                scanner.nextLine();

                if (numPlayers > 0 && numPlayers <= 4) {
                    break;
                } else {
                    System.out.println("Enter number between 1-4");
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }

        // create players
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String playerName = scanner.nextLine();
            BlackjackPlayer player = new BlackjackPlayer(playerName);
            addPlayer(player);
        }
    }

    @Override
    public void play() {

        setupPlayers();

        dealInitialCards();

        handlePlayerTurns();

        handleDealerTurn();

        declareWinner();
    }

    private void handlePlayerTurns() {
        for (BlackjackPlayer player : blackjackPlayers) {
            System.out.println("\n---- " + player.getName() + "'s turn ----");
            System.out.println("Your hand: " + player.getHand().toString());

            while (!player.isBusted()) {
                System.out.print("Hit or stand? (H/S): ");
                String choice = scanner.nextLine().toUpperCase();

                if (choice.equals("H")) {
                    player.hit(dealer);
                    System.out.println("Your hand: " + player.getHand().toString());

                    if (player.isBusted()) {
                        System.out.println(player.getName() + " busts");
                        break;
                    }
                } else if (choice.equals("S")) {
                    break;
                } else {
                    System.out.println("Invalid input. Enter H or S.");
                }
            }
        }
    }

    private void handleDealerTurn() {
        dealer.play();
    }
}