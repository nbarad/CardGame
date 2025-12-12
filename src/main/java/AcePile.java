import java.util.ArrayList;

public class AcePile {
    // instance variables
    private ArrayList<Card> pile;
    private String suit;
    // constructor
    public AcePile(String suit) {
        pile = new ArrayList<Card>();
        this.suit = suit;
    }
    // adds a card with logic to make sure its valid. returns whether or not successful
    public boolean addCard(Card card) {
        if (card.getSuit().equals(suit)) {
            if (card.getValue() == 1) {
                pile.add(card);
                return true;
            }
            else if(!pile.isEmpty() && card.getValue() == pile.getLast().getValue() + 1) {
                pile.add(card);
                return true;
            }
        }
        return false;
    }
    // checks if it has right number of cards to be full
    public boolean checkFull() {
        if (pile.size() == 13) {
            return true;
        }
        return false;
    }
    // getter
    public String getSuit() {
        return suit;
    }
    // toString
    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append(suit.charAt(0) + " ");
        for (Card card : pile) {
            str.append(card);
            str.append(" ");
        }
        return String.valueOf(str);
    }
}
