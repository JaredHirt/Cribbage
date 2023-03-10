package cribbageGUI;

import javax.swing.*;
import java.awt.*;

public class
Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> startGUI()
            );
    }

    private static void startGUI(){
        CribbageFrame frame = new CribbageFrame();

        StartGameButton startGameButton = new StartGameButton(new ImageIcon("src/CribbageGUI_Images/Buttons/StartGameButton.png"));
        startGameButton.setBorder(BorderFactory.createEmptyBorder());
        GameComponent gameComponent = new GameComponent();
        Controller controller = new Controller(frame, gameComponent, startGameButton);
        frame.add(startGameButton, BorderLayout.CENTER);

    }

}

