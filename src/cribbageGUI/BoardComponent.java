package cribbageGUI;

import javax.swing.*;
import java.awt.*;

public class BoardComponent extends JComponent {
    private JLabel board;
    public BoardComponent(ImageIcon board){
        super();
        setLayout(new BorderLayout());
        this.board = new JLabel(board, JLabel.RIGHT);
        add(this.board);
        setSize(board.getIconWidth(), board.getIconHeight());
        setBackground(Color.blue);
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){
        add(board);
    }
}
