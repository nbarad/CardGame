import java.util.ArrayList;

public class CardRow {

    private ArrayList<Card> row;

    public CardRow() {
        row = new ArrayList<>();
    }

    public void addCard(Card card) {
        row.add(card);
    }

    public void addCardLogic(Card card) {

    }


    public ArrayList<Card> getRow() {
        return row;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < row.size(); i++) {
            str.append(row.get(i));
            str.append("  ");
        }


        return str.toString();
    }
}
