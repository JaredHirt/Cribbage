/**
 *This file is part of a solution to
 * CPSC 101 Group Project Team Frappucino
 * Implements a Deck class which stores all cards used in the project, as well as deals cards to the players each round
 * @author Jared Hirt
 * Student Number: 230154787
 */
package deck;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    final ArrayList<Card> theCards;


    public Deck(){
        theCards = new ArrayList<>();
        for(int i = 0; i < 52; i++)
            theCards.add(Card.getCard(i));
    }

    /**
     * Returns a set amount of unique cards
     */
    public Card[] returnUniqueCards(int numberOfCards){
        //adding all possible numbers to an array list
        if(52 < numberOfCards || numberOfCards < 0)
            return new Card[0];
        Card [] uniqueCards = new Card[numberOfCards];
        ArrayList<Integer> listOfNumbers = new ArrayList<>();
        for(int i = 0; i < 52; i++)
            listOfNumbers.add(i);

        //Getting the random numbers
        for(int i = 0; i < uniqueCards.length;i++) {
            Collections.shuffle(listOfNumbers);
            uniqueCards[i] = theCards.get(listOfNumbers.get(0));
            listOfNumbers.remove(0);
        }

        return uniqueCards;
    }

}
