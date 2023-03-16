package cribbageGUI;

import javax.swing.*;
import java.awt.*;

public class ScoreComponent extends JComponent {
    private JLabel playerScore;
    private JLabel computerScore;
    public ScoreComponent(){
        super();
        setLayout(new BorderLayout());
        playerScore = new JLabel();
        computerScore = new JLabel();

        setPlayerScore(0);
        setComputerScore(0);

        add(playerScore, BorderLayout.NORTH);
        add(computerScore, BorderLayout.SOUTH);
    }
    public void setPlayerScore(int score){
        playerScore.setText("Player: " + score);
    }
    public void setComputerScore(int score){
        computerScore.setText("Computer: " + score);
    }
}