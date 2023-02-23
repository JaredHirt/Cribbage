package Testing;
import Card.Card;
import Hand.Hand;
import Card.Rank;
import Card.Suit;

import java.util.ArrayList;

public class ScoringTest {
    public static void main(String[] args) {
        Card[] test1Hand = {Card.getCard("5S"), Card.getCard("5D"), Card.getCard("5C"), Card.getCard("JH")};
        System.out.println(checkScore(getArrayOfCards(test1Hand), Card.getCard("5H"), 29));




    }
    public static boolean checkScore(ArrayList<Card> cards, Card cutCard, int expectedScore){
        Hand testHand = new Hand();
        testHand.setHand(cards);
        testHand.setCutCard(cutCard);
        return testHand.count() == expectedScore;
    }
    public static ArrayList<Card> getArrayOfCards(Card[] cards){
        ArrayList<Card> newArray = new ArrayList<Card>();
        for(Card i: cards)
            newArray.add(i);
        return newArray;
    }
}
