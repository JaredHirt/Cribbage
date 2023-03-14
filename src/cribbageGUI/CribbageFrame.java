package cribbageGUI;

import deck.Card;

import javax.swing.*;
import java.awt.*;

public class CribbageFrame extends JFrame {
    private Controller controller;
    public CribbageFrame(){
        super("Team Frappuccino Cribbage");
        setSize(800, 800);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void setController(Controller control){
        controller = control;
    }


}
