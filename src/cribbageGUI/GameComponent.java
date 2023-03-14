package cribbageGUI;


import deck.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameComponent extends JComponent{
    private Controller controller;
    private JPanel opponentCards;
    private JPanel peggedCards;
    private JPanel yourCards;
    private JComponent cribBoard;
    private JComponent cutCard;
    private JComponent info;

    public GameComponent(){
        super();
        setLayout(new GridBagLayout());

        opponentCards = new JPanel();
        peggedCards = new JPanel();
        yourCards = new JPanel();
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
        add(opponentCards, oppCards);

        GridBagConstraints pegCards = new GridBagConstraints();
        pegCards.gridx = 0;
        pegCards.gridy = 1;
        pegCards.gridwidth = 3;
        pegCards.gridheight = 1;
        pegCards.fill = GridBagConstraints.BOTH;
        add(peggedCards, pegCards);

        GridBagConstraints playerCards = new GridBagConstraints();
        playerCards.gridx = 0;
        playerCards.gridy = 2;
        playerCards.gridwidth = 3;
        playerCards.gridheight = 1;
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
    public JPanel accessOpponentCards(){return opponentCards;}
    public JPanel accessPeggedCards(){return peggedCards;}
    public JPanel accessYourCards(){return yourCards;}
    public JComponent accessBoard(){return cribBoard;}
    public JComponent accessCutCard(){return cutCard;}
    public JComponent accessInfo(){return info;}

}
