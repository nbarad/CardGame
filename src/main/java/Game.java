public class Game {

    private Deck theDeck;
    private CardRow[] rows = new CardRow[7];

    public Game() {
        for (int i = 0; i < 7; i++) {
            for(int j = 0; j < i; j++) {

                rows[i].addCard(theDeck.deal());
            }

        }

    }

    public static void main(String[] args) {



    }
}
