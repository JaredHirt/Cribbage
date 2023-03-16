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
    private JPanel cutCard;
    private JPanel info;

    public GameComponent(){
        super();
        setLayout(new GridBagLayout());

        opponentCards = new CardPanel();
        peggedCards = new CardPanel();
        yourCards = new CardPanel();
        cribBoard = new JPanel();
        cutCard = new JPanel();
        info = new JPanel();

        cutCard.setLayout(new BorderLayout());
        cribBoard.setLayout(new BorderLayout());
        info.setLayout(new BorderLayout());


        GridBagConstraints oppCards = new GridBagConstraints();
        oppCards.gridx = 0;
        oppCards.gridy = 0;
        oppCards.gridwidth = 3;
        oppCards.gridheight = 1;
        oppCards.fill = GridBagConstraints.BOTH;
        oppCards.weighty = 1;
        add(opponentCards, oppCards);

        GridBagConstraints pegCards = new GridBagConstraints();
        pegCards.gridx = 0;
        pegCards.gridy = 1;
        pegCards.gridwidth = 3;
        pegCards.gridheight = 1;
        pegCards.weighty = 0.5;
        pegCards.fill = GridBagConstraints.BOTH;
        add(peggedCards, pegCards);

        GridBagConstraints playerCards = new GridBagConstraints();
        playerCards.gridx = 0;
        playerCards.gridy = 2;
        playerCards.gridwidth = 3;
        playerCards.gridheight = 1;
        playerCards.weighty = 0;
        playerCards.fill = GridBagConstraints.BOTH;
        add(yourCards, playerCards);

        GridBagConstraints cardCut = new GridBagConstraints();
        cardCut.gridx = 3;
        cardCut.gridy = 1;
        cardCut.gridwidth = 1;
        cardCut.gridheight = 1;
        cardCut.fill = GridBagConstraints.BOTH;
        add(cutCard, cardCut);

        GridBagConstraints board = new GridBagConstraints();
        board.gridx = 4;
        board.gridy = 0;
        board.gridwidth = 1;
        board.gridheight = 2;
        board.fill = GridBagConstraints.BOTH;
        add(cribBoard, board);

        GridBagConstraints infoPane = new GridBagConstraints();
        infoPane.gridx = 4;
        infoPane.gridy = 2;
        infoPane.gridwidth = 1;
        infoPane.gridheight = 1;
        infoPane.fill = GridBagConstraints.BOTH;
        add(info, infoPane);


    }
    public void setController(Controller control){controller = control;}
    public CardPanel accessOpponentCards(){return opponentCards;}
    public CardPanel accessPeggedCards(){return peggedCards;}
    public CardPanel accessYourCards(){return yourCards;}
    public JComponent accessBoard(){return cribBoard;}
    public JComponent accessCutCard(){return cutCard;}
    public JComponent accessInfo(){return info;}

    public void addCardToYourHand(Card c){
        yourCards.add(c);
    }

}
