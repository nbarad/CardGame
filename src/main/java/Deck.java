import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> deck;
    private int cardsLeft;

    public Deck(String[] rank, String[] suits, int[] values) {
        cardsLeft = rank.length * suits.length;
        deck = new ArrayList<Card>();

        for (String s : suits) {
            for (int v : values) {
                deck.add(new Card(rank[v - 1], s, v));
            }
        }
    }
    public Boolean hasNoCards() {
        return cardsLeft == 0;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card getCard(int position) {
        return deck.get(position);
    }


    public ArrayList<Card> getDeck() {
        return deck;
    }

    public Card deal() {
        if (cardsLeft == 0) {
            return null;
        }

        cardsLeft--;
        return deck.get(cardsLeft);

    }
    public void shuffle() {
        cardsLeft = deck.size();

        for (int i = cardsLeft - 1; i >= 1; i--) {
            int index = (int) (Math.random() * i);
            deck.add(index, deck.remove(i));
            deck.add(i, deck.remove(index + 1));
        }
    }


}
