import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int points;

    public Player(String name) {
        this.name = name;
        points = 0;
        hand = new ArrayList<Card>();
    }

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        points = 0;
        this.hand = hand;
    }

    public int getPoints() {
        return points;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
    public void addPoints(int addition) {
        points+= addition;
    }
    public void addCard(Card addition) {
        hand.add(addition);
    }

    @Override
    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}
