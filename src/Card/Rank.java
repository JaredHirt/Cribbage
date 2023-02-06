/**
 2 * This file is part of a solution to
 3 * CPSC 101 Lab 4 Winter 2023
 4 *
 5 * Implements a Rank enum for the card class
 7 * @author Jared Hirt
 8 * Student Number: 230154787
 9 * @version 1
 10 */
package Card;

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
