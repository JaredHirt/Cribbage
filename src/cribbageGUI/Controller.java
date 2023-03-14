package cribbageGUI;
import javax.swing.*;
import deck.Card;

import java.awt.*;

public class Controller {
    private CribbageFrame frame;
    private GameComponent gameComponent;
    private StartGameButton startGameButton;

    public Controller(CribbageFrame frame, GameComponent gameComponent, StartGameButton startGameButton) {
        this.frame = frame;
        this.gameComponent = gameComponent;
        this.startGameButton = startGameButton;
        frame.setController(this);
        gameComponent.setController(this);
        startGameButton.setController(this);

    }

    public void startTheGame(StartGameButton buttonToRemove){
        frame.remove(buttonToRemove);

        //Turn this game component into the actual game, draw the cribbage board, set the pegs to 0
        GameComponent gameComponent = new GameComponent();

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

        frame.add(gameComponent);
        frame.pack();
        frame.revalidate();
        game.Game.playGame();


    }
}
