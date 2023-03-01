package cribbageGUI;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Menu {

    private Shape ring;

    private BasicStroke strokeSize;




    public Menu(Point upperLeftCorner, Point size, Color colour){
        this.colour = colour;
        ring = new Ellipse2D.Double(upperLeftCorner.getX(), upperLeftCorner.getY(), size.getX(), size.getY());
        strokeSize = new BasicStroke((float)size.getX()/20);
    }

    public void draw(Graphics2D g2d) {
        g2d.setStroke(strokeSize);
        g2d.setColor(colour);
        g2d.draw(ring);
    }
}
