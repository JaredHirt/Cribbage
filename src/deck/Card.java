/**
 *This file is part of a solution to
 * CPSC 101 Group Project Team Frappucino
 * Implements a card class with enum ranks and suits with a cribbage count
 * @author Jared Hirt
 * Student Number: 230154787
 */
package deck;

public class Card implements Comparable<Card>{

    private final Rank rank;
    private final Suit suit;


    /**
     * Constructor creating a card class using enums
     * @param rank = The rank of the card, a 10 of clubs would be a Ten
     * @param suit = What suit the card is, a 10 of clubs would be a Club
     */
    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Gets the rank of the card
     * @return the cards rank
     */
    public Rank getRank(){return rank;}
    /**
     * Gets the suit of the card
     * @return the cards suit
     */
    public Suit getSuit(){return suit;}

    /**
     * Makes a new card with a specific suit and rank
     * @param r the rank of the card
     * @param s the suit of the card
     * @return = A new card with the specified suit and rank
     */
    public static Card getCard(Rank r, Suit s){return new Card(r, s);}
    /**
     * Makes a new card with a specified suit and rank using a number between 0 and 51
     * @param i A number between 0 and 51
     * @return A card calculated with the number specified going through the suits in alphabetical order
     */
    public static Card getCard(int i){
        return new Card(Rank.values()[i%13], Suit.values()[i/13]);
    }
    /**
     * Makes a new card using a string starting with the rank then the suit
     * @param i is a two character string starting with the rank, and then the suit
     */
    public static Card getCard(String i){
        Rank rank = switch(i.charAt(0)) {
            case 'T' -> Rank.Ten;
            case 'J' -> Rank.Jack;
            case 'Q' -> Rank.Queen;
            case 'K' -> Rank.King;
            case 'A' -> Rank.Ace;
            default -> Rank.values()[Integer.parseInt(i.substring(0, 1))-1];
        };
        Suit suit = switch(i.charAt(1)) {
            case 'C' -> Suit.Club;
            case 'D' -> Suit.Diamond;
            case 'H' -> Suit.Heart;
            case 'S' -> Suit.Spade;
            default -> null;
        };
        return getCard(rank, suit);
    }

    /**
     * Returns the name of the card in a 2 character format
     * @return = 2 character card
     */
    public String toString(){
        String card = switch (getRank()) {
            case Ace -> "A";
            case Ten -> "T";
            case Jack -> "J";
            case Queen -> "Q";
            case King -> "K";
            default -> "" + (getRank().ordinal() + 1);
        };

        card += (getSuit().name().charAt(0));
        return card;
    }

    /**
     * Returns the count of the card in cribbage
     * @return Count of card in cribbage
     */
    public int getCribCount(){return rank.count();}




    /**
     * Compares a card to see if the rank is higher
     * @param a the card you want to compare to
     * @return returns a 1 if the original card is higher, a 0 if they are equal, and a -1 if the original card rank is lower
     */
    @Override
    public int compareTo(Card a){
        return Integer.compare(this.rank.ordinal(), a.rank.ordinal());
    }



}
