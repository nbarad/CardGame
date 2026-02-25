import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    // instance variables
    private String name;
    private ArrayList<Card> hand;
    private int points;
    private int index;
    private Card currentCard;
    private GameViewer playerSpace;
    // constructors
    public Player(String name) {
        this.name = name;
        points = 0;
        hand = new ArrayList<Card>();
    }

    public Player(String name, ArrayList<Card> hand, GameViewer playerSpace) {
        this.name = name;
        points = 0;
        this.hand = hand;
        index = -1;
    }
    // getters and setters
    public int getPoints() {
        return points;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void updateCurrentCard() {
        currentCard = hand.get(index);
    }

    public int getIndex() {
        return index;
    }

    public void stepIndex() {
        index--;
    }

    public void addPoints(int addition) {
        points+= addition;
    }
    public void addCard(Card addition) {
        hand.add(addition);
    }
    // draw function that draws from deck and increments index, resets index if it is too high
    public void draw() {
        index++;
        if (index >= hand.size()) {
            index = 0;
        }
        currentCard = hand.get(index);
    }

    public void graphicsDraw(Graphics g) {
        if (currentCard != null) {
            currentCard.draw(g, 1000, 750);
        }
        if ((this.getHand().size() - this.getIndex()) > 0) {
            g.drawImage(new ImageIcon("src/main/resources/back.png").getImage(), 1250, 750, 150, 210, playerSpace);
        }
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.setColor(Color.black);
        g.drawString("Cards left in draw pile: " + (this.hand.size() - this.index - 1), 1200, 700);
    }

    // toString
    @Override
    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}