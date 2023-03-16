package cribbageGUI;

import game.Game;

import javax.swing.*;
import java.awt.*;

/**
 * This class is responsible for starting the Cribbage game GUI.
 */
public class Main {

    /**
     * This method is the entry point of the Cribbage game GUI.
     * It initializes the GUI components and starts the event dispatch thread.
     *
     * @param args the command line arguments.
     * @throws InterruptedException if the event dispatch thread is interrupted.
     */
    public static void main(String[] args) throws InterruptedException{
        SwingUtilities.invokeLater(
                () -> startGUI()
            );

    }
    /**
     * This method initializes the GUI components and creates the game controller.
     */
    private static void startGUI(){
        // Create the main frame
        CribbageFrame frame = new CribbageFrame();

        // Create the start game button and add it to the frame
        StartGameButton startGameButton = new StartGameButton();
        startGameButton.setBorder(BorderFactory.createEmptyBorder());

        // Create the game component and the game controller
        GameComponent gameComponent = new GameComponent();
        Game game = new Game();
        Controller controller = new Controller(frame, gameComponent, startGameButton, game);
        frame.add(startGameButton, BorderLayout.CENTER);


    }

}

