package cribbageGUI;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameComponent extends JComponent{
    private Controller controller;
    public GameComponent(){
        super();
        setLayout(new GridBagLayout());
    }
    public void setController(Controller control){controller = control;}

}
