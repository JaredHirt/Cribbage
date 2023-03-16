package cribbageGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A custom button that starts the game when clicked.
 */
public class StartGameButton extends JButton implements ActionListener {
    private Controller controller;

    /**
     * Creates a new StartGameButton with the specified image icon.
     *
     * @param img the image icon to be displayed on the button
     */
    public StartGameButton(ImageIcon img){
        this.setIcon(img);
        setBackground(new Color(102,50,0));
        addActionListener(this);
    }

    /**
     * Sets the controller for this button.
     *
     * @param control the controller to be set
     */
    public void setController(Controller control){this.controller = control;}

    /**
     * Called when the button is clicked.
     *
     * @param e the ActionEvent that occurred
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.startTheGame(this);
    }
}
