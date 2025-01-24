import java.util.ArrayList;

public class Deck {
    
    private ArrayList<Card> cards;
    
    // Constructor to populate deck with 52 cards
    public Deck() {
        cards = new ArrayList<Card>();
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j <= 14; j++) {
                cards.add(new Card(j));
            }
        }
    }
    
    // Getter method for cards
    public ArrayList<Card> getCards() {
        return cards;
    }
    
    // Method to shuffle the deck
    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            int randomIndex = (int) (Math.random() * cards.size());
            Card temp = cards.get(i);
            cards.set(i, cards.get(randomIndex));
            cards.set(randomIndex, temp);
        }
    }
    
    // Method to print the deck
    public void print() {
        for (Card card : cards) {
            System.out.print(card.getRank() + ", ");
        }
    }
}
