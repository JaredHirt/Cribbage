package cribbageGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuComponent extends JComponent {
    private ArrayList<Menu> myMenus;

    public MenuComponent(){
        super();
        myMenus = new ArrayList<Menu>();
    }

    public void addRing(Menu n){
        myMenus.add(n);}


    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        for(Menu n: myMenus)
            n.draw(g2d);
    }
}
