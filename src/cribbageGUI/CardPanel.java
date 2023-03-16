package cribbageGUI;

import deck.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardPanel extends JPanel {
    private ArrayList<Card> cardsToDraw;
    public CardPanel(){
        cardsToDraw = new ArrayList<Card>();
        setBackground(new Color(53,101,77));
        setBorder(BorderFactory.createRaisedBevelBorder());
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Card i: cardsToDraw){
            add(i);
        }
    }
    public void setCardsToDraw(ArrayList<Card> c){
        cardsToDraw = new ArrayList<>(c);
    }
}
