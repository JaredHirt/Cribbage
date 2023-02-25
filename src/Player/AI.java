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

    //Please optimize this eventually

    /**
     * Automated AI discard method
     */
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

    @Override
    public void playCard(ArrayList<Card> peggedCards){
        Card cardToPeg = null;
        ArrayList<Card> peggingCards = getPeggingCards();
        for(Card i: peggingCards)
            if(super.canPlayCard(peggedCards, i)) {
                cardToPeg = i;
            }
        peggedCards.add(cardToPeg);
        getPeggingCards().remove(cardToPeg);

        System.out.print("\nPegged Cards: ");
            for (Card i : peggedCards)
                System.out.print(i + " ");
        System.out.println();

    }
}
