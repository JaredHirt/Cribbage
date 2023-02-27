package Hand;

import Deck.Card;
import Game.Counting;

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
