package Player;
import Deck.Card;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Deck.Deck;
import Game.Counting;
import Hand.Crib;
import Hand.Hand;
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

    public void optimalDiscard(){
        ArrayList<Card> hand = getHand().getHand();
        ArrayList<Card> subsetOfHand;
        ArrayList<ArrayList<Card>> possibleDiscards = new ArrayList<>();
        ArrayList<Card> discards = new ArrayList<>(hand);
        for(int i = 0; i < hand.size(); i++)
            for(int j = i + 1; j < hand.size();j++){
                subsetOfHand = new ArrayList<Card>(hand);
                subsetOfHand.remove(hand.get(j));
                subsetOfHand.remove(hand.get(i));
                possibleDiscards.add(subsetOfHand);
            }
        while(possibleDiscards.size() > 1){
            if(countOfAllPossibleCutCards(possibleDiscards.get(0), hand) > countOfAllPossibleCutCards(possibleDiscards.get(1), hand))
                possibleDiscards.remove(1);
            else possibleDiscards.remove(0);
        }
        for(Card i: possibleDiscards.get(0))
            discards.remove(i);

        for(Card i: discards){
            getTheCrib().getHand().add(i);
            hand.remove(i);
        }

        setPeggingCards(new ArrayList<Card>(hand));

    }

    private static int countOfAllPossibleCutCards(ArrayList<Card> subset, ArrayList<Card> hand){
        int count = 0;
        ArrayList<Card> subsetWithCutCard;
        Card cardToRemove = null;
        ArrayList<Card> possibleCutCards = new ArrayList<>();
        for(int i = 0; i < 52; i++)
            possibleCutCards.add(Card.getCard(i));
        while(possibleCutCards.size() > 46) {
            for (Card i : hand)
                for (Card j : possibleCutCards)
                    if (i.toString().equals(j.toString()))
                        cardToRemove = j;
            possibleCutCards.remove(cardToRemove);
        }
        for(Card i: possibleCutCards){
            subsetWithCutCard = new ArrayList<Card>(subset);
            subsetWithCutCard.add(i);

            count += Counting.points15(subsetWithCutCard);
            count += Counting.pointsPair(subsetWithCutCard);
            count += Counting.flush(subset);
            if(Counting.flush(subset) > 0 && subset.get(0).getSuit() == i.getSuit())
                count++;
            count += Counting.knob(subset, i);
            count += Counting.runs(subsetWithCutCard);
        }
        return count;
    }
}

