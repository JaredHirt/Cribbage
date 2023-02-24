package Player;
import Hand.Hand;
import Hand.Crib;
import Deck.Card;
import Deck.Deck;
import java.util.Scanner;

import java.util.ArrayList;

public class Player {
    private int score;
    private boolean dealer;
    private Hand myHand;
    private ArrayList<Card> peggingCards;
    public static Crib theCrib = new Crib();
    private Scanner kbd = new Scanner(System.in);

    /**
     * Player constructor
     */
    public Player(){
        myHand = new Hand();
    }

    /**
     * Sets the hand to an arraylist of Cards
     * @param cards the array list of cards which you want to set the hand to
     */
    public void setHand(ArrayList<Card> cards){myHand.setHand(cards);}

    /**
     * Sets the cut card
     * @param c the card which has been cut
     */
    public void setCutCard(Card c){myHand.setCutCard(c);}

    /**
     * Gives how many cards in hand
     * @return returns the amount of cards in the hand
     */
    public int howManyCardsInHand(){return peggingCards.size();}

    /**
     * Checks if you have a low enough card to peg
     * @param highestPossibleCard the highest possible card to play
     * @return if there is a card you can play
     */
    public boolean canPlayCard(int highestPossibleCard){
        if(howManyCardsInHand() == 0)
            return false;
        for(Card i:peggingCards)
            if(i.getRank().count() <= highestPossibleCard)
                return true;
        return false;

    }

    /**
     * Increases the score by an amount that is passed
     * @param scoreIncrease the amount to increase the score by
     * Also updates the GUI and checks if there is a win
     */
    public void increaseScore(int scoreIncrease){
        score+=scoreIncrease;
        //GUI STUFF RIGHT HERE
        //CHECK IF YOU WIN BY GETTING THESE POINTS
    }

    /**
     * Sets up the player for the new round
     */
    public void newRound(){
        myHand.setHand(new ArrayList<>());
        myHand.setCutCard(null);
        theCrib.setHand(new ArrayList<>());
    }

    /**
     * Counts the hand
     */
    public void countHand(){increaseScore(myHand.count());}

    /**
     * Counts the crib
     */
    public void countCrib(){increaseScore(theCrib.count());}

    public void discard(){
        System.out.print("\nCards in hand: ");
        for(Card i: myHand.getHand())
            System.out.print(i + " ");
        System.out.println("Please enter which card you would like to remove");
        myHand.getHand().remove()

    }

}
