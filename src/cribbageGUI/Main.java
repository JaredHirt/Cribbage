package cribbageGUI;

import game.Game;

import javax.swing.*;
import java.awt.*;

public class
Main {
    public static void main(String[] args) throws InterruptedException{
        Game game = new Game();
        SwingUtilities.invokeLater(
                () -> startGUI(game)
            );
        System.out.println("hi");
        game.playGame();

    }

    private static void startGUI(Game game){
        CribbageFrame frame = new CribbageFrame();

        StartGameButton startGameButton = new StartGameButton(new ImageIcon("src/CribbageGUI_Images/Buttons/StartGameButton.png"));
        startGameButton.setBorder(BorderFactory.createEmptyBorder());
        GameComponent gameComponent = new GameComponent();
        Controller controller = new Controller(frame, gameComponent, startGameButton, game);
        frame.add(startGameButton, BorderLayout.CENTER);


    }

}

