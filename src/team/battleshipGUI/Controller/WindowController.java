package team.battleshipGUI.Controller;

import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import team.battleshipGUI.Model.Game;
import team.battleshipGUI.View.GameWindow;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.*;

public class WindowController {
    private GameWindow window;
    private Preferences prefs;
    private WindowListener windowListener = new WindowListener();
    private JMenuBar menuBar;
    private Game game;
    private TargetGridController tgc;
    private OceanGridController ogc;
    private StatusController sc;

    public WindowController(GameWindow window, Game game){
        this.game = game;
        this.window = window;
        this.prefs = Preferences.userNodeForPackage(getClass());

        tgc = new TargetGridController(window.getTargetPanel(), game.getHumanTargetGrid());
        sc = new StatusController(window.getTextPane(), game);
        ogc = new OceanGridController(window.getOceanPanel(), game.getHumanOceanGrid());

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

        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Open Game");

            int userSelection = fileChooser.showSaveDialog(window);

            if(userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToOpen = fileChooser.getSelectedFile();
                System.out.println("Opening File: " + fileToOpen.getAbsolutePath());
                ObjectInputStream is;
                try {
                   is = new ObjectInputStream(new FileInputStream(fileToOpen.getAbsolutePath()));
                    this.game = (Game)is.readObject();
                    is.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }catch(IOException e1){
                    e1.printStackTrace();
                } catch(ClassNotFoundException e1){
                    e1.printStackTrace();
                }

                window.getTargetPanel().removeMouseListener(tgc.getviewListener());

                tgc = null;
                ogc = null;
                sc = null;

                tgc = new TargetGridController(window.getTargetPanel(), this.game.getHumanTargetGrid());
                sc = new StatusController(window.getTextPane(), this.game);
                ogc = new OceanGridController(window.getOceanPanel(), this.game.getHumanOceanGrid());

            }
        });
        openItem.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        fileMenu.add(openItem);
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Game");

            int userSelection = fileChooser.showSaveDialog(window);

            if(userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                System.out.println("Save as File: " + fileToSave.getAbsolutePath());
                try {
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileToSave.getAbsolutePath()));
                    os.writeObject(this.game);
                    os.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }catch(IOException e1){
                    e1.printStackTrace();
                }
            }
        });;
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        fileMenu.add(saveItem);
        window.setJMenuBar(menuBar);
        

    }



    class WindowListener extends ComponentAdapter{
        
        @Override
        public void componentMoved(ComponentEvent e){
            prefs.putInt(Constants.Keys.WindowPositionX, window.getX());
            prefs.putInt(Constants.Keys.WindowPositionY, window.getY());
        }

    }



    
    
}
