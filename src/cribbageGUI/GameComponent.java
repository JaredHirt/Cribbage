package cribbageGUI;


import deck.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**

 * This class represents the graphical component of the Cribbage game.
 * It extends JComponent and contains various panels for displaying game elements.
 */
public class GameComponent extends JComponent{
    private Controller controller;
    private CardPanel opponentCards;
    private CardPanel peggedCards;
    private CardPanel yourCards;
    private JPanel cribBoard;
    private CardPanel cutCard;
    private ScoreComponent info;

    /**
     * Constructor for the GameComponent class. Initializes all the necessary components and adds them to the layout.
     */
    public GameComponent(){
        super();
        setLayout(new GridBagLayout());

        opponentCards = new CardPanel();
        peggedCards = new CardPanel();
        yourCards = new CardPanel();
        cribBoard = new JPanel();
        cutCard = new CardPanel();
        info = new ScoreComponent(0, 0);

        cutCard.setLayout(new BorderLayout());
        cribBoard.setLayout(new BorderLayout());
        info.setLayout(new BorderLayout());
        info.setBackground(Color.ORANGE);
        setOpaque(true);

        GridBagConstraints oppCards = new GridBagConstraints();
        oppCards.gridx = 0;
        oppCards.gridy = 0;
        oppCards.gridwidth = 3;
        oppCards.gridheight = 1;
        oppCards.weightx = 0.5;
        oppCards.weighty = 0.5;
        add(opponentCards, oppCards);

        GridBagConstraints pegCards = new GridBagConstraints();
        pegCards.gridx = 0;
        pegCards.gridy = 1;
        pegCards.gridwidth = 3;
        pegCards.gridheight = 1;
        pegCards.weightx = 0.5;
        add(peggedCards, pegCards);

        GridBagConstraints playerCards = new GridBagConstraints();
        playerCards.gridx = 0;
        playerCards.gridy = 2;
        playerCards.gridwidth = 3;
        playerCards.gridheight = 1;
        playerCards.weightx = 0.5;
        playerCards.weighty = 0.5;
        add(yourCards, playerCards);

        GridBagConstraints cardCut = new GridBagConstraints();
        cardCut.gridx = 3;
        cardCut.gridy = 1;
        cardCut.gridwidth = 1;
        cardCut.gridheight = 1;
        cardCut.weightx = 0;
        cardCut.weighty = 0;
        add(cutCard, cardCut);

        GridBagConstraints board = new GridBagConstraints();
        board.gridx = 4;
        board.gridy = 0;
        board.gridwidth = 1;
        board.gridheight = 3;
        playerCards.weightx = 0.5;
        playerCards.weighty = 1;
        add(cribBoard, board);

        GridBagConstraints infoPane = new GridBagConstraints();
        infoPane.gridx = 3;
        infoPane.gridy = 2;
        infoPane.gridwidth = 1;
        infoPane.gridheight = 1;

        add(info, infoPane);

    }

    /**
     * Sets the controller for the GameComponent.
     * @param control The controller to set.
     */
    public void setController(Controller control){controller = control;}


    /**
     * @return The opponent's card panel.
     */
    public CardPanel accessOpponentCards(){return opponentCards;}

    /**
     * @return The pegged card panel.
     */
    public CardPanel accessPeggedCards(){return peggedCards;}

    /**
     * @return the panel containing the user's cards
     */
    public CardPanel accessYourCards(){return yourCards;}

    /**
     * @return the component containing the cribbage board
     */
    public JComponent accessBoard(){return cribBoard;}

    /**
     * @return the panel containing the cut card
     */
    public CardPanel accessCutCard(){return cutCard;}

    /**
     * @return the component containing the score information
     */
    public ScoreComponent accessInfo(){return info;}

}
