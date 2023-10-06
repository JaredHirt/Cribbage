package main.java.deck;

import main.java.cribbageGUI.CardButton;


public interface CardInterface extends Comparable<CardInterface> {
    Suit getSuit();
    Rank getRank();
    CardButton getCardButton();
    @Override
    int compareTo(CardInterface a);
    int getCribCount();

}

