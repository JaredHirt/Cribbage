/**
 *This file is part of a solution to
 * CPSC 101 Group Project Team Frappucino
 * Holds all the game objects, and runs the game
 * @author Jared Hirt
 * Student Number: 230154787
 */
package game;


import cribbageGUI.Controller;
import deck.Deck;
import deck.Card;
import deck.Rank;
import player.Player;
import player.AI;
import javax.swing.*;


import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private boolean gameStarted;
    private Controller controller;
    private Player player;
    private AI ai;
    private Player dealer;
    private Player pone;
    private Deck theDeck;
    private ArrayList cardsPegged;

    public Game(){
        cribbageGUI.Controller control = controller;
        //Setting up the game
        theDeck = new Deck();
        player = new Player();
        ai = new AI();
        Player pone = null;
        Player dealer = null;
        Player swap;
        cardsPegged = new ArrayList<>();
    }

    public void findDealer(){
        //Finding out who the dealer is
        //Need to make a GUI for this action
        boolean dealerFound = false;
        Card[] dealtCards;
        while(!dealerFound){
            dealtCards = theDeck.returnUniqueCards(2);
            if(dealtCards[0].getRank().ordinal() < dealtCards[1].getRank().ordinal()) {
                dealerFound = true;
                dealer = player;
                pone = ai;
            }
            if(dealtCards[0].getRank().ordinal() > dealtCards[1].getRank().ordinal()){
                dealerFound = true;
                dealer = ai;
                pone = player;
            }
        }

    }

    public void deal(){
        Card[] dealtCards;
        dealer.newRound();
        pone.newRound();
        cardsPegged.clear();
        //Dealing the cards 1-6 is for the dealer, 7-12 is for the pone, 13 is the cut card which is not revealed to the players yet
        dealtCards = theDeck.returnUniqueCards(13);
        //This monstrosity of code is getting the first 6 numbers of dealtCards and passing it to the setHand method
        dealer.setHand(new ArrayList<>(Arrays.asList(Arrays.copyOfRange(dealtCards, 0, 6))));
        pone.setHand(new ArrayList<>(Arrays.asList(Arrays.copyOfRange(dealtCards, 6, 12))));

        dealer.setCutCard(dealtCards[12]);
        pone.setCutCard(dealtCards[12]);
    }

    public void swapDealer(){
        Player swap = dealer;
        dealer = pone;
        pone = swap;
    }
    public void countHands(){
        pone.countHand();
        dealer.countHand();
        dealer.countCrib();
    }
    public void ponePegging(){

        while(dealer.howManyCardsInHand() + pone.howManyCardsInHand() > 0) {
            //Pone Pegging
            if (pone.canPlayCard(cardsPegged)) {
                pone.playCard(cardsPegged);
                pone.increaseScore(Counting.pointsPegging(cardsPegged));
                if(!(pone.canPlayCard(cardsPegged) || dealer.canPlayCard(cardsPegged))) {
                    pone.increaseScore(1);
                    cardsPegged.clear();
                }
            }
        }

    }
    public void setController(Controller control){controller = control;}

    public Player getPlayer(){return player;}
    public AI getAi(){return ai;}

    public void drawState(){
        controller.drawState();
    }
}
