package team.battleshipGUI.Model;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OceanGrid extends Grid {
    private List<String> sunkShips = new ArrayList<String>();
    protected transient ArrayList<IGridListener> listeners = new ArrayList<IGridListener>();

    public OceanGrid(){
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
    public void printGrid(){

        System.out.println("Ocean Grid:");
        
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
                if(cells[row][column].getState() == CellState.OCCUPIED){
                    System.out.print(" S |");
                }else if(cells[row][column].getState() == CellState.HIT){
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
    
    public ShotResult receiveShot(Shot shot) {
        Cell cell = getCell(shot);
        //get corrdinates from shot row/column
        CellState cs = getCellState(shot);
        //make descion 
        if(cs == CellState.EMPTY){
            setCellState(shot, CellState.MISS);
            notifyListeners();
            return ShotResult.MISS;
        }else{
            Ship ship = cell.getShip();
            ship.registerHit();
            cell.setState(CellState.HITOCCUPIED);
            notifyListeners();
            if(ship.isSunk() == true){
                sunkShips.add(ship.getName());
                
                return ShotResult.SUNK;
            } else{
                return ShotResult.HIT;
                
            }
        } 
        
    }
    protected void setShipCells(Ship ship){

        for (Coordinate coordinate : ship.coordinates) {
            
            setCellState(coordinate, CellState.OCCUPIED);
            Cell cell = getCell(coordinate);
            cell.setShip(ship);
        }
    }

    public List<String> getSunkShips(){
        return sunkShips;
    }
    
    
   
    
}
