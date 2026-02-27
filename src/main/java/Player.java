import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    // instance variables
    private ArrayList<Card> stock;
    private ArrayList<Card> waste;
    private int points;
    private Card currentCard;
    private GameViewer playerSpace;

    // constructor
    public Player(ArrayList<Card> stock, GameViewer playerSpace) {
        points = 0;
        this.stock = stock;
        waste = new ArrayList<Card>();
        this.playerSpace = playerSpace;
    }
    // getters and setters

    public ArrayList<Card> getWaste() {
        return waste;
    }

    public ArrayList<Card> getStock() {
        return stock;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    // draw function that draws from deck and increments index, resets index if it is too high
    public void draw() {
        if (stock.isEmpty()) {
            stock.addAll(waste);
            waste.clear();
            currentCard = null;
        }

        if (!(stock.isEmpty())) {
            Card mover = stock.removeLast();
            waste.add(mover);
            currentCard = mover;
        } else {

        }
    }

    public void postPlay() {
        if (!waste.isEmpty()) {
            waste.removeLast();
            if (!waste.isEmpty()) {
                currentCard = waste.getLast();
            }
        } else {
            currentCard = null;
        }
    }

    public void graphicsDraw(Graphics g) {
        if (currentCard != null) {
            currentCard.draw(g, 1000, 750);
        }
        if (!this.stock.isEmpty()) {
            g.drawImage(new ImageIcon("src/main/resources/back.png").getImage(), 1250, 750, 150, 210, playerSpace);
        }

        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.setColor(Color.black);
        g.drawString("Number: -1", 1025, 700);
        if (!(stock == null)) {
            g.drawString("Stock cards left: " + stock.size(), 1260, 700);
        }
        else {
            g.drawString("Stock cards left: 0", 1260, 700);
        }

        if (!(waste == null)) {
            g.drawString("Waste cards left: " + waste.size(), 1000, 720);
        }
        else {
            g.drawString("Waste cards left: 0", 1000, 720);
        }
    }
}