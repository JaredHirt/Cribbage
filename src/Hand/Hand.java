/**
 2 * This file is part of a solution to
 3 * CPSC 101 Group Project Team Frappucino
 4 *
 5 * Implements a hand class.
 7 * @author Jared Hirt
 8 * Student Number: 230154787
 9 * @version 0.1
 10 */
package Hand;
import Card.Card;

import java.lang.reflect.Array;
import java.util.ArrayList;
import Score.Counting;
public class Hand {
    private Card cutCard;
    private ArrayList<Card> hand;


    /**
     * Sets the hand to a new hand
     * @param newHand the new hand you would like the player to have
     */
    public void setHand(ArrayList<Card> newHand){
        hand = newHand;
    }
    public void setCutCard(Card c){cutCard = c;}

    /**
     * Gets the hand
     */
    public ArrayList<Card> getHand(){
        return hand;
    }

    public ArrayList<Card> getHandWithCutCard(){
        ArrayList<Card> newHand = new ArrayList<Card>();
        for(Card i: hand)
            newHand.add(i);
        newHand.add(cutCard);
        return newHand;
    }

    /**
     * Gets the point value of the hand
     * @return Returns the point value of the hand
     */
    public int count(){
        int count = 0;
        count += Counting.points15(getHandWithCutCard());
        count += Counting.pointsPair(getHandWithCutCard());
        count += Counting.flush(getHand());
        if(Counting.flush(getHand()) > 0 && getHand().get(0).getSuit() == cutCard.getSuit())
            count++;
        count += Counting.knob(getHand(), cutCard);
        count += Counting.runs(getHand());
        return count;
    }

}
