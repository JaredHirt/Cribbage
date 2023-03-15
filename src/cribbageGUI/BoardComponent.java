package cribbageGUI;

import javax.swing.*;
import java.awt.*;

public class BoardComponent extends JComponent {
    private JLabel board;
    public BoardComponent(ImageIcon board){
        super();
        setLayout(new BorderLayout());
        this.board = new JLabel(board);
        add(this.board);
        setVisible(true);
    }
}
