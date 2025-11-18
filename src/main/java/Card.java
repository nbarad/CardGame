public class Card {


    private int rank;
    private String suit;
    private int value;


    public Card(int rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
