package cribbageGUI;
import java.awt.geom.Rectangle2D;
import java.awt.*;

public class Game {


    private Shape rectangle;




    public Game(Point upperLeftCorner, Point size){
        rectangle = new Rectangle2D.Double(upperLeftCorner.getX(), upperLeftCorner.getY(), size.getX(), size.getY());
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.blue);
        g2d.fill(rectangle);
    }

}
