package cribbageGUI;
import javax.swing.*;
import deck.Card;


public class Controller {
    private CribbageFrame frame;
    private GameComponent gameComponent;
    private StartGameButton startGameButton;
    private game.Game game;
    private Card cutCard;

    public Controller(CribbageFrame frame, GameComponent gameComponent, StartGameButton startGameButton, game.Game game) {
        this.frame = frame;
        this.gameComponent = gameComponent;
        this.startGameButton = startGameButton;
        this.game = game;
        frame.setController(this);
        gameComponent.setController(this);
        startGameButton.setController(this);
        game.setController(this);

    }

    public void startTheGame(StartGameButton buttonToRemove){
        frame.remove(buttonToRemove);
        //Turn this game component into the actual game, draw the cribbage board, set the pegs to 0
        GameComponent gameComponent = new GameComponent();
        game.deal();
        cutCard = Card.getBlankCard();
        gameComponent.accessBoard().add(new BoardComponent(new ImageIcon("src/CribbageGUI_Images/Buttons/PotentialCribBoard.png")));
        gameComponent.accessInfo().add(new ScoreComponent());

        frame.add(gameComponent);

        drawState();
        drawState();
        frame.revalidate();
    }

    public void repaint(){
        frame.revalidate();
        frame.repaint();
        frame.add(gameComponent);
        frame.pack();

    }

    public void drawState(){
        gameComponent.accessYourCards().removeAll();
        for(Card c: game.getPlayer().getPeggingCards())
            gameComponent.accessYourCards().add(c);

        gameComponent.accessOpponentCards().removeAll();
        for(Card c: game.getAi().getPeggingCards())
            gameComponent.accessOpponentCards().add(Card.getBlankCard());

        gameComponent.accessPeggedCards().removeAll();
        for(Card c: game.getPlayer().getPeggingCards())
            gameComponent.accessPeggedCards().add(Card.getBlankCard());

        gameComponent.accessCutCard().removeAll();
        gameComponent.accessCutCard().add(cutCard);

        repaint();

    }
}

