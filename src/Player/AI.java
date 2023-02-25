package Player;

public class AI extends Player{
    public AI(){
        super();
    }
    @Override
        public void increaseScore(int scoreIncrease){
        setScore(getScore()+scoreIncrease);
        System.out.println("The Score of the AI is " + getScore());
        if(getScore() > 120) {
            System.out.println("THE AI HAS WON THE GAME");
            System.exit(1);
        }
        //GUI STUFF RIGHT HERE
        //CHECK IF YOU WIN BY GETTING THESE POINTS
    }
}
