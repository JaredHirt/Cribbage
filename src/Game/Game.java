package Game;

/**
 * Holds all the game objects and runs the game
 */
import Deck.Deck;
import Deck.Card;
import Player.Player;
import Player.AI;
public class Game {
    public static void main(String[] args) {
        //Setting up the game
        Deck theDeck = new Deck();
        Player player = new Player();
        AI ai = new AI();

        Player pone;
        Player dealer;

        //Finding out who the dealer is
        //Need to make a GUI for this action
        boolean dealerFound = false;
        Card[] cardsForDealer;
        while(!dealerFound){
            cardsForDealer = theDeck.returnUniqueCards(2);
            if(cardsForDealer[0].getRank().ordinal() < cardsForDealer[1].getRank().ordinal()) {
                dealerFound = true;
                dealer = player;
                pone = ai;
            }
            if(cardsForDealer[0].getRank().ordinal() > cardsForDealer[1].getRank().ordinal()){
                dealerFound = true;
                dealer = ai;
                pone = player;
            }
        }

        //Game is now setup, now play can begin
        //while(true) is temporary, replace it later down to make the code more readable.
        while(true){}

    }
}
