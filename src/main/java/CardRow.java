import java.util.ArrayList;

public class CardRow {

    private ArrayList<Card> row;

    public CardRow() {
        row = new ArrayList<>();
    }

    public void addCard(Card card) {
        row.add(card);
    }

    public boolean addCardLogic(Card card) {
        if (card.getRed()) {

            if (row.isEmpty() || !(row.getLast().getRed())) {
                if (card.getValue() == 12 && (!row.isEmpty())){
                    return false;
                }
                row.add(card);
                return true;
            }
        }
        if (!(card.getRed())) {
            if (row.getLast().getRed() ) {
                if (card.getValue() == 12 && (!row.isEmpty())){
                    return false;
                }
                row.add(card);
                return true;
            }
        }
        return false;
    }


    public ArrayList<Card> getRow() {
        return row;
    }

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
