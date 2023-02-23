package Testing;
import Deck.Card;
import Hand.Hand;
import Deck.Rank;
import Deck.Suit;

import java.util.ArrayList;

public class ScoringTest {
    public static void main(String[] args) {

        Card[] test1Hand = {Card.getCard("5S"), Card.getCard("5D"), Card.getCard("5C"), Card.getCard("JH")};
        System.out.println(checkScore(getArrayOfCards(test1Hand), Card.getCard("5H"), 29));

        Card[] test2Hand = {Card.getCard("4S"), Card.getCard("5C"), Card.getCard("5D"), Card.getCard("4C")};
        System.out.println(checkScore(getArrayOfCards(test2Hand), Card.getCard("6C"), 24));




    }
    public static boolean checkScore(ArrayList<Card> cards, Card cutCard, int expectedScore){
        Hand testHand = new Hand();
        testHand.setHand(cards);
        testHand.setCutCard(cutCard);
        System.out.println(testHand.count());
        return testHand.count() == expectedScore;
    }
    public static ArrayList<Card> getArrayOfCards(Card[] cards){
        ArrayList<Card> newArray = new ArrayList<Card>();
        for(Card i: cards)
            newArray.add(i);
        return newArray;
    }
}
