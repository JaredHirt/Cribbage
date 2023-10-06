/**
 *This file is part of a solution to
 * CPSC 101 Group Project Team Frappucino
 * Implements a Player class which stores key member variables such as the hand, the score, and a link to the crib
 * Has methods to play cards and discard cards
 * @author Jared Hirt
 * Student Number: 230154787
 */
package main.java.player;
import main.java.cribbageGUI.Controller;
import main.java.deck.CardInterface;
import main.java.game.Counting;
import main.java.hand.Hand;
import main.java.hand.Crib;
import main.java.cribbageGUI.CardButton;
import java.util.Scanner;

import java.util.ArrayList;

public class Player {
    private int score;
    private final Hand myHand;
    private ArrayList<CardButton> peggingCards;
    private static final Crib theCrib = new Crib();
    private final Scanner kbd = new Scanner(System.in);
    Controller controller;

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
    public void setHand(ArrayList<CardButton> cards){myHand.setHand(cards); peggingCards = new ArrayList<>(myHand.getHand());}

    /**
     * Sets the cut card
     * @param c the card which has been cut
     */
    public void setCutCard(CardButton c){
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
    public boolean canPlayCard(ArrayList<CardButton> cardsPegged){
        if(howManyCardsInHand() == 0)
            return false;
        if(cardsPegged.size() == 0)
            return true;
        int peggingScore = 0;
        for(CardButton i:cardsPegged)
            peggingScore += i.getCribCount();

        for(CardButton i:peggingCards)
            if(i.getRank().count() <= 31 - peggingScore)
                return true;
        return false;

    }

    /**
     * Checks if you can play a card
     * @param cardsPegged the cards that are pegged thus far
     * @param cardYouWantToPeg the card that you want to peg
     * @return if it is legal to peg that card
     */
    public boolean canPlayCard(ArrayList<CardButton> cardsPegged, CardButton cardYouWantToPeg){
        int peggingScore = 0;
        for(CardButton i:cardsPegged)
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
        outputScore();
        checkForWin();
    }


    /**
     * outputs the score
     */
    public void outputScore(){
        System.out.println("The Score of the Player is " + getScore());
    }

    /**
     * Checks if the AI has won the game
     */
    public void checkForWin(){
        if(getScore() > 120) {
            System.out.println("THE PLAYER HAS WON THE GAME");
            controller.restart();
        }
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

    /**
     * Discards cards from the hand until the player has two cards remaining
     */
    public void discardTerminal(){
        String cardStringToRemove;
        CardButton cardToRemove = null;

        while(myHand.getHand().size() > 4) {
            System.out.print("\nCards in hand: ");
            for(CardButton i: myHand.getHand())
                System.out.print(i + " ");
            System.out.println("\nPlease enter which card you would like to remove");
            cardStringToRemove = kbd.next();
            for(CardButton i: myHand.getHand())
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

    public void discard(){
        for(CardButton c: peggingCards){
            c.addActionListener(ae -> {
                getHand().getHand().remove(c);
                getPeggingCards().remove(c);
                getTheCrib().getHand().add(c);
            });
        }
    }

    /**
     * Sets the score
     * @param newScore the new score
     */
    public void setScore(int newScore){
        score = newScore;
    }

    /**
     * Gets the score
     * @return the score
     */
    public int getScore(){
        return score;
    }

    /**
     * Pegs a card
     * @param peggedCards the cards that have been pegged in this session
     */
    public void playCardTerminal(ArrayList<CardButton> peggedCards){
        String stringCardToPeg;
        CardButton cardToPeg = null;
        while(cardToPeg == null) {
            outputPeggedCards(peggedCards);

            System.out.print("\nCards you can use to peg: ");
            for (CardButton i : peggingCards)
                System.out.print(i + " ");

            System.out.println("\nWhat card would you like to peg with?");
            stringCardToPeg = kbd.next();
            if(stringCardToPeg.length() == 1) {
                for (CardButton i : peggingCards)
                    if (i.toString().substring(0, 1).equalsIgnoreCase(stringCardToPeg))
                        if (canPlayCard(peggedCards, i))
                            cardToPeg = i;
            }
            else {
                for (CardButton i : peggingCards)
                    if (i.toString().equalsIgnoreCase(stringCardToPeg))
                        if (canPlayCard(peggedCards, i))
                            cardToPeg = i;
            }
            if (cardToPeg != null) {
                peggingCards.remove(cardToPeg);
                peggedCards.add(cardToPeg);
            }
        }
    }

    public void playCard(ArrayList<CardButton> peggedCards){
        controller.drawState();
        for(CardButton c:peggingCards){
            if(canPlayCard(peggedCards, c))
                c.addActionListener(ae -> {
                            for(CardButton ca:peggingCards)
                                ca.removeAllActionListeners();
                            peggingCards.remove(c);
                            peggedCards.add(c);
                            ArrayList<CardInterface>peggedCardsInterface = new ArrayList<>(peggedCards);
                            increaseScore(Counting.pointsPegging(peggedCardsInterface));
                            controller.drawState();
                            System.out.println("Card Played");
                            controller.passPeggingToAI();
                        });
        }
    }

    /**
     * Returns the hand
     * @return returns the hand
     */
    public Hand getHand(){
        return myHand;
    }

    /**
     * Outputs the pegged cards
     * @param peggedCards the pegged cards thus far
     */
    public void outputPeggedCards(ArrayList<CardButton> peggedCards){
        System.out.print("\nPegged Cards: ");
            for (CardButton i : peggedCards)
                System.out.print(i + " ");
    }

    /**
     * Returns the crib
     * @return Returns the crib
     */
    public Crib getTheCrib(){return theCrib;}

    /**
     * Gets the pegging cards
     * @return returns the pegging cards
     */
    public ArrayList<CardButton> getPeggingCards(){return peggingCards;}

    /**
     * Sets the pegging cards
     * @param pegCard sets the pegging cards
     */
    public void setPeggingCards(ArrayList<CardButton> pegCard){peggingCards = pegCard;}
    public CardButton getCutCard(){return myHand.getCutCard();}
    public void setController(Controller control){
        controller = control;
    }
}
