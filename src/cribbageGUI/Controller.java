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
        gameComponent.accessCutCard().add(Card.getCard(5));

        gameComponent.accessYourCards().add(Card.getCard(1));
        gameComponent.accessYourCards().add(Card.getCard(2));
        gameComponent.accessYourCards().add(Card.getCard(3));
        gameComponent.accessYourCards().add(Card.getCard(4));
        gameComponent.accessYourCards().add(Card.getCard(5));
        gameComponent.accessYourCards().add(Card.getCard(6));

        gameComponent.accessOpponentCards().add(Card.getCard(1));
        gameComponent.accessOpponentCards().add(Card.getCard(2));
        gameComponent.accessOpponentCards().add(Card.getCard(3));
        gameComponent.accessOpponentCards().add(Card.getCard(4));
        gameComponent.accessOpponentCards().add(Card.getCard(5));
        gameComponent.accessOpponentCards().add(Card.getCard(6));

        gameComponent.accessPeggedCards().add(Card.getCard(23));
        gameComponent.accessPeggedCards().add(Card.getCard(24));
        gameComponent.accessPeggedCards().add(Card.getCard(25));
        gameComponent.accessPeggedCards().add(Card.getCard(26));
        gameComponent.accessPeggedCards().add(Card.getCard(27));
        gameComponent.accessPeggedCards().add(Card.getCard(28));
        gameComponent.accessPeggedCards().add(Card.getCard(29));
        gameComponent.accessPeggedCards().add(Card.getCard(30));

        gameComponent.accessBoard().add(new BoardComponent(new ImageIcon("src/CribbageGUI_Images/2C.png")));

        frame.add(gameComponent);
        frame.repaint();
        game.Game.playGame(this);


    }
}
