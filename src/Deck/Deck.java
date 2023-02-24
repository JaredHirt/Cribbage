package Deck;
import java.util.ArrayList;
public class Deck {
    ArrayList<Card> theCards;


    public Deck(){
        theCards = new ArrayList<>();
        for(int i = 0; i < 52; i++)
            theCards.add(Card.getCard(i));
    }

    /**
     * Returns a set amount of unique cards
     */
    public Card[] returnUniqueCards(){

    }

}
