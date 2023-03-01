package cribbageGUI;
import game.Game;

import javax.swing.*;
import java.awt.*;

public class
Main {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(
                () -> startGUI()
            );
    }

    private static void startGUI(){
        MenuComponent menuComponent = new MenuComponent();
        Menu menu1 = new Menu(new Point(100, 200), new Point(600, 400), new ImageIcon("src/CribbageGUI_Images/Buttons/StartGameButton.png").getImage());

        menuComponent.addButton(menu1);


        CribbageFrame frame = new CribbageFrame(menuComponent);

    }
}

