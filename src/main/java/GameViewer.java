import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    // TODO: Complete this class
    private final int WINDOW_WIDTH = 2400;
    private final int WINDOW_HEIGHT = 1500;
    private final int TITLE_BAR_HEIGHT = 23;
    public final int[] yLevels;
    public final  int bottom = 750;

    private Game backend;
    private Image[] cardImages;

    // constructor, takes in backend object to get information
    public GameViewer(Game backend) {
        this.backend = backend;// initialize
        cardImages = new Image[53];
        yLevels = new int[19];
        for (int i = 0; i < 19; i++) {
            yLevels[i] = 50 + (i * 30);
        }


        // 4 necessary operations to make window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Solitaire");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public Image[] getImages() {
        return cardImages;
    }

    // Paint, makes the game draw itself, which makes each cardrow and acepile draw themselves, which draw each card.
    public void paint(Graphics g) {
        backend.draw(g);
    }
}