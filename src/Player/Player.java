package Player;
import Hand.Hand;
import Hand.Crib;
import Deck.Card;
import java.util.Scanner;

import java.util.ArrayList;

public class Player {
    private int score;
    private final Hand myHand;
    private ArrayList<Card> peggingCards;
    private static final Crib theCrib = new Crib();
    private final Scanner kbd = new Scanner(System.in);

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
    public void setCutCard(Card c){
        myHand.setCutCard(c);
        theCrib.setCutCard(c);
    }

    /**
     * Gives how many cards in hand
     * @return returns the amount of cards in the hand
     */
    public int howManyCardsInHand(){return peggingCards.size();}

    /**
     * Checks if you have a low enough card to peg
     * @param cardsPegged the arraylist of cards pegged
     * @return if there is a card you can play
     */
    public boolean canPlayCard(ArrayList<Card> cardsPegged){
        if(howManyCardsInHand() == 0)
            return false;
        if(cardsPegged.size() == 0)
            return true;
        int peggingScore = 0;
        for(Card i:cardsPegged)
            peggingScore += i.getCribCount();

        for(Card i:peggingCards)
            if(i.getRank().count() <= 31 - peggingScore)
                return true;
        return false;

    }

    public boolean canPlayCard(ArrayList<Card> cardsPegged, Card cardYouWantToPeg){
        int peggingScore = 0;
        for(Card i:cardsPegged)
            peggingScore += i.getCribCount();
        return (peggingScore + cardYouWantToPeg.getCribCount() <= 31);
    }

    /**
     * Increases the score by an amount that is passed
     * @param scoreIncrease the amount to increase the score by
     * Also updates the GUI and checks if there is a win
     */
    public void increaseScore(int scoreIncrease){
        if(scoreIncrease == 0)
            return;
        score+=scoreIncrease;
        System.out.println("The Score of the player is " + score);
        if(getScore() > 120) {
            System.out.println("THE PLAYER HAS WON THE GAME");
            System.exit(1);
        }
        //GUI STUFF RIGHT HERE
    }

    /**
     * Sets up the player for the new round
     */
    public void newRound(){
        myHand.getHand().clear();
        myHand.setCutCard(null);
        theCrib.getHand().clear();
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
        String cardStringToRemove;
        Card cardToRemove = null;

        while(myHand.getHand().size() > 4) {
            System.out.print("\nCards in hand: ");
            for(Card i: myHand.getHand())
                System.out.print(i + " ");
            System.out.println("\nPlease enter which card you would like to remove");
            cardStringToRemove = kbd.next();
            for(Card i: myHand.getHand())
                if(i.toString().equalsIgnoreCase(cardStringToRemove))
                    cardToRemove = i;
            if(cardToRemove != null) {
                theCrib.getHand().add(cardToRemove);
                myHand.getHand().remove(cardToRemove);
            }
            cardToRemove = null;

        }
        peggingCards = new ArrayList<>(myHand.getHand());
    }

    public void setScore(int newScore){
        score = newScore;
    }
    public int getScore(){
        return score;
    }

    /**
     * Pegs a card
     * @param peggedCards the cards that have been pegged in this session
     */
    public void playCard(ArrayList<Card> peggedCards){
        String stringCardToPeg;
        Card cardToPeg = null;
        while(cardToPeg == null) {
            System.out.print("\nPegged Cards: ");
            for (Card i : peggedCards)
                System.out.print(i + " ");

            System.out.print("\nCards you can use to peg: ");
            for (Card i : peggingCards)
                System.out.print(i + " ");

            System.out.println("\nWhat card would you like to peg with?");
            stringCardToPeg = kbd.next();
            for (Card i : peggingCards)
                if (i.toString().equalsIgnoreCase(stringCardToPeg))
                    if(canPlayCard(peggedCards, i))
                        cardToPeg = i;
            if (cardToPeg != null) {
                peggingCards.remove(cardToPeg);
                peggedCards.add(cardToPeg);
            }
        }
    }
    public Hand getHand(){
        return myHand;
    }

    public Crib getTheCrib(){return theCrib;}
    public ArrayList<Card> getPeggingCards(){return peggingCards;}
    public void setPeggingCards(ArrayList<Card> pegCard){peggingCards = pegCard;}
}
