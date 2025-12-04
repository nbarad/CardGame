public class Card {


    private String rank;
    private String suit;
    private int value;
    private boolean hidden;
    private boolean red;


    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        hidden = false;
        red = (this.suit.equals("Hearts") || this.suit.equals("Diamonds"));
    }

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

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        if (this.hidden)
        {
            return "HH";
        }
        return "" + rank.charAt(0) + suit.charAt(0);
        //return rank + " of " + suit;
    }
}
