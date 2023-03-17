/**
 *This file is part of a solution to
 * CPSC 101 Group Project Team Frappucino
 * Implements an AI class which holds key variables for the AI and performs some operations
 * @author Jared Hirt
 * Student Number: 230154787
 */
package player;
import cribbageGUI.Controller;
import deck.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import game.Counting;
import hand.Crib;
public class AI extends Player{
    /**
     * Initializes the AI class the same as the player class
     */
    public AI(){
        super();
    }

    /**
     *
     * @param scoreIncrease the amount to increase the score by
     * Also updates the GUI and checks if there is a win
     */
    @Override
        public void increaseScore(int scoreIncrease){
        if(scoreIncrease == 0)
            return;
        setScore(getScore()+scoreIncrease);
        outputScore();
        checkForWin();
        }

    /**
     * outputs the score
     */
    @Override
    public void outputScore(){
        System.out.println("The Score of the AI is " + getScore());
    }

    /**
     * Checks if the AI has won the game
     */
    @Override
    public void checkForWin(){
        if(getScore() > 120) {
            System.out.println("THE AI HAS WON THE GAME");
            controller.restart();
        }
    }


    /**
     * Old random discard method, please keep for potential use in easier difficulty
     */
    public void randomDiscard(){
        ArrayList<Card> cardsInHand = getHand().getHand();
        Crib theCrib = getTheCrib();
        theCrib.getHand().add(cardsInHand.get(0));
        cardsInHand.remove(0);
        theCrib.getHand().add(cardsInHand.get(0));
        cardsInHand.remove(0);
        setPeggingCards(new ArrayList<>(cardsInHand));
    }

    /**
     * chooses which card to get pegged
     * @param peggedCards the cards that have been pegged in this session
     */
    @Override
    public void playCard(ArrayList<Card> peggedCards){
        //Choosing which card to peg
        Card cardToPeg = null;
        ArrayList<Card> peggingCards = getPeggingCards();
        for(Card i: peggingCards)
            if(super.canPlayCard(peggedCards, i)) {
                cardToPeg = i;
            }

        pegCard(peggedCards, cardToPeg);
        super.outputPeggedCards(peggedCards);
        controller.drawState();
        increaseScore(Counting.pointsPegging(peggedCards));
        controller.passPeggingtoPlayer();
    }

    /**
     * Adds a card to pegged cards, and removes it from pegging cards
     * @param peggedCards the ArrayList of cards that have been already pegged
     * @param cardToPeg the card you want to peg
     */
    private void pegCard(ArrayList<Card> peggedCards, Card cardToPeg){
        ArrayList<Card> peggingCards = getPeggingCards();
        peggedCards.add(cardToPeg);
        peggingCards.remove(cardToPeg);

    }

    /**
     * does a smart discard of hand, calculates all possible discards and keeps the hand with the highest average point count
     */
    @Override
    public void discard(){
        ArrayList<Card> hand = getHand().getHand();
        ArrayList<Card> subsetOfHand;
        ArrayList<ArrayList<Card>> possibleDiscards = new ArrayList<>();
        ArrayList<Card> discards = new ArrayList<>(hand);
        for(int i = 0; i < hand.size(); i++)
            for(int j = i + 1; j < hand.size();j++){
                subsetOfHand = new ArrayList<>(hand);
                subsetOfHand.remove(hand.get(j));
                subsetOfHand.remove(hand.get(i));
                possibleDiscards.add(subsetOfHand);
            }
        possibleDiscards.set(0, Collections.max(possibleDiscards, (Comparator.comparingInt(c -> countOfAllPossibleCutCards(c, hand)))));
        for(Card i: possibleDiscards.get(0))
            discards.remove(i);

        for(Card i: discards){
            getTheCrib().getHand().add(i);
            hand.remove(i);
        }

        setPeggingCards(new ArrayList<>(hand));
    }

    /**
     * Finds the total of points the hand could earn with every single cut card
     * @param subset the subset of hand without the discards
     * @param hand the full hand even the cards you would discard
     * @return returns the point of every single possible cut card
     */
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
            subsetWithCutCard = new ArrayList<>(subset);
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

