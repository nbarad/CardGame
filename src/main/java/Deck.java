import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Deck {
    // Instance variables
    private ArrayList<Card> deck;
    private int cardsLeft;
    private GameViewer deckSpace;
    // Constructor, makes all necesary cards
    public Deck(String[] rank, String[] suits, int[] values, GameViewer deckSpace) {
        cardsLeft = rank.length * suits.length;
        deck = new ArrayList<Card>();
        this.deckSpace = deckSpace;
        int counter = 1;
        // Initialize each card with their respective value, suit, rank, and image
        for (int v : values) {
            for (String s : suits) {
                deck.add(new Card(rank[v - 1], s, v, new ImageIcon("src/main/resources/" + counter + ".png").getImage(), deckSpace));
                counter++;
            }
        }
    }

    // Getters
    public int getCardsLeft() {
        return cardsLeft;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    // Deal method, deals the last valid card then makes it invalid to deal again
    public Card deal() {
        if (cardsLeft == 0) {
            return null;
        }
        cardsLeft--;
        return deck.get(cardsLeft);
    }
    // Shuffles deck
    public void shuffle() {
        cardsLeft = deck.size();
        for (int i = cardsLeft - 1; i >= 1; i--) {
            int index = (int) (Math.random() * i);
            deck.add(index, deck.remove(i));
            deck.add(i, deck.remove(index + 1));
        }
    }


}
