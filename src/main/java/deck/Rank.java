/**
 *This file is part of a solution to
 * CPSC 101 Group Project Team Frappucino
 * Implements a Rank enum for the Card class
 * also includes a count() method which gets the cribbage value of each of the ranks
 * @author Jared Hirt
 * Student Number: 230154787
 */
package deck;

public enum Rank {
    Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King ;

    /**
     * Returns the value of the card
     * @return = the value of the card
     */
    public int count() {
        return Math.min(ordinal() + 1, 10);
    }
}
