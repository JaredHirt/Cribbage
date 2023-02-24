package Testing;
import Deck.Card;
import Hand.Hand;


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



    }
    public static boolean checkScore(ArrayList<Card> cards, Card cutCard, int expectedScore){
        Hand testHand = new Hand();
        testHand.setHand(cards);
        testHand.setCutCard(cutCard);
        System.out.println(testHand.count());
        return testHand.count() == expectedScore;
    }
    public static ArrayList<Card> getArrayOfCards(Card[] cards){
        return new ArrayList<>(Arrays.asList(cards));
    }
}
