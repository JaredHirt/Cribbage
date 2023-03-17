package cribbageGUI;

import javax.swing.*;
import deck.Card;
import deck.Rank;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * The controller class for the Cribbage game.
 */
public class Controller {
    private final CribbageFrame frame;
    private final GameComponent gameComponent;
    private final StartGameButton startGameButton;
    private final game.Game game;
    private Card cutCard;

    /**
     * Creates a new instance of the Controller class.
     *
     * @param frame the main frame of the Cribbage game
     * @param gameComponent the component that displays the game state
     * @param startGameButton the button used to start the game
     * @param game the Cribbage game instance
     */
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

    /**
     * Starts the game by removing the start game button and drawing the game state.
     *
     * @param buttonToRemove the button to remove
     */
    public void startTheGame(StartGameButton buttonToRemove){
        frame.remove(buttonToRemove);
        //Turn this game component into the actual game, draw the cribbage board, set the pegs to 0
        GameComponent gameComponent = new GameComponent();
        newRound();
    }

    public void restartGame(RestartGameButton buttonToRemove){
        frame.remove(buttonToRemove);
        frame.add(gameComponent);
        repaint();
        game.getAi().setScore(0);
        game.getPlayer().setScore(0);
        game.getDealer();
        newRound();
    }

    /**
     * Repaints the game state.
     */
    public void repaint(){
        frame.add(gameComponent);
        frame.revalidate();
        frame.repaint();

    }

    /**
     * Draws the game state.
     */
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

        gameComponent.accessBoard().add(new BoardComponent(new ImageIcon(getClass().getClassLoader().getResource("CribbageGUI_Images/Buttons/PotentialCribBoard.png"))));
        gameComponent.accessInfo().removeAll();
        gameComponent.accessInfo().add(new ScoreComponent(game.getPlayer().getScore(), game.getAi().getScore(), game.getDealer()));

