import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    // Instance variables
    private final int WINDOW_WIDTH = 2400;
    private final int WINDOW_HEIGHT = 1500;
    public final int[] yLevels;
    public final  int bottom = 750;
    private Game backend;
    private final int LINEBREAK_VERTICAL_SHIFT = 25;

    // Constructor, takes in backend object to get information
    public GameViewer(Game backend) {
        this.backend = backend;

        // Initialize y constants for drawing
        yLevels = new int[19];
        for (int i = 0; i < 19; i++) {
            yLevels[i] = 60 + (i * 30);
        }


        // 4 necessary operations to make window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Solitaire");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }
    public void preGameDisplay(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.setColor(Color.BLUE);
        int xInstructions = 1700;
        int yInstructions = 750;
        g.drawString("Welcome to Solitaire, the classic 1 player card game!", xInstructions, yInstructions);
        g.drawString("The objective is to rearrange all of the cards so that in the end you can fill the ace piles " +
                "on rows 8-11 with their respective suit from Ace to King ", xInstructions, yInstructions + LINEBREAK_VERTICAL_SHIFT);
        g.drawString("You first choose if you want to draw, which will give you a card. Then, you choose where to " +
                "take a card and where to place it.",xInstructions, yInstructions + (LINEBREAK_VERTICAL_SHIFT * 2));
        g.drawString("You can only place kings on empty spaces ", xInstructions, yInstructions + (LINEBREAK_VERTICAL_SHIFT * 3));
        g.drawString("Good Luck!", xInstructions, yInstructions + (LINEBREAK_VERTICAL_SHIFT * 4));
        g.drawString("Click enter to continue to the game!", xInstructions, yInstructions + (LINEBREAK_VERTICAL_SHIFT * 4));

    }

    // Paint, makes the game draw itself, which makes each cardRow and acePile draw themselves, which draw each card.
    public void paint(Graphics g) {
        if (backend.getGameState() == Game.PREGAME_STATE) {
            preGameDisplay(g);
        }
        else if (backend.getGameState() == Game.INGAME_STATE) {
            backend.graphicsDraw(g);
        }
    }
}