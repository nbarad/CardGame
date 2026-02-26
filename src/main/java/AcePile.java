import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AcePile {
    // instance variables
    private ArrayList<Card> pile;
    private String suit;
    private GameViewer AcePileSpace;
    // constructor
    public AcePile(String suit, GameViewer A) {
        pile = new ArrayList<Card>();
        this.suit = suit;
        AcePileSpace = A;
    }
    // adds a card with logic to make sure its valid. returns whether successful
    public boolean addCard(ArrayList<Card> cards) {
        if (cards.getFirst().getSuit().equals(suit)) {
            if (cards.getFirst().getValue() == 1) {
                pile.addAll(cards);
                return true;
            }
            else if(!pile.isEmpty() && cards.getFirst().getValue() == pile.getLast().getValue() + 1) {
                pile.addAll(cards);
                return true;
            }
        }
        return false;
    }
    // checks if it has right number of cards to be full
    public boolean checkFull() {
        return pile.size() == 13;
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

    public void draw(Graphics g, int x) {
        if (pile.isEmpty()) {
            g.drawImage(new ImageIcon("src/main/resources/back.png").getImage(), x, AcePileSpace.bottom, 150, 210, AcePileSpace);
        }
        else {
            pile.getLast().draw(g, x, AcePileSpace.bottom);
        }

        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.setColor(Color.black);
        g.drawString(suit + " Pile, number " + (8 + (x-200)/165) , x - 10, 700);
    }
}
