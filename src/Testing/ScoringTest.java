/**
 *This file is part of a solution to
 * CPSC 101 Group Project Team Frappucino
 * Implements a testing class to test the scoring capabilities of the hand and cribbage class
 * @author Jared Hirt
 * Student Number: 230154787
 */
package Testing;
import Deck.Card;
import Hand.Hand;
import Hand.Crib;


import java.util.ArrayList;
import java.util.Arrays;

public class ScoringTest {
    public static void main(String[] args) {


        Card[] test1Hand = {Card.getCard("5S"), Card.getCard("5D"), Card.getCard("5C"), Card.getCard("JH")};
        System.out.println(checkScore(getArrayOfCards(test1Hand), Card.getCard("5H"), 29));

        Card[] test2Hand = {Card.getCard("4S"), Card.getCard("5C"), Card.getCard("5D"), Card.getCard("4C")};
        System.out.println(checkScore(getArrayOfCards(test2Hand), Card.getCard("6C"), 24));

        Card [] test3Hand = {Card.getCard("AS"), Card.getCard("2C"), Card.getCard("3D"), Card.getCard("4C")};
        System.out.println(checkScore(getArrayOfCards(test3Hand), Card.getCard("5C"), 7));

        Card [] test4Hand = {Card.getCard("5S"), Card.getCard("5C"), Card.getCard("5D"), Card.getCard("5H")};
        System.out.println(checkScore(getArrayOfCards(test4Hand), Card.getCard("JC"), 28));

        Card [] test5Hand = {Card.getCard("7S"), Card.getCard("7C"), Card.getCard("8D"), Card.getCard("8H")};
        System.out.println(checkScore(getArrayOfCards(test5Hand), Card.getCard("9C"), 24));

        Card[] test6Hand = {Card.getCard("5S"), Card.getCard("5D"), Card.getCard("5C"), Card.getCard("JH")};
        System.out.println(checkScoreCrib(getArrayOfCards(test6Hand), Card.getCard("5H"), 29));



    }

    /**
     * Checks the score of a hand
     * @param cards the hand without the cut card
     * @param cutCard the cut card
     * @param expectedScore the expected score for the hand
     * @return if the hand count equals the expected score
     */
    public static boolean checkScore(ArrayList<Card> cards, Card cutCard, int expectedScore){
        Hand testHand = new Hand();
        testHand.setHand(cards);
        testHand.setCutCard(cutCard);
        System.out.println(testHand.count());
        return testHand.count() == expectedScore;
    }

    /**
     * Checks the score for the crib
     * @param cards the crib without the cut card
     * @param cutCard the cut card
     * @param expectedScore the expected score
     * @return if the score equals the expected score
     */
    public static boolean checkScoreCrib(ArrayList<Card> cards, Card cutCard, int expectedScore){
        Crib testHand = new Crib();
        testHand.setHand(cards);
        testHand.setCutCard(cutCard);
        System.out.println(testHand.count());
        return testHand.count() == expectedScore;
    }


    /**
     *
     * @param cards An array of cards
     * @return the cards but as an ArrayList
     */
    public static ArrayList<Card> getArrayOfCards(Card[] cards){
        return new ArrayList<>(Arrays.asList(cards));
    }
}
