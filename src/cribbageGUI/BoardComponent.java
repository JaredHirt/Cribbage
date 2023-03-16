package cribbageGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The BoardComponent class is a custom Swing component used to display the game board image.
 */
public class BoardComponent extends JComponent {
    private JLabel board;

    /**
     * Constructs a new BoardComponent with the specified image for the game board.
     *
     * @param board the image for the game board.
     */
    public BoardComponent(ImageIcon board){
        super();
        setLayout(new BorderLayout());
        this.board = new JLabel(board, JLabel.RIGHT);
        setSize(board.getIconWidth(), board.getIconHeight());
        this.board.setBorder(new EmptyBorder(20,20,20,20));
        this.board.setBackground(new Color(102,50,0));
        this.board.setOpaque(true);
        setBackground(Color.blue);
        add(this.board);
        setVisible(true);
    }

    /**
     * Overrides the paintComponent method to add the board JLabel to the component.
     *
     * @param g the Graphics object used to paint the component.
     */
    @Override
    public void paintComponent(Graphics g){
        add(board);
    }
}
