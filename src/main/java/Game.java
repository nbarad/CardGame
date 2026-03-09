// Solitaire by Nate Barad - 2/26/26
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {

    // Instance variables
    private Deck theDeck;
    private ArrayList<CardRow> rows;
    private ArrayList<AcePile> piles;
    private Player you;
    private GameViewer window;
    private Scanner check;


    // Constructor
    public Game() {
        // New GameViewer object, passing in this
        window = new GameViewer(this);
        // Make a new deck with standard qualities and shuffle it
        theDeck = new Deck(new String[] {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "Ten", "Jack", "Queen", "King"},
                          new String[] {"Spades", "Hearts", "Diamonds", "Clubs"},
                          new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13},
                          window);
        theDeck.shuffle();
        // Initialize rows array of cardRows
        // Initialize each CardRow
        rows = new ArrayList<CardRow>();
        for (int i = 0; i < 7; i++) {
            rows.add(new CardRow(window));
        }
        // Add Cards
        for (int i = 0; i < 7; i++) {
            for(int j = 0; j < i + 1; j++) {
                rows.get(i).addCard(theDeck.deal());
            }
        }
        // Hide necessary cards
        for (int i = 0; i < 7; i++) {
            for(int j = 0; j < i; j++) {
                rows.get(i).getRow().get(j).setHidden(true);
            }
        }
        // New acePiles and input
        piles = new ArrayList<AcePile>();
        piles.add(new AcePile("Clubs", window));
        piles.add(new AcePile("Diamonds", window));
        piles.add(new AcePile("Hearts", window));
        piles.add(new AcePile("Spades", window));
        check = new Scanner(System.in);
        // New player with no name and a hand of all the unused cards from deck
        you = new Player(new ArrayList<Card>(theDeck.getDeck().subList(0,theDeck.getCardsLeft())), window);
    }

    // This function prints all relevant information for the player onto the console
    public void printState() {
        int line = 0;

        // Print CardRows
        for (int i = 0; i < 7; i++) {
            System.out.print(line);
            System.out.println(" " + rows.get(i));
            line++;
        }
        System.out.println();
        line++;

        // Print AcePiles
        for (int i = 0; i < 4; i++) {
            System.out.print(line);
            System.out.println(" " + piles.get(i));
            line++;
        }

        // Print draw information
        System.out.println();
        if (!(you.getCurrentCard() == null)){
            System.out.println("Hand: " + you.getCurrentCard());
        }
        // Whenever console gets new information, so should the window
        window.repaint();
    }
    // Print instructions for game
    public void printInstructions() {
        System.out.println("Welcome to Solitaire, the classic 1 player card game!" +
                "\nThe objective is to rearrange all of the cards so that in the end you can " +
                "\nfill the ace piles on rows 8-11 with their respective suit from Ace to King" +
                "\nYou first choose if you want to draw, which will give you a card" +
                "\nThen, you choose where to take a card and where to place it" +
                "\nYou can only place kings on empty spaces " +
                "\nGood luck!");
    }
    // Get input for where the user wants to move a card from
    public int getFirstInput() {
        int line;
        do {
            System.out.println("(say -1 for hand) Move card from number: ");
            line = check.nextInt();
        }// can only move to -1 through 6
        while ((line < -1 || line > 6) || (line != -1 && rows.get(line).getRow().isEmpty()));

        return line;
    }
    // Get input on where the user wants to move the card
    public int getSecondInput() {
        int line;
        do {
            System.out.println("To number: ");
            line = check.nextInt();
        }// Can only move through 0 - 11 excluding 7
        while (line < 0 || line > 11 || line == 7);
        return line;
    }
    // Win checker, logic for adding cards makes it so this is very simple
    public boolean checkWin() {
        for (int i = 0; i < 4; i++) {
            if (!(piles.get(i).checkFull())) {
                return false;
            }
        }
        return true;
    }

    public void graphicsDraw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 2000, 1000);
        if (rows != null) {
            for (CardRow r : rows) {
                r.draw(g, 165 + rows.indexOf(r) * 200);
            }
        }
        if (piles != null) {

            for (AcePile p : piles) {
                p.draw(g, 165 + piles.indexOf(p) * 200);
            }
        }
        if (you != null) {
            you.graphicsDraw(g);
        }
    }

    public void draw() {
        String answer;
        boolean draw;
        do {
            // Input for draw or not
            do {
                // Make space for new game state printout
                for (int i = 0; i < 30; i++) {
                    System.out.println("\n");
                }
                printState();
                System.out.println("Draw? (y/n): ");
                answer = check.nextLine();
            }
            while (!(answer.equals("y") || answer.equals("n")));
            draw = answer.equals("y");

            if (draw) {

                you.draw();
                // Print relevant information
                System.out.println(you.getCurrentCard() + " " + (you.getStock().size() + you.getWaste().size()) + " cards left");
            }
        }
        while (draw);
    }
    public static void main(String[] args) {
        // Game object
        Game g = new Game();
        // Print instructions on first go
        g.printInstructions();


        // Main game loop
        while (!g.checkWin()) {

            // Input and card drawing system
            g.draw();

            // Where to move from and to input
            ArrayList<Card> mover = new ArrayList<Card>();
            int a = g.getFirstInput();
            int b = g.getSecondInput();
            if (b < 7 && a != -1) {
                // If moving to and from tableaus, you can take multiple cards
                mover = g.rows.get(a).getUnHidden();
            }
            else if (a == -1) {
                // If moving from hand, get the currentCard
                mover.add(g.you.getCurrentCard());
            }
            else if (b >= 8 && b <= 11) {
                // If moving to acePile, only take bottom card
                mover.add(g.rows.get(a).getRow().getLast());
            }

            // Move card to rows
            if (b < 7 ) {
                // The addCardLogic method returns whether it was successful, so it can be in an if statement
                if (!(g.rows.get(b).addCardLogic(mover))) {
                    // Print invalid if the move was invalid, attempts the move in the process
                    System.out.println("Invalid move");
                }
                else if (a != -1) {
                    // If successful, remove every unhidden card from the row you took from
                    for (int i = 0; i < mover.size(); i++) {
                        g.rows.get(a).getRow().removeLast();
                    }
                }// If successful and card taken from hand, clean up
                else {
                    g.you.postPlay();
                }
            }
            // Move card to ace piles
            else {
                // The addCard method returns whether it was successful, so it can be in an if statement
                if (!(g.piles.get(b - 8).addCard(mover))) {
                    System.out.println("Invalid move");

                } else if (a != -1) {
                    // If successful and from tableau, remove the last card in it
                    g.rows.get(a).getRow().removeLast();
                }
                else {
                    // If successful and card taken from hand, clean up
                    g.you.postPlay();
                }
            }
            // if any card is at the end of its row and is hidden, it should be revealed
            for (int i = 0; i < 7; i++) {
               if (!(g.rows.get(i).getRow().isEmpty()) && g.rows.get(i).getRow().getLast().isHidden()) {
                   g.rows.get(i).getRow().getLast().setHidden(false);
               }
            }
        }
    }
}