        repaint();

    }

    public void drawHandCounting(){
        gameComponent.accessCutCard().removeAll();
        gameComponent.accessCutCard().add(cutCard);

        gameComponent.accessBoard().add(new BoardComponent(new ImageIcon(getClass().getClassLoader().getResource("CribbageGUI_Images/Buttons/PotentialCribBoard.png"))));
        gameComponent.accessInfo().removeAll();
        gameComponent.accessInfo().add(new ScoreComponent(game.getPlayer().getScore(), game.getAi().getScore(), game.getDealer()));
        repaint();
    }

    /**
     * Starts a new round by dealing the cards and drawing the game state.
     */
    public void newRound(){
        game.deal();
        cutCard = Card.getBlankCard();
        AIDiscard dis= new AIDiscard();
        dis.execute();
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
                    try {
                        dis.get();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                    newPeggingRound();
                }
                    });
    }

    /**

     * Starts a new Pegging round by setting the cut card, giving 2 points to the dealer if the cut card is a Jack,
     * and calling the playCard method for the human player.
     * It then calls the drawState method to update the game's UI.
     */
    public void newPeggingRound(){
        cutCard = game.getPlayer().getCutCard();
        //Gives two points to dealer for "his knees"
        if(cutCard.getRank() == Rank.Jack)
            game.getDealer().increaseScore(2);
        drawState();
        game.getPone().playCard(game.getPeggedCards());
        drawState();
    }

    /**

     * Passes the turn to the AI player if it can play a card, otherwise passes the turn to the human player
     * if it can play a card. If neither player can play a card, the round is reset, 1 point is given to the AI player,
     * the pegged cards are cleared, and the AI player plays a card.
     * It then calls the drawState method to update the game's UI.
     */
    public void passPeggingToAI(){
        if(game.getAi().canPlayCard(game.getPeggedCards()))
            game.getAi().playCard(game.getPeggedCards());
        else if(game.getPlayer().canPlayCard(game.getPeggedCards()))
            game.getPlayer().playCard(game.getPeggedCards());
        else if(!game.getPlayer().canPlayCard(game.getPeggedCards()) && !game.getAi().canPlayCard(game.getPeggedCards())){
            System.out.println("Round reset");
            game.getPlayer().increaseScore(1);
            //Making JButton that can clear the board
            JButton c = new JButton("Click to Clear");
            c.addActionListener(ae -> {
                game.getPeggedCards().clear();
                    drawState();
                    if(game.getPlayer().getPeggingCards().size() == 0 && game.getAi().getPeggingCards().size() == 0)
                        peggingDone();
                    else game.getAi().playCard(game.getPeggedCards());
            });
            gameComponent.accessPeggedCards().add(c);
            drawHandCounting();

        }
    }


    /**

     * Passes the turn to the human player if it can play a card, otherwise passes the turn to the AI player
     * if it can play a card. If neither player can play a card, the round is reset, 1 point is given to the AI player,
     * the pegged cards are cleared, and the human player plays a card.
     * It then calls the drawState method to update the game's UI.
     */
    public void passPeggingtoPlayer() {
        if (game.getPlayer().canPlayCard(game.getPeggedCards()))
            game.getPlayer().playCard(game.getPeggedCards());
        else if (game.getAi().canPlayCard(game.getPeggedCards()))
            game.getAi().playCard(game.getPeggedCards());
        else if (!game.getAi().canPlayCard(game.getPeggedCards()) && !game.getPlayer().canPlayCard(game.getPeggedCards())) {
            System.out.println("Round reset");
            game.getAi().increaseScore(1);
            //Making JButton that can clear the board
            JButton c = new JButton("Click to Clear");
            c.addActionListener(ae -> {
                game.getPeggedCards().clear();
                    drawState();
                    if(game.getPlayer().getPeggingCards().size() == 0 && game.getAi().getPeggingCards().size() == 0)
                        peggingDone();
                    else game.getPlayer().playCard(game.getPeggedCards());
            });
            gameComponent.accessPeggedCards().add(c);
            drawHandCounting();
        }

    }

    /**

     * Method called when the pegging phase is finished, and it's time to count the points and start a new round.
     * This method counts the hands of both the Pone and the Dealer, as well as the Dealer's crib.
     * It then swaps the dealer and starts a new round.
     */
    public void peggingDone(){
        showCountingOfHands();
    }


    public void showCountingOfHands(){
        gameComponent.accessPeggedCards().removeAll();
        game.getPone().countHand();
        for(Card c: game.getPone().getHand().getHand()) {
            c.addActionListener(ae->
            {
                for(Card c1: game.getPone().getHand().getHand())
                    c1.removeAllActionListeners();
                showCountingOfDealer();
            });
            gameComponent.accessPeggedCards().add(c);
            drawHandCounting();
        }
    }

    public void showCountingOfDealer(){
        gameComponent.accessPeggedCards().removeAll();
        game.getDealer().countHand();
        for(Card c: game.getDealer().getHand().getHand()) {
            c.addActionListener(ae->
            {
                for(Card c1: game.getDealer().getHand().getHand())
                    c1.removeAllActionListeners();
                showCountingOfCrib();
            });
            gameComponent.accessPeggedCards().add(c);
            drawHandCounting();
        }
    }

    public void showCountingOfCrib(){
        gameComponent.accessPeggedCards().removeAll();
        game.getDealer().countCrib();
        for(Card c: game.getDealer().getTheCrib().getHand()) {
            c.addActionListener(ae->
            {
                for(Card c1: game.getDealer().getTheCrib().getHand())
                    c1.removeAllActionListeners();
                game.swapDealer();
                newRound();
            });
            gameComponent.accessPeggedCards().add(c);
            drawHandCounting();
        }
    }



    public void restart(){
        RestartGameButton restartGameButton = new RestartGameButton();
        restartGameButton.setController(this);
        frame.remove(gameComponent);
        frame.add(restartGameButton, BorderLayout.SOUTH);
    }

    private class AIDiscard extends SwingWorker{
        @Override
        public Object doInBackground(){
            game.getAi().discard();
            return null;
        }
        @Override
        public void done(){
            drawState();
        }
    }
}

