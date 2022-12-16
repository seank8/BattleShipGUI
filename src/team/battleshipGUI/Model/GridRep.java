package team.battleshipGUI.Model;

import java.io.Serializable;

public class GridRep implements Serializable{

    private CellState[][] cells = new CellState[10][10];

    public GridRep(Grid grid){
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++){
                cells[row][column] = grid.cells[row][column].getState();
            }
            
        }
    }

    public CellState getStateAt(int row, int column){
        return cells[row][column];
    }

    
}
