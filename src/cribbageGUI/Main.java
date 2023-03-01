package cribbageGUI;
import game.Game;
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
        Menu menu1 = new Menu(new Point(100, 400), new Point(200, 200), Color.blue);
        Menu menu2 = new Menu(new Point(300, 400), new Point(200, 200), Color.black);
        Menu menu3 = new Menu(new Point(500, 400), new Point(200, 200), Color.red);
        Menu menu4 = new Menu(new Point(200, 500), new Point(200, 200), Color.yellow);
        Menu menu5 = new Menu(new Point(400, 500), new Point(200, 200), Color.green);
        menuComponent.addRing(menu1);
        menuComponent.addRing(menu2);
        menuComponent.addRing(menu3);
        menuComponent.addRing(menu4);
        menuComponent.addRing(menu5);

        CribbageFrame frame = new CribbageFrame(menuComponent);

    }
}

