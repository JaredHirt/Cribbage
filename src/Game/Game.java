/**
 *This file is part of a solution to
 * CPSC 101 Group Project Team Frappucino
 * Holds all the game objects, and runs the game
 * @author Jared Hirt
 * Student Number: 230154787
 */
package Game;


import Deck.Deck;
import Deck.Card;
import Deck.Rank;
import Player.Player;
import Player.AI;


import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    public static void playGame() {
        //Setting up the game
        Deck theDeck = new Deck();
        Player player = new Player();
        AI ai = new AI();

        Player pone = null;
        Player dealer = null;
        Player swap;

        ArrayList<Card> cardsPegged = new ArrayList<>();

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

        //Game is now setup, now play can begin
        //while(true) is temporary, it represents a round, replace it later down to make the code more readable
        while(true){
            System.out.println("\n\n\n\nBEGINNING OF NEW ROUND");
            if(dealer instanceof AI)
                System.out.println("Player is the Pone");
            else System.out.println("Player is the Dealer");
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

            //The cards are now all dealt

            dealer.discard();
            pone.discard();
            //Make sure to set the pegging hand with the discard method

            //Reveal cut card in the GUI, if it is a jack then give the dealer 2 points for "his knees"
            if(dealtCards[12].getRank() == Rank.Jack)
                dealer.increaseScore(2);


            //Code for pegging

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



                //Dealer Pegging
                if (dealer.canPlayCard(cardsPegged)) {
                    dealer.playCard(cardsPegged);
                    dealer.increaseScore(Counting.pointsPegging(cardsPegged));
                    if(!(pone.canPlayCard(cardsPegged) || dealer.canPlayCard(cardsPegged))) {
                        dealer.increaseScore(1);
                        cardsPegged.clear();
                    }
                }
            }

            //Counting the score of the hands
            pone.countHand();
            dealer.countHand();
            dealer.countCrib();

            //Switching the dealer and the pone for the next round
            swap = dealer;
            dealer = pone;
            pone = swap;
        }

    }

    public static void main(String[] args) {
        playGame();
    }
}
