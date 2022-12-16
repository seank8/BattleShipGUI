package team.battleshipGUI.View;

import javax.swing.JPanel;

import team.battleshipGUI.Controller.Constants;
import team.battleshipGUI.Controller.Constants.Dimensions;
import team.battleshipGUI.Model.CellState;
import team.battleshipGUI.Model.GridRep;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.*;
import java.awt.geom.*;

public class GridPanel extends JPanel{
    private GridRep gridRep = null;

    public void setGridRep(GridRep rep){
        gridRep = rep;
        this.repaint();
    }

    public void paintComponent(Graphics g){
        int width = this.getWidth();
        int height = this.getHeight();


        g.setColor(Color.BLACK);

        if(gridRep == null){
            for(int x = 0; x < width; x += Constants.Dimensions.CELL_SIZE){
                g.drawLine(x, 0, x, height);
            }
    
            for(int y = 0; y < height; y += Constants.Dimensions.CELL_SIZE){
                g.drawLine(0, y, width, y);
            }
        }else{
            for (int row = 0; row < 10; row++) {
                for (int column = 0; column < 10; column++){

                    g.setColor(Color.BLUE);
                    int upperLeftX = row * Constants.Dimensions.CELL_SIZE;
                    int upperLeftY = column * Constants.Dimensions.CELL_SIZE;
                    int rectW = Constants.Dimensions.CELL_SIZE;
                    int rectH = Constants.Dimensions.CELL_SIZE;
                    g.drawRect(upperLeftX, upperLeftY, rectW, rectH);
                    
                    if(gridRep.getStateAt(row, column) == CellState.HIT){
                        Graphics2D g2d = (Graphics2D)g;
                        g2d.setColor(Color.RED);
                        Ellipse2D.Double circle = new Ellipse2D.Double(upperLeftX + (Constants.Dimensions.CELL_SIZE/2.0) - (Constants.Dimensions.PEG_DIAMETER / 2.0), upperLeftY + (Constants.Dimensions.CELL_SIZE/2.0) - (Constants.Dimensions.PEG_DIAMETER / 2.0), Constants.Dimensions.PEG_DIAMETER, Constants.Dimensions.PEG_DIAMETER);
                        g2d.fill(circle);
                        g2d.setColor(Color.BLACK);
                        g2d.draw(circle);
                    }
                    if(gridRep.getStateAt(row, column) == CellState.MISS){
                        Graphics2D g2d = (Graphics2D)g;
                        g2d.setColor(Color.WHITE);
                        Ellipse2D.Double circle = new Ellipse2D.Double(upperLeftX + (Constants.Dimensions.CELL_SIZE/2.0) - (Constants.Dimensions.PEG_DIAMETER / 2.0), upperLeftY + (Constants.Dimensions.CELL_SIZE/2.0) - (Constants.Dimensions.PEG_DIAMETER / 2.0), Constants.Dimensions.PEG_DIAMETER, Constants.Dimensions.PEG_DIAMETER);
                        g2d.fill(circle);
                        g2d.setColor(Color.BLACK);
                        g2d.draw(circle);
                    }
                    if(gridRep.getStateAt(row, column) == CellState.OCCUPIED){
                        Graphics2D g2d = (Graphics2D)g;
                        g2d.setColor(Color.GRAY);
                        Rectangle2D.Double rect = new Rectangle2D.Double(upperLeftX,upperLeftY,Constants.Dimensions.CELL_SIZE,Constants.Dimensions.CELL_SIZE);
                        g2d.fill(rect);
                    }
                    if(gridRep.getStateAt(row, column) == CellState.HITOCCUPIED){
                        Graphics2D g2d = (Graphics2D)g;
                        g2d.setColor(Color.RED);
                        Rectangle2D.Double rect = new Rectangle2D.Double(upperLeftX,upperLeftY,Constants.Dimensions.CELL_SIZE,Constants.Dimensions.CELL_SIZE);
                        g2d.fill(rect);
                    }


                }
            }
        }
        
    }


    @Override
    public Dimension getMinimumSize(){
        return new Dimension(Constants.Dimensions.GRID_SIZE, Constants.Dimensions.CELL_SIZE);

    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(Constants.Dimensions.GRID_SIZE, Constants.Dimensions.GRID_SIZE);
    }

    @Override
    public Dimension getMaximumSize(){
        return new Dimension(Constants.Dimensions.GRID_SIZE, Constants.Dimensions.GRID_SIZE);
    }

    
}
