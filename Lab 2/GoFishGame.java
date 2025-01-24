import java.util.*;

public class GoFishGame {
    private Deck deck;
    private Hand playerHand;
    private Hand opponentHand;
    private ArrayList<Card> playerBooks;
    private ArrayList<Card> opponentBooks;
    private Iterator<Card> playerIterator;
    private Iterator<Card> opponentIterator;
    private boolean playerTurn;
    private int lastOpponentRank;

    // Constructor, initializes the game. Populates the deck, shuffles it, and deals hands to the players.
    public GoFishGame() {
        deck = new Deck();
        deck.shuffle();

        playerHand = new Hand();
        opponentHand = new Hand();

        for (int i = 0; i < 7; i++) {
            playerHand.addCard(deck.getCards().remove(0));
            opponentHand.addCard(deck.getCards().remove(0));
        }

        playerBooks = new ArrayList<Card>();
        opponentBooks = new ArrayList<Card>();
        playerTurn = true;
        lastOpponentRank = -1;
    }

    // Method to play the game
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        announceGameStart();
        
        // Pause for a second to simulate thinking
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Main game loop
        while (playerBooks.size() < 7 && opponentBooks.size() < 7) {
            if (playerTurn) {
                System.out.println("Your turn!");
                
                // Pause for a second to simulate thinking
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.print("Your hand: ");
                printHand(playerHand);
                System.out.println();
                System.out.print("Your books: ");
                displayBooks(playerBooks);
                System.out.print("Enter the rank you want to ask for (2-14, where 11=J, 12=Q, 13=K, 14=A): ");
                int playerRank = scanner.nextInt();
                playerTurn(playerRank);
                
                // Pause for a second to simulate thinking
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                if (deck.size() == 0) {
                    System.out.println("Deck is empty! Game over!");
                    break;
                }
                opponentTurn();

                // Pause for a second to simulate thinking
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        declareWinner();
        scanner.close();
    }

    // Method for player's turn
    private void playerTurn(int playerRank) {
        checkForBooks(playerHand, playerBooks);
        boolean opponentHasCard = false;
        // Iterate through opponent's hand to check for card
        opponentIterator = opponentHand.getCards().iterator();
        while (opponentIterator.hasNext()) {
            Card card = opponentIterator.next();
            if (card.getRank() == playerRank) {
                opponentHasCard = true;
                opponentIterator.remove();
                playerHand.addCard(card);
            }
        }

        // Pause for a second to simulate thinking
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Check opponent's hand for card. If not found, player draws a card from the deck
        if (opponentHasCard) {
            System.out.println("Opponent has the card(s) you asked for!");
        } else {
            System.out.println("Go fish!");
            if (!deck.getCards().isEmpty()) {
                playerHand.addCard(deck.getCards().remove(0));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("You drew a " + playerHand.getCards().get(playerHand.getCards().size() - 1).getRank());
            }
            playerTurn = false;
        }

        checkForBooks(playerHand, playerBooks);
    }

    // Method for opponent's turn
    private void opponentTurn() {
        checkForBooks(opponentHand, opponentBooks);
        System.out.println("Opponent's turn!");
        System.out.print("Opponent's books: ");
        displayBooks(opponentBooks);
        boolean playerHasCard = false;
        
        
        // Generate a random card, of which the opponent possesses to ask the player for
        Random rand = new Random();
        int opponentRank;
        if (opponentHand.getCards().size() != 0 && playerHand.getCards().size() != 0) {
            do {
                int randomIndex = rand.nextInt(opponentHand.getCards().size());
                opponentRank = opponentHand.getCards().get(randomIndex).getRank();
            } while (opponentRank == lastOpponentRank);
            lastOpponentRank = opponentRank;
            System.out.println("Opponent asks for a: " + opponentRank);

            playerIterator = playerHand.getCards().iterator();
            while (playerIterator.hasNext()) {
                Card card = playerIterator.next();
                if (card.getRank() == opponentRank) {
                    playerHasCard = true;
                    playerIterator.remove();
                    opponentHand.addCard(card);
                }
            }
        } else {
            System.out.println("Opponent's hand is empty! Opponent draws a card from the deck.");
        }

        // Pause for a second to simulate thinking
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Check player's hand for card. If not found, opponent draws a card from the deck
        if (playerHasCard) {
            System.out.println("You had the card(s) your opponent asked for!");
        } else {
            System.out.println("Opponent Goes Fish!");
            if (!deck.getCards().isEmpty()) {
                opponentHand.addCard(deck.getCards().remove(0));
            }
            playerTurn = true;
        }
        checkForBooks(opponentHand, opponentBooks);
    }

    // Method to check for books in a player's hand
    private void checkForBooks(Hand hand, ArrayList<Card> books) {
        for (int i = 2; i <= 14; i++) {
            int count = 0;
            for (Card card : hand.getCards()) {
                if (card.getRank() == i) {
                    count++;
                }
            }
            if (count == 4) {
                Iterator<Card> iterator = hand.getCards().iterator();
                while (iterator.hasNext()) {
                    Card card = iterator.next();
                    if (card.getRank() == i) {
                        iterator.remove();
                    }
                }
                books.add(new Card(i));
            }
        }
    }

    // Method to print the player's hand
    public void printHand(Hand hand) {
        hand.print();
    }

    // Method to announce the start of the game
    public void announceGameStart() {
        System.out.println("Welcome to Go Fish! The goal of the game is to collect more \"books\" of cards than your opponent. ");
        System.out.println("A book is a set of four cards with the same rank. Each turn you may ask your opponent for a card/cards of a specific rank. ");
        System.out.println("If your opponent has that card or cards, they must give them to you, and you get another turn. ");
        System.out.println("If your opponent does not have the card or cards, they say \"Go fish!\" and you must draw a card from the deck. ");
        System.out.print("To start, you've been dealt the hand: ");
        printHand(playerHand);
        System.out.println();
    }

    // Method to display a player's books
    public void displayBooks(ArrayList<Card> books) {
        for (Card card : books) {
            if(card.getRank() == 11) {
                System.out.print("J, ");
            } else if(card.getRank() == 12) {
                System.out.print("Q, ");
            } else if(card.getRank() == 13) {
                System.out.print("K, ");
            } else if(card.getRank() == 14) {
                System.out.print("A, ");
            } else {
                System.out.print(card.getRank() + ", ");
            }
        }
        System.out.println();
    }

    // Method to declare the winner of the game
    public void declareWinner() {
        System.out.print("Your books: ");
        displayBooks(playerBooks);
        System.out.print("Opponent's books: ");
        displayBooks(opponentBooks);
        if (playerBooks.size() > opponentBooks.size()) {
            System.out.println("Congratulations! You win!");
        } else if (playerBooks.size() < opponentBooks.size()) {
            System.out.println("You lose! Better luck next time!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}