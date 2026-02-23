import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Deck {
    // instance variables
    private ArrayList<Card> deck;
    private int cardsLeft;
    private GameViewer deckSpace;
    // constructor, makes all necesary cards
    public Deck(String[] rank, String[] suits, int[] values, GameViewer deckSpace) {
        cardsLeft = rank.length * suits.length;
        deck = new ArrayList<Card>();
        this.deckSpace = deckSpace;
        int counter = 1;
        for (int v : values) {
            for (String s : suits) {
                deck.add(new Card(rank[v - 1], s, v, new ImageIcon("src/main/resources/" + counter + ".png").getImage(), deckSpace));
                counter++;
            }
        }


    }

    public Boolean hasNoCards() {
        return cardsLeft == 0;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    // getters
    public Card getCard(int position) {
        return deck.get(position);
    }


    public ArrayList<Card> getDeck() {
        return deck;
    }
    // deal method, deals the last valid card then makes it invalid to deal again
    public Card deal() {
        if (cardsLeft == 0) {
            return null;
        }

        cardsLeft--;
        return deck.get(cardsLeft);

    }
    // shuffles deck
    public void shuffle() {
        cardsLeft = deck.size();

        for (int i = cardsLeft - 1; i >= 1; i--) {
            int index = (int) (Math.random() * i);
            deck.add(index, deck.remove(i));
            deck.add(i, deck.remove(index + 1));
        }
    }


}
