package cribbageGUI;


import deck.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameComponent extends JComponent{
    private Controller controller;
    private CardPanel opponentCards;
    private CardPanel peggedCards;
    private CardPanel yourCards;
    private JPanel cribBoard;
    private CardPanel cutCard;
    private ScoreComponent info;

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
        info.setBackground(Color.GREEN);
        setOpaque(true);

        GridBagConstraints oppCards = new GridBagConstraints();
        oppCards.gridx = 1;
        oppCards.gridy = 0;
        oppCards.gridwidth = 3;
        oppCards.gridheight = 1;
        oppCards.weightx = 0.5;
        oppCards.weighty = 0.5;
        add(opponentCards, oppCards);

        GridBagConstraints pegCards = new GridBagConstraints();
        pegCards.gridx = 1;
        pegCards.gridy = 1;
        pegCards.gridwidth = 3;
        pegCards.gridheight = 1;
        pegCards.weightx = 0.5;
        add(peggedCards, pegCards);

        GridBagConstraints playerCards = new GridBagConstraints();
        playerCards.gridx = 1;
        playerCards.gridy = 2;
        playerCards.gridwidth = 3;
        playerCards.gridheight = 1;
        playerCards.weightx = 0.5;
        playerCards.weighty = 0.5;
        add(yourCards, playerCards);

        GridBagConstraints cardCut = new GridBagConstraints();
        cardCut.gridx = 4;
        cardCut.gridy = 2;
        cardCut.gridwidth = 1;
        cardCut.gridheight = 1;
        cardCut.weightx = 0;
        add(cutCard, cardCut);

        GridBagConstraints board = new GridBagConstraints();
        board.gridx = 4;
        board.gridy = 0;
        board.gridwidth = 1;
        board.gridheight = 2;
        playerCards.weightx = 0.5;
        playerCards.weighty = 1;
        add(cribBoard, board);

        GridBagConstraints infoPane = new GridBagConstraints();
        infoPane.gridx = 4;
        infoPane.gridy = 3;
        infoPane.gridwidth = 1;
        infoPane.gridheight = 1;
        infoPane.anchor = GridBagConstraints.NORTHWEST;
        add(info, infoPane);


    }
    public void setController(Controller control){controller = control;}
    public CardPanel accessOpponentCards(){return opponentCards;}
    public CardPanel accessPeggedCards(){return peggedCards;}
    public CardPanel accessYourCards(){return yourCards;}
    public JComponent accessBoard(){return cribBoard;}
    public CardPanel accessCutCard(){return cutCard;}
    public ScoreComponent accessInfo(){return info;}


}
