package main.java.cribbageGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A panel to display the cards to be drawn.
 */
public class CardPanel extends JPanel {
    private ArrayList<CardButton> cardsToDraw;

    /**
     * Constructs a new CardPanel object with default values.
     */
    public CardPanel(){
        cardsToDraw = new ArrayList<CardButton>();
        setBackground(new Color(53,101,77));
        setBorder(BorderFactory.createRaisedBevelBorder());
    }

    /**
     * Paints the cards onto the panel.
     *
     * @param g the Graphics object to paint with.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(CardButton i: cardsToDraw){
            add(i);
        }
    }

    /**
     * Sets the cards to be drawn on the panel.
     *
     * @param c the ArrayList of Cards to be drawn.
     */
    public void setCardsToDraw(ArrayList<CardButton> c){
        cardsToDraw = new ArrayList<>(c);
    }
}
