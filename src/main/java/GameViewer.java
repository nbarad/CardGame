import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    // TODO: Complete this class
    private final int WINDOW_WIDTH = 2400;
    private final int WINDOW_HEIGHT = 1500;
    private final int TITLE_BAR_HEIGHT = 23;
    public final int[19] yLevels;
    public final  int bottom = 100;

    private Game backend;
    private Image[] cardImages;

    // constructor, takes in backend object to get information
    public GameViewer(Game backend) {
        this.backend = backend;// initialize

        cardImages = new Image[53];

        for (int i = 0; i < 7) {

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

    // Paint, makes each square draw itself, the axes labels,  and win/tie message
    public void paint(Graphics g) {
        for (Card c : backend.getTheDeck().getDeck()) {
            c.
        }
    }
}