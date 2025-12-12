import java.util.ArrayList;

public class CardRow {
    // instance variables
    private ArrayList<Card> row;
    // simple constructor
    public CardRow() {
        row = new ArrayList<>();
    }
    // add a card without logic for initialization
    public void addCard(Card card) {
        row.add(card);
    }
    // adds a card with logic to make sure its valid. returns whether or not successful
    public boolean addCardLogic(Card card) {
        if (card.getRed()) {

            if (row.isEmpty() || !row.getLast().getRed()) {
                if (card.getValue() == 13 && !row.isEmpty()){
                    return false;
                }
                row.add(card);
                return true;
            }
        }
        if (!card.getRed()) {
            if (row.getLast().getRed() ) {
                if (card.getValue() == 13 && !row.isEmpty()){
                    return false;
                }
                row.add(card);
                return true;
            }
        }
        return false;
    }

    // getter
    public ArrayList<Card> getRow() {
        return row;
    }
    // toString
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Card card : row) {
            str.append(card);
            str.append("  ");
        }


        return str.toString();
    }
}
