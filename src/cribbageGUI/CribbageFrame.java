package cribbageGUI;

import javax.swing.*;
import java.awt.*;

public class CribbageFrame extends JFrame {
    public CribbageFrame(){
        super("Team Frappuccino Cribbage");
        setSize(800, 800);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void startTheGame(StartGameButton buttonToRemove){
        remove(buttonToRemove);

        //Turn this game component into the actual game, draw the cribbage board, set the pegs to 0
        GameComponent gameComponent = new GameComponent();
        Game rectangle = new Game(new Point(50, 50), new Point(100, 100));
        gameComponent.addGame(rectangle);
        this.add(gameComponent);
        revalidate();
        SwingUtilities.invokeLater( () -> {game.Game.playGame();});




    }

}
