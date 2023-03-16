package cribbageGUI;

import player.AI;
import player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * A custom Swing component that displays the scores of the player and computer in the Cribbage game.
 */
public class ScoreComponent extends JComponent {
    private JLabel playerScore;
    private JLabel computerScore;

    /**
     * Constructs a ScoreComponent with the initial scores of the player and computer.
     *
     * @param playerPoints the initial score of the player.
     * @param aiPoints the initial score of the computer.
     */
    public ScoreComponent(int playerPoints, int aiPoints, Player dealer){
        super();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createRaisedBevelBorder());

        setFont(new Font("MENLO", Font.BOLD, 24));
        playerScore = new JLabel();
        computerScore = new JLabel();
        playerScore.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerScore.setFont(new Font("MENLO", Font.BOLD, 24));
        computerScore.setFont(new Font("MENLO", Font.BOLD, 24));
        playerScore.setOpaque(true);
        computerScore.setOpaque(true);
        playerScore.setBackground(Color.LIGHT_GRAY);
        computerScore.setBackground(Color.LIGHT_GRAY);
        playerScore.setForeground(Color.BLUE);
        computerScore.setForeground(Color.RED);

        setPlayerScore(playerPoints);
        setComputerScore(aiPoints);



        if(dealer instanceof AI)
            playerScore.setBackground(Color.WHITE);
        else
            computerScore.setBackground(Color.WHITE);

        add(playerScore, BorderLayout.SOUTH);
        add(computerScore, BorderLayout.NORTH);
    }

    /**
     * Sets the score of the player and updates the label that displays the score.
     *
     * @param score the new score of the player.
     */
    public void setPlayerScore(int score){
        playerScore.setText("Player: " + score);
    }

    /**
     * Sets the score of the computer and updates the label that displays the score.
     *
     * @param score the new score of the computer.
     */
    public void setComputerScore(int score){
        computerScore.setText("Computer: " + score);
    }
}
