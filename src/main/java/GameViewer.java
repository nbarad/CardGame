import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    // Instance variables
    private final int WINDOW_WIDTH = 2400;
    private final int WINDOW_HEIGHT = 1500;
    public final int[] yLevels;
    public final int bottom = 750;
    private Game backend;

    // We will use a JPanel as our canvas to properly mix custom drawing with buttons
    private JPanel canvas;

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

        // Create a custom JPanel that handles all the card drawing
        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Clears the background to prevent graphical glitches
                backend.graphicsDraw(g); // Draws your white background and all the cards
            }
        };

        // Use absolute positioning on the canvas so we can freely place the button
        canvas.setLayout(null);

        // Create and place the Restart Button
        JButton restartBtn = new JButton("Restart Game");
        restartBtn.setBounds(20, 20, 130, 40); // Top-left corner

        // When clicked, call the reset method in Game.java
        restartBtn.addActionListener(e -> backend.restartGame());

        // Add the button to the canvas, and the canvas to the window
        canvas.add(restartBtn);
        this.add(canvas);

        this.setVisible(true);
    }

    // We completely removed the public void paint(Graphics g) override here!
    // When Game calls window.repaint(), the JFrame will automatically pass
    // the repaint request down to our custom canvas JPanel.
}