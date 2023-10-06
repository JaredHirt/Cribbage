/**
 *This file is part of a solution to
 * CPSC 101 Group Project Team Frappucino
 * Implements an AI class which holds key variables for the AI and performs some operations
 * @author Jared Hirt
 * Student Number: 230154787
 */
package main.java.player;
import main.java.cribbageGUI.CardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import main.java.deck.Card;
import main.java.deck.CardInterface;
import main.java.deck.CardInterface;
import main.java.game.Counting;
import main.java.hand.Crib;
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
        ArrayList<CardButton> cardsInHand = getHand().getHand();
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
    public void playCard(ArrayList<CardButton> peggedCards){
        //Choosing which card to peg
        CardButton cardToPeg = null;
        ArrayList<CardButton> peggingCards = getPeggingCards();
        for(CardButton i: peggingCards)
            if(super.canPlayCard(peggedCards, i)) {
                cardToPeg = i;
            }

        pegCard(peggedCards, cardToPeg);
        super.outputPeggedCards(peggedCards);
        controller.drawState();
        ArrayList<CardInterface> peggedCardsInterface = new ArrayList<>(peggedCards);
        increaseScore(Counting.pointsPegging(peggedCardsInterface));
        controller.passPeggingtoPlayer();
    }

    /**
     * Adds a card to pegged cards, and removes it from pegging cards
     * @param peggedCards the ArrayList of cards that have been already pegged
     * @param cardToPeg the card you want to peg
     */
    private void pegCard(ArrayList<CardButton> peggedCards, CardButton cardToPeg){
        ArrayList<CardButton> peggingCards = getPeggingCards();
        peggedCards.add(cardToPeg);
        peggingCards.remove(cardToPeg);

    }

    /**
     * does a smart discard of hand, calculates all possible discards and keeps the hand with the highest average point count
     */
    @Override
    public void discard(){
        ArrayList<CardButton> hand = getHand().getHand();
        ArrayList<CardInterface> subsetOfHand;
        ArrayList<ArrayList<CardInterface>> possibleDiscards = new ArrayList<>();
        ArrayList<CardInterface> discards = new ArrayList<>(hand);
        for(int i = 0; i < hand.size(); i++)
            for(int j = i + 1; j < hand.size();j++){
                subsetOfHand = new ArrayList<>(hand);
                subsetOfHand.remove(hand.get(j));
                subsetOfHand.remove(hand.get(i));
                possibleDiscards.add(subsetOfHand);
            }
        possibleDiscards.set(0, Collections.max(possibleDiscards, (Comparator.comparingInt(c -> countOfAllPossibleCutCards(c, hand)))));
        for(CardInterface i: possibleDiscards.get(0))
            discards.remove(i);

        for(CardInterface i: discards){
            getTheCrib().getHand().add(i.getCardButton());
            hand.remove(i.getCardButton());
        }

        setPeggingCards(new ArrayList<>(hand));
    }

    /**
     * Finds the total of points the hand could earn with every single cut card
     * @param subset the subset of hand without the discards
     * @param hand the full hand even the cards you would discard
     * @return returns the point of every single possible cut card
     */
    private static int countOfAllPossibleCutCards(ArrayList<CardInterface> subset, ArrayList<CardButton> hand){
        int count = 0;
        ArrayList<CardInterface> subsetWithCutCard;
        CardInterface cardToRemove = null;
        ArrayList<CardInterface> possibleCutCards = new ArrayList<>();
        for(int i = 0; i < 52; i++)
            possibleCutCards.add(Card.getCard(i));
        while(possibleCutCards.size() > 46) {
            for (CardButton i : hand)
                for (CardInterface j : possibleCutCards)
                    if (i.toString().equals(j.toString()))
                        cardToRemove = j;
            possibleCutCards.remove(cardToRemove);
        }
        for(CardInterface i: possibleCutCards){
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

