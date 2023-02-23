package Hand;

import Game.Counting;

public class Crib extends Hand{
    public Crib(){
        super();
    }
    @Override
    public int count(){
        int count = 0;
        count += Counting.points15(getHandWithCutCard());
        count += Counting.pointsPair(getHandWithCutCard());
        count += Counting.flush(getHandWithCutCard());
        count += Counting.knob(getHand(), getCutCard());
        count += Counting.runs(getHandWithCutCard());
        return count;
    }
}
