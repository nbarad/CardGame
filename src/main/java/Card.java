import javax.swing.*;
import java.awt.*;

public class Card {

    // instance variables
    private String rank;
    private String suit;
    private int value;
    private Image image;
    private boolean hidden;
    private boolean red;
    private boolean dealt;
    private GameViewer cardSpace;

    // constructor
    public Card(String rank, String suit, int value, Image image, GameViewer cardSpace) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.image = image;
        this.cardSpace = cardSpace;
        hidden = false;
        red = (this.suit.equals("Hearts") || this.suit.equals("Diamonds"));
        dealt = false;
    }
    // getters and setters
    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public boolean getRed() {
        return red;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean isDealt() {
        return dealt;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setDealt(boolean dealt) {
        this.dealt = dealt;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    // toString
    @Override
    public String toString() {
        if (this.hidden)
        {
            return "HH";
        }
        return "" + rank.charAt(0) + suit.charAt(0);
        //return rank + " of " + suit;
    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(image, x, y, 100, 140, cardSpace);
    }
}
