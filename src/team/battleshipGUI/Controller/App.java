package team.battleshipGUI.Controller;
import javax.swing.*;

import team.battleshipGUI.Model.Game;
import team.battleshipGUI.View.GameWindow;


public class App {
    public static void main(String[] args) throws Exception {


        GameWindow window = new GameWindow();

        Game game = new Game();
        game.setup();

        

        WindowController wc = new WindowController(window, game);
        window.setVisible(true);
        window.pack();
        
    }
}
