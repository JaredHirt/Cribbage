package cribbageGUI;

import game.Game;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

public class Menu{


    private Image startButton;
    private Point upperLeftCorner;
    private Point size;




    public Menu(Point upperLeftCorner, Point size, Image startButton){
        this.startButton = startButton;
        this.upperLeftCorner = upperLeftCorner;
        this.size = size;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(startButton, (int)upperLeftCorner.getX(), (int)upperLeftCorner.getY(), (int)size.getX(), (int)size.getY(), null);
    }

    public void startGame(){
        Game.playGame();
    }
}
