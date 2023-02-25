package Player;
import Deck.Card;
import java.util.ArrayList;
import Hand.Crib;
public class AI extends Player{
    public AI(){
        super();
    }
    @Override
        public void increaseScore(int scoreIncrease){
        if(scoreIncrease == 0)
            return;
        setScore(getScore()+scoreIncrease);
        System.out.println("The Score of the AI is " + getScore());
        if(getScore() > 120) {
            System.out.println("THE AI HAS WON THE GAME");
            System.exit(1);
        }
        //GUI STUFF RIGHT HERE
    }

    @Override
    public void discard(){
        ArrayList<Card> cardsInHand = getHand().getHand();
        Crib theCrib = getTheCrib();
        theCrib.getHand().add(cardsInHand.get(0));
        cardsInHand.remove(0);
        theCrib.getHand().add(cardsInHand.get(0));
        cardsInHand.remove(0);
        setPeggingCards(new ArrayList<>(cardsInHand));
    }
}
