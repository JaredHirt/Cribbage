package cribbageGUI;

import javax.swing.*;

public class CribbageFrame extends JFrame {
    public CribbageFrame(JComponent menu){
        super("Team Frappuccino Cribbage");
        setSize(800, 800);
        setLocation(100, 100);
        getContentPane().add(menu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
