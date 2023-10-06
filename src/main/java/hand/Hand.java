/**
 *This file is part of a solution to
 * CPSC 101 Group Project Team Frappucino
 * Implements a Hand class which holds the hand and the cut card
 * Has a count hand method
 * @author Jared Hirt
 * Student Number: 230154787
 */
package main.java.hand;
import main.java.cribbageGUI.CardButton;

import java.util.ArrayList;

import main.java.deck.CardInterface;
import main.java.game.Counting;
public class Hand {
    private CardButton cutCard;
    private ArrayList<CardButton> hand;

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
    public void setHand(ArrayList<CardButton> newHand){
        hand = newHand;
    }

    /**
     * Sets the cut card
     * @param c the card that is cut
     */
    public void setCutCard(CardButton c){cutCard = c;}

    /**
     * Returns the cut card
     * @return the card that is cut
     */
    public CardButton getCutCard(){return cutCard;}

    /**
     * Gets the hand without the cut card
     */
    public ArrayList<CardButton> getHand(){
        return hand;
    }

    /**
     * Returns the hand with the cut card
     * @return the hand with the cut card
     */
    public ArrayList<CardButton> getHandWithCutCard(){
        ArrayList<CardButton> newHand = new ArrayList<>(hand);
        newHand.add(cutCard);
        return newHand;
    }

    /**
     * Gets the point value of the hand
     * @return Returns the point value of the hand
     */
    public int count(){
        ArrayList<CardInterface> handWithCutCard = new ArrayList<>(getHandWithCutCard());
        ArrayList<CardInterface> hand = new ArrayList<>(getHand());
        int count = 0;
        count += Counting.points15(handWithCutCard);
        count += Counting.pointsPair(handWithCutCard);
        count += Counting.flush(hand);
        if(Counting.flush(hand) > 0 && getHand().get(0).getSuit() == cutCard.getSuit())
            count++;
        count += Counting.knob(hand, cutCard);
        count += Counting.runs(handWithCutCard);

        System.out.print("\nThe Hand of ");
        for(CardButton i: getHand())
            System.out.print(i+ " ");

        System.out.println("with a cut card of " + getCutCard() + " scored " + count + " points");
        return count;
    }

}
