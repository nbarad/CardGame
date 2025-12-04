import java.util.Scanner;

public class Game {

    private Deck theDeck;
    private CardRow[] rows;
    private AcePile[] piles;
    private Scanner check;

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

        piles = new AcePile[] {new AcePile("Clubs"), new AcePile("Diamonds"), new AcePile("Hearts"), new AcePile("Spades")};
        check = new Scanner(System.in);
    }

    public void printState() {
        int line = 0;
        for (int i = 0; i < 7; i++) {
            System.out.print(line);
            System.out.println(" " + rows[i]);
            line++;
        }
        System.out.println();
        line++;
        for (int i = 0; i < 4; i++) {
            System.out.print(line);
            System.out.println(" " + piles[i]);
            line++;
        }
        System.out.println();
        System.out.println(theDeck.getCardsLeft());
    }

    public int getFirstInput() {
        int line;
        do {
            System.out.println("Move card from row: ");
            line = check.nextInt();
        }
        while ((line < 0 || line > 6) || rows[line].getRow().isEmpty());
        return line;
    }

    public int getSecondInput() {
        int line;
        do {
            System.out.println("To row: ");
            line = check.nextInt();
        }
        while (line < 0 || line > 11 || line == 7);
        return line;
    }

    public boolean checkWin() {
        for (int i = 0; i < 4; i++) {
            if (!(piles[i].checkFull())) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Game g = new Game();

        while (!g.checkWin()) {
            g.printState();
            int a = g.getFirstInput();
            int b = g.getSecondInput();
            if (b < 7) {
                if (!(g.rows[b].addCardLogic(g.rows[a].getRow().getLast()))) {
                    System.out.println("Invalid move");
                }
            }
            if (b > 7) {

            }

            for (int i = 0; i < 7; i++) {
               if (!(g.rows[i].getRow().isEmpty()) && g.rows[i].getRow().getLast().isHidden()) {
                   g.rows[i].getRow().getLast().setHidden(false);
               }
            }


        }



    }
}
