import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    // instance variables
    private String name;
    private ArrayList<Card> hand;
    private int points;
    private int currentIndex;
    private int nextIndex;
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
        currentIndex = -1;
        nextIndex = 0;
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

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void stepIndex(boolean up) {
        if (up) {
            currentIndex++;
            nextIndex++;
        }
        else {
            currentIndex--;
            nextIndex--;
        }

    }

    public void addPoints(int addition) {
        points+= addition;
    }
    public void addCard(Card addition) {
        hand.add(addition);
    }
    // draw function that draws from deck and increments index, resets index if it is too high
    public void draw() {
        stepIndex(true);
        if (nextIndex >= hand.size()) {
            nextIndex = 0;
        }
        if (currentIndex >= hand.size()) {
            currentIndex = 0;
        }
        currentCard = hand.get(currentIndex);
    }

    public void postPlay() {
        if(index > 0) {
            index--;
            currentCard = hand.get(index);
        }
        else {
            hand.remove(index);
        }
    }

    public void graphicsDraw(Graphics g) {
        if (currentCard != null) {
            currentCard.draw(g, 1000, 750);
        }
        if ((this.getHand().size() - this.getCurrentIndex()) > 0) {
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