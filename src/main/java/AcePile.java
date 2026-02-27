import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AcePile {
    // Instance variables
    private ArrayList<Card> pile;
    private String suit;
    private GameViewer AcePileSpace;
    // Constructor
    public AcePile(String suit, GameViewer A) {
        pile = new ArrayList<Card>();
        this.suit = suit;
        AcePileSpace = A;
    }
    // Adds a card with logic to make sure its valid. returns whether successful
    public boolean addCard(ArrayList<Card> cards) {
        // Check if suits match first, then if number is 1
        if (cards.getFirst().getSuit().equals(suit)) {
            if (cards.getFirst().getValue() == 1) {
                // If so, add the card
                pile.addAll(cards);
                return true;
            }
            // Otherwise if the number is 1 more than the current number, also add it
            else if(!pile.isEmpty() && cards.getFirst().getValue() == pile.getLast().getValue() + 1) {
                pile.addAll(cards);
                return true;
            }
        }
        return false;
    }
    // Checks if it has right number of cards to be full
    public boolean checkFull() {
        return pile.size() == 13;
    }
    // Getter
    public String getSuit() {
        return suit;
    }
    // The toString
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
    // Draw function, takes in x that the cards should draw themselves at
    public void draw(Graphics g, int x) {
        // Draw back of card if pile is empty
        if (pile.isEmpty()) {
            g.drawImage(new ImageIcon("src/main/resources/back.png").getImage(), x, AcePileSpace.bottom, 150, 210, AcePileSpace);
        }
        // otherwise draw the top card
        else {
            pile.getLast().draw(g, x, AcePileSpace.bottom);
        }
        // Draw the number for user convenience
        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.setColor(Color.black);
        g.drawString(suit + " Pile, number " + (8 + (x-200)/165) , x - 10, 700);
    }
}
