import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardRow {
    // Instance variables
    private ArrayList<Card> row;
    private GameViewer CardRowSpace;
    // Simple constructor
    public CardRow(int number, GameViewer g) {
        row = new ArrayList<>();
        CardRowSpace = g;
    }
    // Returns all unhidden cards in the row
    public ArrayList<Card> getUnHidden() {
        ArrayList<Card> list = new ArrayList<Card>();
        for (Card c : row) {
            if (!(c.isHidden())) {
                list.add(c);
            }
        }
        return list;
    }
    // Add a card without logic for initialization
    public void addCard(Card card) {
        row.add(card);
    }
    // Adds card(s) with logic to make sure its valid. returns whether successful
    public boolean addCardLogic(ArrayList<Card> cards) {
        // If row is empty, only kings allowed
        if (row.isEmpty()) {
            if (cards.getFirst().getValue() != 13) {
                return false;
            }
            else {
                row.addAll(cards);
                return true;
            }
        }
        // If row has cards in it, make sure you are allowed to place cards in
        else {
            if (cards.getFirst().getValue() + 1 != row.getLast().getValue()) {
                return false;
            }
            else if (row.getLast().getRed() != cards.getFirst().getRed()){
                row.addAll(cards);
                return true;
            }
        }
        return false;
    }

    // Getter
    public ArrayList<Card> getRow() {
        return row;
    }
    // The toString
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Card card : row) {
            str.append(card);
            str.append("  ");
        }


        return str.toString();
    }
    // Draw function
    public void draw(Graphics g, int x) {
        // E rase itself
        g.setColor(Color.white);
        g.fillRect(x, 0, 150, 750);
        // Draw each card within it
        for (Card c : row) {
            c.draw(g, x, CardRowSpace.yLevels[row.indexOf(c)]);
        }
        // Writes its own number
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.setColor(Color.black);
        g.drawString("Number " + (x-165)/200, x + 25, 50);
    }
}
