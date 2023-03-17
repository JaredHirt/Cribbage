/**
 *This file is part of a solution to
 * CPSC 101 Group Project Team Frappucino
 * Implements a Crib class which acts as a hand but with a separate counting method because of flushes
 * @author Jared Hirt
 * Student Number: 230154787
 */
package hand;

import deck.Card;
import game.Counting;

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
        int count = 0;
        count += Counting.points15(getHandWithCutCard());
        count += Counting.pointsPair(getHandWithCutCard());
        count += Counting.flush(getHandWithCutCard());
        count += Counting.knob(getHand(), getCutCard());
        count += Counting.runs(getHandWithCutCard());

        System.out.print("\nThe Crib of ");
        for(Card i: getHand())
            System.out.print(i+ " ");

        System.out.println("with a cut card of " + getCutCard() + " scored " + count + " points");

        return count;
    }
}
