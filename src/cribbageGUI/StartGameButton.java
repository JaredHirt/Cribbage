package cribbageGUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameButton extends JButton implements ActionListener {
    private Controller controller;
    public StartGameButton(ImageIcon img){
        this.setIcon(img);
        addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.startTheGame(this);
    }
    public void setController(Controller control){this.controller = control;}


}
