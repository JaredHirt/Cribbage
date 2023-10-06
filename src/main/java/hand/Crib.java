/**
 *This file is part of a solution to
 * CPSC 101 Group Project Team Frappucino
 * Implements a Crib class which acts as a hand but with a separate counting method because of flushes
 * @author Jared Hirt
 * Student Number: 230154787
 */
package main.java.hand;

import main.java.cribbageGUI.CardButton;
import main.java.deck.CardInterface;
import main.java.game.Counting;

import java.util.ArrayList;

public class Crib extends Hand{
    public Crib(){
        super();
    }

    /**
     * Returns the points from the hand
     * @return the points from the hand
     */
    @Override
    public int count(){
        ArrayList<CardInterface> handWithCutCard = new ArrayList<>(getHandWithCutCard());
        ArrayList<CardInterface> hand = new ArrayList<>(getHand());
        int count = 0;
        count += Counting.points15(handWithCutCard);
        count += Counting.pointsPair(handWithCutCard);
        count += Counting.flush(handWithCutCard);
        count += Counting.knob(hand, getCutCard());
        count += Counting.runs(handWithCutCard);

        System.out.print("\nThe Crib of ");
        for(CardButton i: getHand())
            System.out.print(i+ " ");

        System.out.println("with a cut card of " + getCutCard() + " scored " + count + " points");

        return count;
    }
}
