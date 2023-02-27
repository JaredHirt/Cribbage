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
        StringBuilder set;
        for(int i=0; i < Math.pow(2, cards.size()); i++){
            setCount = 0;
            set = new StringBuilder(Integer.toBinaryString(i));
            //Makes the binary number the length of the amount of cards by padding it with 0's
            while(set.length() < cards.size())
                set.insert(0, "0");
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
     * Returns the point from the knob if applicable
     * @param cards the cards in the hand
     * @param  cutCard the cut card
     * @return the point for the knob if applicable
     */
    public static int knob(ArrayList<Card> cards, Card cutCard){
        for(Card i:cards)
            if(i.getSuit() == cutCard.getSuit() && i.getRank() == Rank.Jack)
                return 1;
        return 0;
    }

    /**
     * Returns the points from runs when counting hands
     * @param cards the cards you want to check for runs
     * @return the amount of points from runs
     */
    public static int runs(ArrayList<Card> cards){
        int count = 0;
        StringBuilder set;
        ArrayList<Card> setCard = new ArrayList<>();
        for(int i=0; i < Math.pow(2, cards.size()); i++){
            setCard.clear();
            set = new StringBuilder(Integer.toBinaryString(i));
            //Makes the binary number the length of the amount of cards by padding it with 0's
            while(set.length() < cards.size())
                set.insert(0, "0");
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
                        } else if (setCard.size() == 4) {
                            count -= 2;
                        }
                }
        }
        return count;
    }

    /**
     * Returns the amount of points gotten from pegging
     * @param peggedCards the cards that have been pegged thus far
     * @return the points gotten from pegging
     */
    public static int pointsPegging(ArrayList<Card> peggedCards){
        if(peggedCards.size() == 1)
            return 0;
        return points15Pegging(peggedCards) + points31Pegging(peggedCards) + pointsPairsPegging(peggedCards) + pointsRunsPegging(peggedCards);
    }

    /**
     * Returns the points for 15's from pegging
     * @param peggedCards the cards that have been pegged thus far
     * @return the points from 15's for pegging
     */
    public static int points15Pegging(ArrayList<Card> peggedCards){
        int count = 0;
        for(Card i: peggedCards)
            count += i.getRank().count();
        if(count==15)
            return 2;
        return 0;
    }

    /**
     * Checks for if the pegging has reached 31
     * @param peggedCards the cards that have been pegged thus far
     * @return a point if the player has reached 31 points (they will an additional point when the pegging resets)
     */
    public static int points31Pegging(ArrayList<Card> peggedCards){
        int count = 0;
        for(Card i: peggedCards)
            count += i.getRank().count();
        if(count==31)
            return 1;
        return 0;
    }

    /**
     * The pegging points for pairs thus far
     * @param peggedCards the cards that have been pegged thus far
     * @return the pegging points for pairs
     */
    public static int pointsPairsPegging(ArrayList<Card> peggedCards){
        int count = 0;
        if(peggedCards.size() < 2)
            return count;
        if(peggedCards.get(peggedCards.size()-1).getRank() == peggedCards.get(peggedCards.size()-2).getRank())
            count = 2;
        if(peggedCards.size() < 3)
            return count;
        if((peggedCards.get(peggedCards.size()-1).getRank() == peggedCards.get(peggedCards.size()-2).getRank()) && (peggedCards.get(peggedCards.size()-1).getRank() == peggedCards.get(peggedCards.size()-3).getRank()))
            count = 6;
        if(peggedCards.size() < 4)
            return count;
        if((peggedCards.get(peggedCards.size()-1).getRank() == peggedCards.get(peggedCards.size()-2).getRank()) && (peggedCards.get(peggedCards.size()-1).getRank() == peggedCards.get(peggedCards.size()-3).getRank()) && peggedCards.get(peggedCards.size()-1) == peggedCards.get(peggedCards.size()-4))
            count = 12;
        return count;
    }

    /**
     * Counts the pegging points for runs
     * @param peggedCards the cards that have been pegged thus far
     * @return the pegging points for runs
     */
    public static int pointsRunsPegging(ArrayList<Card> peggedCards){
        int highestRun = 0;
        if(peggedCards.size() < 3)
            return highestRun;
        ArrayList<Card> peggedCardsEditable;
        for(int i = peggedCards.size()-3; i >= 0; i--) {
            peggedCardsEditable = new ArrayList<>(peggedCards.subList(i, peggedCards.size()));
            Collections.sort(peggedCardsEditable);
            for (int j = 1; j < peggedCardsEditable.size(); j++) {
                if (peggedCardsEditable.get(j).getRank().ordinal() != peggedCardsEditable.get(j - 1).getRank().ordinal() + 1)
                    break;
                if (j == (peggedCardsEditable.size() - 1))
                    highestRun = peggedCardsEditable.size();
            }
        }
        return highestRun;
    }


}
