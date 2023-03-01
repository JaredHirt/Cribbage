package cribbageGUI;

import javax.swing.*;

public class CribbageFrame extends JFrame {
    public CribbageFrame(MenuComponent name){
        super("Question 6 Olympic Rings");
        setSize(800, 800);
        setLocation(100, 100);
        getContentPane().add(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
