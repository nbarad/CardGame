import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    // instance variables
    private Deck theDeck;
    private CardRow[] rows;
    private AcePile[] piles;
    private Player you;
    private Scanner check;

    // constructor
    public Game() {
        // make a new deck with standard qualities and shuffle it
        theDeck = new Deck(new String[] {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "Ten", "Jack", "Queen", "King"},
                          new String[] {"Clubs", "Diamonds", "Hearts", "Spades"},
                          new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}
                         );
        theDeck.shuffle();
        // initialize rows array of cardRows
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
        //new acePiles and input
        piles = new AcePile[] {new AcePile("Clubs"), new AcePile("Diamonds"), new AcePile("Hearts"), new AcePile("Spades")};
        check = new Scanner(System.in);
        // new player with no name and a hand of all of the unused cards from deck
        you = new Player("", new ArrayList<Card>(theDeck.getDeck().subList(0,theDeck.getCardsLeft())));
    }
    // this function prints all relevant information for the player onto the console
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
        if (!(you.getCurrentCard() == null)){
            System.out.println("Hand: " + you.getCurrentCard());
        }
        System.out.println((you.getHand().size() - you.getIndex()) + " cards left");
    }
    // print instructions for game
    public void printInstructions() {
        System.out.println("Welcome to Solitaire, the classic 1 player card game!" +
                "\nThe objective is to rearrange all of the cards so that in the end you can " +
                "\nfill the ace piles on rows 8-11 with their respective suit from Ace to King" +
                "\nYou first choose if you want to draw, which will give you a card" +
                "\nThen, you choose where to take a card and where to place it" +
                "\nYou can only place kings on empty spaces " +
                "\nGood luck!");
    }
    // get input for where the user wants to move a card from
    public int getFirstInput() {
        int line;
        do {
            System.out.println("(say -1 for hand) Move card from row: ");
            line = check.nextInt();
        }
        while ((line < -1 || line > 6) || (line != -1 && rows[line].getRow().isEmpty()));

        return line;
    }
    // get input on where the user wants to move the card
    public int getSecondInput() {
        int line;
        do {
            System.out.println("To row: ");
            line = check.nextInt();
        }
        while (line < 0 || line > 11 || line == 7);
        return line;
    }
    // win checker, logic for adding cards makes it so this is very simple
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
        int counter = 0;
        while (!g.checkWin()) {
            String answer;
            do {
                do {

                    if (counter == 0) {
                        g.printInstructions();
                        counter++;
                    }
                    else {
                        for (int i = 0; i < 30; i++) {
                            System.out.println("\n");
                        }
                    }
                    g.printState();
                    System.out.println("Draw? (y/n): ");
                    answer = g.check.nextLine();
                }
                while (!(answer.equals("y") || answer.equals("n")));
                draw = answer.equals("y");

                if (draw) {
                    g.you.draw();
                    System.out.println(g.you.getCurrentCard() + " " + (g.you.getHand().size() - g.you.getIndex()) + " cards left");
                    //System.out.println(g.you.getIndex());

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
                else {
                    g.you.getHand().remove(g.you.getIndex());
                }
            }
            else {
                if (!(g.piles[b - 8].addCard(mover))) {
                    System.out.println("Invalid move");
                } else if (a != -1) {
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
