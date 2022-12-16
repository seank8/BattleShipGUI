package team.battleshipGUI.Controller;

import java.awt.event.MouseEvent;

import team.battleshipGUI.Model.GridRep;
import team.battleshipGUI.Model.IGridListener;
import team.battleshipGUI.Model.Shot;
import team.battleshipGUI.Model.TargetGrid;
import team.battleshipGUI.View.GridPanel;
import java.awt.event.MouseAdapter;



public class TargetGridController {
    GridPanel view;
    TargetGrid model;
    GridPanelListener viewListener;
    TargetGridListener modelListener;

    public TargetGridController(GridPanel view, TargetGrid model){
        this.view = view;
        this.model = model;

        viewListener = new GridPanelListener();
        view.addMouseListener(viewListener);

        modelListener = new TargetGridListener();
        model.addListener(modelListener);

    }

    public GridPanelListener getviewListener(){
        return viewListener;
    }

    private class GridPanelListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            int x = e.getX();
            int y = e.getY();

            int modelX = x / Constants.Dimensions.CELL_SIZE;
            int modelY = y / Constants.Dimensions.CELL_SIZE;

            Shot shot = new Shot(modelX, modelY);
            
            if(model.isShotValid(shot)){
                model.handleShot(shot);
            }else{
                System.out.println("You have already taken this shot, try again!");
            }
            
        }

        
    }

    private class TargetGridListener implements IGridListener{

        @Override
        public void gridChanged(GridRep gridRep) {
            view.setGridRep(gridRep);
            
        }
        
    }
    
}
