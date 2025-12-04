import java.util.ArrayList;

public class AcePile {

    private ArrayList<Card> pile;
    private String suit;

    public AcePile(String suit) {
        pile = new ArrayList<Card>();
        this.suit = suit;
    }

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

    public boolean checkFull() {
        if (pile.size() == 13) {
            return true;
        }
        return false;
    }

    public String getSuit() {
        return suit;
    }

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
