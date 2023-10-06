package main.java.cribbageGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RestartGameButton extends StartGameButton{
    private Controller controller;
    public RestartGameButton(){
        super();
        setIcon(new ImageIcon("src/main/resources/CribbageGUI_Images/Buttons/RestartButton.png"));
    }
    public void setController(Controller controller){
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        controller.restartGame(this);
    }
}
