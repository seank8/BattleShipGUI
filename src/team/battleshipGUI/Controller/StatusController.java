package team.battleshipGUI.Controller;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import team.battleshipGUI.Model.Game;
import team.battleshipGUI.Model.IStatusListener;
import team.battleshipGUI.Model.TargetGrid;
import team.battleshipGUI.View.GridPanel;

public class StatusController {
    JTextPane view;
    Game model;
    private StatusListener statusListener;

    public StatusController(JTextPane pane, Game model){
        this.view = pane;
        this.model = model;

        statusListener = new StatusListener();
        this.model.addListener(statusListener);
    }

    private class StatusListener implements IStatusListener{

        @Override
        public void statusMessage(String message) {
            try {
                view.getStyledDocument().insertString(view.getStyledDocument().getLength(), message, null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
        
    }


    
}
