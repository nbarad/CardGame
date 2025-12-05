import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Deck theDeck;
    private CardRow[] rows;
    private AcePile[] piles;
    private Player you;
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
            for(int j = 0; j < i + 1; j++) {
                rows[i].addCard(theDeck.deal());
            }
        }
        for (int i = 0; i < 7; i++) {
            for(int j = 0; j < i; j++) {
                rows[i].getRow().get(j).setHidden(true);
            }
        }

        piles = new AcePile[] {new AcePile("Clubs"), new AcePile("Diamonds"), new AcePile("Hearts"), new AcePile("Spades")};
        check = new Scanner(System.in);
        you = new Player("", new ArrayList<Card>(theDeck.getDeck().subList(0,theDeck.getCardsLeft() - 1)));
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
            System.out.println("(say -1 for hand) Move card from row: ");
            line = check.nextInt();
        }
        while ((line < -1 || line > 6) || (line != -1 && rows[line].getRow().isEmpty()));

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
        boolean draw;
        while (!g.checkWin()) {
            g.printState();
            String answer;
            do {
                do {
                    System.out.println("Draw? (y/n): ");
                    answer = g.check.nextLine();
                }
                while (!(answer.equals("y") || answer.equals("n")));
                draw = answer.equals("y");

                if (draw) {
                    g.you.draw();
                    System.out.println(g.you.getCurrentCard() + " " + g.you.getHand().size() + " cards left");
                }
            }
            while (draw);



            Card mover;
            int a = g.getFirstInput();

            if (a == -1) {
                mover = g.you.getCurrentCard();
            }
            else {
                mover = g.rows[a].getRow().getLast();
            }
            g.theDeck.shuffle();
            int b = g.getSecondInput();
            if (b < 7 ) {
                if (!(g.rows[b].addCardLogic(mover))) {
                    System.out.println("Invalid move");
                }
                else if (a != -1) {
                    g.rows[a].getRow().removeLast();
                }
            }
            else if (a != -1) {
                if (!(g.piles[b - 8].addCard(mover))) {
                    System.out.println("Invalid move");
                } else {
                    g.rows[a].getRow().removeLast();
                }
            }

            for (int i = 0; i < 7; i++) {
               if (!(g.rows[i].getRow().isEmpty()) && g.rows[i].getRow().getLast().isHidden()) {
                   g.rows[i].getRow().getLast().setHidden(false);
               }
            }


        }



    }
}
