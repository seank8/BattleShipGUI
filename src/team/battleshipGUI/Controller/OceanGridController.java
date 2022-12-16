package team.battleshipGUI.Controller;

import team.battleshipGUI.Model.TargetGrid;
import team.battleshipGUI.View.GridPanel;
import team.battleshipGUI.Model.GridRep;
import team.battleshipGUI.Model.IGridListener;
import team.battleshipGUI.Model.OceanGrid;


public class OceanGridController {
    GridPanel view;
    OceanGrid model;
    OceanGridListener modelListener;

    public OceanGridController(GridPanel view, OceanGrid model){
        this.view = view;
        this.model = model;

        

        modelListener = new OceanGridListener();
        model.addListener(modelListener);

    }



    private class OceanGridListener implements IGridListener{

        @Override
        public void gridChanged(GridRep gridRep) {
            view.setGridRep(gridRep);
            
        }
        
    }

}
