package team.battleshipGUI.Controller;

import java.util.prefs.Preferences;
import team.battleshipGUI.View.GameWindow;
import java.awt.event.*;
import java.awt.*;

public class WindowController {
    private GameWindow window;
    private Preferences prefs;
    private WindowListener windowListener = new WindowListener();

    public WindowController(GameWindow window){

        this.window = window;
        this.prefs = Preferences.userNodeForPackage(getClass());

        this.window.addComponentListener(windowListener);

        int width = prefs.getInt(Constants.Keys.WindowWidth, Constants.Dimensions.WINDOW_WIDTH);
        int height = prefs.getInt(Constants.Keys.WindowHeight, Constants.Dimensions.WINDOW_HEIGHT);
        int x = prefs.getInt(Constants.Keys.WindowPositionX, 0);
        int y = prefs.getInt(Constants.Keys.WindowPositionY, 0);

        this.window.setSize(width,height);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        if(x == 0 && y == 0){
            this.window.setLocationRelativeTo(null);
        }else if(x + width > screenWidth || y + height > screenHeight || x < 0 || y < 0 ){
            this.window.setLocationRelativeTo(null);
        } else{
            this.window.setLocation(x, y);
        }
        

    }



    class WindowListener extends ComponentAdapter{
        
        @Override
        public void componentMoved(ComponentEvent e){
            prefs.putInt(Constants.Keys.WindowPositionX, window.getX());
            prefs.putInt(Constants.Keys.WindowPositionY, window.getY());
        }

    }



    
    
}
