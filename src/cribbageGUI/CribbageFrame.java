package cribbageGUI;

import deck.Card;

import javax.swing.*;
import java.awt.*;

public class CribbageFrame extends JFrame {
    private Controller controller;
    public CribbageFrame(){
        super("Team Frappuccino Cribbage");
        setLayout(new BorderLayout());
        setBackground(new Color(102, 51, 0));
        setSize(1600, 1200);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void setController(Controller control){
        controller = control;
    }


}
