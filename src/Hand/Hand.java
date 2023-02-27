/**
 *This file is part of a solution to
 * CPSC 101 Group Project Team Frappucino
 * Implements a Hand class which holds the hand and the cut card
 * Has a count hand method
 * @author Jared Hirt
 * Student Number: 230154787
 */
package Hand;
import Deck.Card;

import java.util.ArrayList;
import Game.Counting;
public class Hand {
    private Card cutCard;
    private ArrayList<Card> hand;

    /**
     * Initializes the hand with a new array list
     */
    public Hand(){
        hand = new ArrayList<>();
    }

    /**
     * Sets the hand to a new hand
     * @param newHand the new hand you would like the player to have
     */
    public void setHand(ArrayList<Card> newHand){
        hand = newHand;
    }

    /**
     * Sets the cut card
     * @param c the card that is cut
     */
    public void setCutCard(Card c){cutCard = c;}

    /**
     * Returns the cut card
     * @return the card that is cut
     */
    public Card getCutCard(){return cutCard;}

    /**
     * Gets the hand without the cut card
     */
    public ArrayList<Card> getHand(){
        return hand;
    }

    /**
     * Retuns the hand with the cut card
     * @return the hand with the cut card
     */
    public ArrayList<Card> getHandWithCutCard(){
        ArrayList<Card> newHand = new ArrayList<>(hand);
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
        count += Counting.runs(getHandWithCutCard());

        System.out.print("\nThe Hand of ");
        for(Card i: getHand())
            System.out.print(i+ " ");

        System.out.println("with a cut card of " + getCutCard() + " scored " + count + " points");
        return count;
    }

}
