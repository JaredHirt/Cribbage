package cribbageGUI;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MenuComponent extends JComponent implements MouseListener{
    private ArrayList<Menu> myButtons;

    public MenuComponent(){
        super();
        addMouseListener(this);
        myButtons = new ArrayList<Menu>();
    }

    public void addButton(Menu n){
        myButtons.add(n);}


    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        for(Menu n: myButtons)
            n.draw(g2d);
    }

    public void mousePressed(java.awt.event.MouseEvent mouseEvent)
    {
        Game.playGame();
    }

    public void mouseReleased(java.awt.event.MouseEvent mouseEvent)
    {
    }

    public void mouseClicked(java.awt.event.MouseEvent mouseEvent)
    {
    }

    public void mouseEntered(java.awt.event.MouseEvent mouseEvent)
    {
    }

    public void mouseExited(java.awt.event.MouseEvent mouseEvent)
    {
    }


}
