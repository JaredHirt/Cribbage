package Game;

import Deck.Card;
import Deck.Rank;
import java.util.Collections;

import java.util.ArrayList;

public class Counting {


    /**
     *
     * @param cards The hand you would like to count
     * @return The amount of 15's you get
     */
    public static int points15(ArrayList<Card> cards){
        int count = 0;
        int setCount;
        String set;
        for(int i=0; i < Math.pow(2, cards.size()); i++){
            setCount = 0;
            set = Integer.toBinaryString(i);
            //Makes the binary number the length of the amount of cards by padding it with 0's
            while(set.length() < cards.size())
                set = "0" + set;
            for(int j = 0; j < cards.size(); j++)
                if(set.charAt(j) == '1')
                    setCount += cards.get(j).getCribCount();
            if(setCount == 15)
                count +=2;
        }
        return count;
    }

    /**
     * Gets the amount of points from pairs
     * @param cards the ArrayList which makes up the hand
     * @return returns the total amount of points from pairs
     */
    public static int pointsPair(ArrayList<Card> cards){
        int count = 0;
        for(int i = 0; i < cards.size(); i++)
            for(int j = i+1; j < cards.size(); j++){
                if(cards.get(i).getRank() == cards.get(j).getRank())
                    count+=2;
            }
        return count;
    }

    /**
     * Gets the points for a flush
     * @param cards the cards you want to check for the flush
     * @return the amount of points received for the flush
     */
    public static int flush(ArrayList<Card> cards){
        for(Card card:cards)
            if(cards.get(0).getSuit() != card.getSuit())
                return 0;
        return cards.size();
    }

    /**
     * Calculates the score for a knob
     * @param cards the hand of cards excluding the cut card
     * @param cutCard the card which is cut
     * @return the point value for knobs in this hand
     */

    public static int knob(ArrayList<Card> cards, Card cutCard){
        for(Card i:cards)
            if(i.getSuit() == cutCard.getSuit() && i.getRank() == Rank.Jack)
                return 1;
        return 0;
    }
    public static int runs(ArrayList<Card> cards){
        int count = 0;
        String set;
        ArrayList<Card> setCard = new ArrayList<Card>();
        for(int i=0; i < Math.pow(2, cards.size()); i++){
            setCard.clear();
            set = Integer.toBinaryString(i);
            //Makes the binary number the length of the amount of cards by padding it with 0's
            while(set.length() < cards.size())
                set = "0" + set;
            //Gets the subset of card
            for(int j = 0; j < set.length(); j++)
                if(set.charAt(j) == '1')
                    setCard.add(cards.get(j));
            Collections.sort(setCard);
            if(setCard.size() >= 3)
                for(int j = 1; j < setCard.size(); j++) {
                    if (setCard.get(j).getRank().ordinal() != setCard.get(j - 1).getRank().ordinal() + 1)
                        break;
                    if(j == (setCard.size()-1))
                        if(setCard.size() == 3) {
                            count += 3;
                        }
                        else count++;
                }
        }
        return count;
    }
}
