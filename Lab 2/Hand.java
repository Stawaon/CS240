import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void print() {
        for (Card card : hand) {
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
    }

    public void removeCard(Card card) {
        hand.remove(card);
    }

    public ArrayList<Card> getCards() {
        return hand;
    }
}
