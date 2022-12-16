package team.battleshipGUI.View;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import team.battleshipGUI.Controller.Constants;


public class GameWindow extends JFrame{
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel footerPanel = new JPanel();
    private GridPanel targetPanel = new GridPanel();
    private GridPanel oceanPanel = new GridPanel();
    private JTextPane statusPanel = new JTextPane();
    private JScrollPane statusScroller = new JScrollPane(statusPanel);


    public GameWindow(){
        super("Battleship");
        this.setResizable(false);

        Container contentPane = this.getContentPane();
        contentPane.add(leftPanel, BorderLayout.WEST);
        contentPane.add(rightPanel, BorderLayout.CENTER);
        contentPane.add(footerPanel, BorderLayout.SOUTH);

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(targetPanel);

        statusPanel.setEditable(false);
        statusPanel.setText("Welcome to Battleship.\n");
        try {
            statusPanel.getStyledDocument().insertString(statusPanel.getStyledDocument().getLength(), "It is your turn", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        statusScroller.setPreferredSize(new Dimension(Constants.Dimensions.GRID_SIZE, Constants.Dimensions.STATUS_AREA_HEIGHT));

        statusScroller.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
            public void adjustmentValueChanged(AdjustmentEvent e){
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });
        leftPanel.add(statusScroller);

        leftPanel.add(oceanPanel);

    }

    public GridPanel getTargetPanel(){
        return targetPanel;
    }

    public JTextPane getTextPane(){
        return statusPanel;
    }
    public GridPanel getOceanPanel(){
        return oceanPanel;
    }
    
}
