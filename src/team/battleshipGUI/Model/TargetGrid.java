package team.battleshipGUI.Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class TargetGrid extends Grid implements IShotDelegate {
    protected IShotDelegate delegate;
    
    protected transient ArrayList<IGridListener> listeners = new ArrayList<IGridListener>();

    public TargetGrid() {
        super();
    }

    public void addListener(IGridListener toAdd){
        listeners.add(toAdd);
        notifyListeners();
    }

    protected void notifyListeners(){
        for(IGridListener listener: listeners){
            GridRep gridRep = new GridRep(this);
            listener.gridChanged(gridRep);
        }
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        listeners = new ArrayList<IGridListener>();
    }

    @Override
    public void printGrid() {

        System.out.println("Target Grid:");
        String rowName = "";
        printDivider();
        printHeader();
        printDivider();
        for(int row = 0; row < 10 ; row++){
            
            switch(row){
                case 0 :
                rowName = "| A |";
                break;
                case 1 :
                rowName = "| B |";
                break;
                case 2 :
                rowName = "| C |";
                break;
                case 3 :
                rowName = "| D |";
                break;
                case 4 :
                rowName = "| E |";
                break;
                case 5 :
                rowName = "| F |";
                break;
                case 6 :
                rowName = "| G |";
                break;
                case 7 :
                rowName = "| H |";
                break;
                case 8 :
                rowName = "| I |";
                break;
                case 9 :
                rowName = "| J |";
                break;

            }
            System.out.print(rowName);
            for(int column = 0; column < 10; column++){
                
                if(cells[row][column].getState() == CellState.HIT){
                    System.out.print(" X |");
                }else if(cells[row][column].getState() == CellState.MISS){
                    System.out.print(" O |");
                }else{
                    System.out.print("   |");
                }
                
            }
            System.out.println();
            printDivider();
        }
    }

    // return true or false if the cell state is hit or miss 
    public boolean isShotValid(Shot shot) {
        

        //use to find cell at coordinate
        Cell cell = getCell(shot);

        //get state of cell, empyty or occupied
        CellState cs = cell.getState();

        if (cs == CellState.HIT) {
            return false;
        }

        if (cs == CellState.MISS) {
            return false;
        }
        return true;
    }
        

    
         
    

    public void receiveShotResult(ShotResult result, Shot shot) {
        Cell cell = getCell(shot);

        //get state of cell, empyty or occupied
        CellState cs = cell.getState();
        if (result == ShotResult.HIT || result == ShotResult.SUNK) {
            cell.setState(CellState.HIT);
            notifyListeners();
        } else if (result == ShotResult.MISS){
            cell.setState(CellState.MISS);
            notifyListeners();
        }

    }

    @Override
    public void handleShot(Shot shot) {
        delegate.handleShot(shot);
        
    }

    public void setDelegate(IShotDelegate object){
        delegate = object;

    }

}
    
  

    
    
    

