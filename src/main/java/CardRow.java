import java.util.ArrayList;

public class CardRow {

    private ArrayList<Card> row;

    public CardRow() {
        row = new ArrayList<>();
    }

    public void addCard(Card card)
    {
        row.add(card);
    }


}
