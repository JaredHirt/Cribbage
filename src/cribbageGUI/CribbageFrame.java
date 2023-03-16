package cribbageGUI;

import deck.Card;
import javax.swing.*;
import java.awt.*;

/**
 * This class represents the main frame of the Cribbage game GUI.
 */
public class CribbageFrame extends JFrame {
    private Controller controller;

    /**
     * Constructor for CribbageFrame class.
     * Creates a new instance of CribbageFrame and sets its properties.
     */
    public CribbageFrame(){
        super("Team Frappuccino Cribbage");
        setLayout(new BorderLayout());
        setBackground(new Color(102, 51, 0));
        setSize(800, 600);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Sets the controller for the CribbageFrame.
     * @param control the controller for the CribbageFrame.
     */
    public void setController(Controller control){
        controller = control;
    }


}
