package team.battleshipGUI.Controller;
import javax.swing.*;

import team.battleshipGUI.Model.Game;
import team.battleshipGUI.View.GameWindow;


public class App {
    public static void main(String[] args) throws Exception {


        GameWindow window = new GameWindow();

        Game game = new Game();
        game.setup();

        TargetGridController tgc = new TargetGridController(window.getTargetPanel(), game.getHumanTargetGrid());
        StatusController sc = new StatusController(window.getTextPane(), game);
        OceanGridController ogc = new OceanGridController(window.getOceanPanel(), game.getHumanOceanGrid());

        WindowController wc = new WindowController(window);
        window.setVisible(true);
        window.pack();
        
    }
}
