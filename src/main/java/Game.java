public class Game {

    private Deck theDeck;
    private CardRow[] rows;

    public Game() {
        theDeck = new Deck(new String[] {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "Ten", "Jack", "Queen", "King"},
                          new String[] {"Clubs", "Diamonds", "Hearts", "Spades"},
                          new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}
                         );
        theDeck.shuffle();
        rows = new CardRow[7];
        for (int i = 0; i < 7; i++) {
            rows[i] = new CardRow();
        }
        for (int i = 0; i < 7; i++) {
            for(int j = 0; j < i; j++) {
                rows[i].addCard(theDeck.deal());
            }
        }
        for (int i = 0; i < 7; i++) {
            for(int j = 0; j < i - 1; j++) {
                rows[i].getRow().get(j).setHidden(true);
            }
        }
        theDeck.shuffle();
    }

    public void printState() {
        for (int i = 0; i < 7; i++) {
            System.out.print(i + 1);
            System.out.println(" " + rows[i]);
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.printState();


    }
}
