package cribbageGUI;

import javax.swing.*;
import java.awt.*;

public class BoardComponent extends JComponent {
    private ImageIcon board;
    public BoardComponent(ImageIcon board){
        this.board = board;
        JLabel label = new JLabel();
        label.setIcon(board);
        add(label);
    }
}
