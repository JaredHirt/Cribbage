package cribbageGUI;

import deck.Card;

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

        GridBagConstraints board = new GridBagConstraints();
        board.gridx = 0;
        board.gridy = 0;
        board.gridwidth = 1;
        board.gridheight = 0;
        board.fill = GridBagConstraints.BOTH;
        gameComponent.add(Card.getCard(7), board);

        GridBagConstraints stats = new GridBagConstraints();
        stats.gridx = 1;
        stats.gridy = 0;
        stats.gridwidth = 1;
        stats.gridheight = 1;
        stats.fill = GridBagConstraints.BOTH;
        gameComponent.add(Card.getCard(2), stats);

        this.add(gameComponent);
        pack();
        revalidate();
        //SwingUtilities.invokeLater( () -> {game.Game.playGame();});




    }

}
