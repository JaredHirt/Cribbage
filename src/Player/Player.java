package Player;
import Hand.Hand;
import Hand.Crib;
public class Player {
    private int score;
    private boolean dealer;
    private Hand myHand;
    public static Crib theCrib = new Crib();

    /**
     * Player constructor
     */
    public Player(){
        myHand = new Hand();
    }

}
