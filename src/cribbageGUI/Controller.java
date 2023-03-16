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
        newRound();
    }

    public void repaint(){
        frame.add(gameComponent);
        frame.revalidate();
        frame.repaint();

    }

    public void drawState(){
        gameComponent.accessYourCards().removeAll();
        for(Card c: game.getPlayer().getPeggingCards())
            gameComponent.accessYourCards().add(c);

        gameComponent.accessOpponentCards().removeAll();
        for(Card c: game.getAi().getPeggingCards())
            gameComponent.accessOpponentCards().add(Card.getBlankCard());

        gameComponent.accessPeggedCards().removeAll();
        for(Card c: game.getPeggedCards())
            gameComponent.accessPeggedCards().add(Card.getBlankCard());

        gameComponent.accessCutCard().removeAll();
        gameComponent.accessCutCard().add(cutCard);

        gameComponent.accessBoard().add(new BoardComponent(new ImageIcon("src/CribbageGUI_Images/Buttons/PotentialCribBoard.png")));
        gameComponent.accessInfo().add(new ScoreComponent());

        repaint();

    }

    public void newRound(){
        game.deal();
        cutCard = Card.getBlankCard();
        drawState();
        for(Card c:game.getPlayer().getPeggingCards())
            c.addActionListener(ae-> {
                Card card = (Card)ae.getSource();
                game.getPlayer().getHand().getHand().remove(card);
                game.getPlayer().getPeggingCards().remove(card);
                game.getPlayer().getTheCrib().getHand().add(card);
                c.removeAllActionListeners();
                drawState();
                    });
    }
}

