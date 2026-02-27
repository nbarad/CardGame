import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    // Instance variables
    private final int WINDOW_WIDTH = 2400;
    private final int WINDOW_HEIGHT = 1500;
    public final int[] yLevels;
    public final  int bottom = 750;
    private Game backend;

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

    // Paint, makes the game draw itself, which makes each cardRow and acePile draw themselves, which draw each card.
    public void paint(Graphics g) {
        backend.graphicsDraw(g);
    }
}