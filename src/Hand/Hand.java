/**
 2 * This file is part of a solution to
 3 * CPSC 101 Group Project Team Frappucino
 4 *
 5 * Implements a hand class
 7 * @author Jared Hirt
 8 * Student Number: 230154787
 9 * @version 0.1
 10 */
package Hand;
import Card.Card;
import java.util.ArrayList;
public class Hand {
    private ArrayList<Card> hand;
    private Card cutCard;
    public Hand(){
        hand = new ArrayList<Card>();
    }
}
