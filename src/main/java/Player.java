import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    // Instance variables
    private ArrayList<Card> stock;
    private ArrayList<Card> waste;
    private Card currentCard;
    private GameViewer playerSpace;

    // Constructor
    public Player(ArrayList<Card> stock, GameViewer playerSpace) {
        this.stock = stock;
        waste = new ArrayList<Card>();
        this.playerSpace = playerSpace;
    }
    // Getters

    public ArrayList<Card> getWaste() {
        return waste;
    }

    public ArrayList<Card> getStock() {
        return stock;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    // Draw function that draws from deck and increments index, resets index if it is too high
    public void draw() {
        // If stock is empty, recycle the waste back into it, clear waste, and clear currentCard
        if (stock.isEmpty()) {
            stock.addAll(waste);
            waste.clear();
            currentCard = null;
        }
        // Acquire currentCard by taking it from stock and moving to waste and currentCard
        if (!(stock.isEmpty())) {
            Card mover = stock.removeLast();
            waste.add(mover);
            currentCard = mover;
        }
    }
    // After a valid move from hand is played, clean up
    public void postPlay() {
        // If waste isn't empty, then remove the last, and then

        if (!waste.isEmpty()) {
            waste.removeLast();
            // If there's still something in waste then set currentCard to it
            if (!waste.isEmpty()) {
                currentCard = waste.getLast();
            }
        // If waste is empty, then ser currentCard to null
        } else {
            currentCard = null;
        }
    }
    // Draw function
    public void graphicsDraw(Graphics g) {
        // Draw currentCard if it exists
        if (currentCard != null) {
            currentCard.draw(g, 1000, 750);
        }
        // Draw back of card as stock pile if it isn't empty
        if (!this.stock.isEmpty()) {
            g.drawImage(new ImageIcon("src/main/resources/back.png").getImage(), 1250, 750, 150, 210, playerSpace);
        }
        // Print input number
        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.setColor(Color.black);
        g.drawString("Number: -1", 1025, 700);
        // Print number of stock cards left
        if (!(stock == null)) {
            g.drawString("Stock cards left: " + stock.size(), 1260, 700);
        }
        else {
            g.drawString("Stock cards left: 0", 1260, 700);
        }
        // Print number of waste cards left
        if (!(waste == null)) {
            g.drawString("Waste cards left: " + waste.size(), 1000, 720);
        }
        else {
            g.drawString("Waste cards left: 0", 1000, 720);
        }
    }
}