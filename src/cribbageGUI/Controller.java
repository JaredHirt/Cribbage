package cribbageGUI;
import javax.swing.*;
import deck.Card;
import deck.Rank;


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
            gameComponent.accessPeggedCards().add(c);

        gameComponent.accessCutCard().removeAll();
        gameComponent.accessCutCard().add(cutCard);

        gameComponent.accessBoard().add(new BoardComponent(new ImageIcon("src/CribbageGUI_Images/Buttons/PotentialCribBoard.png")));
        gameComponent.accessInfo().removeAll();
        gameComponent.accessInfo().add(new ScoreComponent(game.getPlayer().getScore(), game.getAi().getScore()));

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
                if(game.getPlayer().getPeggingCards().size() == 4){
                    for(Card ca : game.getPlayer().getPeggingCards())
                        ca.removeAllActionListeners();
                    game.getAi().discard();
                    newPeggingRound();
                }
                    });
    }
    public void newPeggingRound(){
        cutCard = game.getPlayer().getCutCard();
        //Gives two points to dealer for "his knees"
        if(cutCard.getRank() == Rank.Jack)
            game.getDealer().increaseScore(2);
        drawState();
        game.getPone().playCard(game.getPeggedCards());
        drawState();
    }

    public void passPeggingToAI(){
        if(game.getAi().canPlayCard(game.getPeggedCards()))
            game.getAi().playCard(game.getPeggedCards());
        else if(game.getPlayer().canPlayCard(game.getPeggedCards()))
            game.getPlayer().playCard(game.getPeggedCards());
        else if(!game.getPlayer().canPlayCard(game.getPeggedCards()) && !game.getAi().canPlayCard(game.getPeggedCards())){
            System.out.println("Round reset");
            game.getPlayer().increaseScore(1);
            game.getPeggedCards().clear();
            drawState();
            if(game.getPlayer().getPeggingCards().size() == 0 && game.getAi().getPeggingCards().size() == 0)
                peggingDone();
            else game.getAi().playCard(game.getPeggedCards());
        }

    }

    public void passPeggingtoPlayer() {
        if (game.getPlayer().canPlayCard(game.getPeggedCards()))
            game.getPlayer().playCard(game.getPeggedCards());
        else if (game.getAi().canPlayCard(game.getPeggedCards()))
            game.getAi().playCard(game.getPeggedCards());
        else if (!game.getAi().canPlayCard(game.getPeggedCards()) && !game.getPlayer().canPlayCard(game.getPeggedCards())) {
            System.out.println("Round reset");
            game.getAi().increaseScore(1);
            game.getPeggedCards().clear();
            drawState();
            if(game.getPlayer().getPeggingCards().size() == 0 && game.getAi().getPeggingCards().size() == 0)
                peggingDone();
            else game.getPlayer().playCard(game.getPeggedCards());
        }

    }
    public void peggingDone(){
        game.getPone().countHand();
        game.getDealer().countHand();
        game.getDealer().countCrib();
        game.swapDealer();
        newRound();
    }
}

