package cribbageGUI;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameComponent extends JComponent{
    private ArrayList<Game> myGame;

    public GameComponent(){
        super();
        setLayout(new GridBagLayout());
        myGame = new ArrayList<Game>();
    }

    public void addGame(Game n){
        myGame.add(n);}


    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        for(Game n: myGame)
            n.draw(g2d);
    }



}
