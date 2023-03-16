package cribbageGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BoardComponent extends JComponent {
    private JLabel board;
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

    @Override
    public void paintComponent(Graphics g){
        add(board);
    }
}
