package cribbageGUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameButton extends JButton implements ActionListener {
    CribbageFrame frame;
    public StartGameButton(ImageIcon img, CribbageFrame frame){
        this.frame = frame;
        this.setIcon(img);
        addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.startTheGame(this);
    }


}
