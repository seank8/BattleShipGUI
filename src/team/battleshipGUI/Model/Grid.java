package team.battleshipGUI.Model;

import java.io.Serializable;

public class Grid implements Serializable{
    protected Cell[][] cells = new Cell[10][10];
    
    private CellState state = CellState.EMPTY;
    

    public Grid(){
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++){
                cells[row][column] = new Cell(state);
            }
            
        }
    }

    protected void printDivider(){
        System.out.println("+---+---+---+---+---+---+---+---+---+---+---+");
    } 

    protected void printHeader(){
        System.out.println("|   | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |10 |");
    }


    public void printGrid() {
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
                if(cells[row][column].getState() == CellState.EMPTY){
                    System.out.print("   |");
                }
                
            }
            System.out.println();
            printDivider();
        }

    }

    protected Cell getCell(Shot shot){
        int r = shot.getRow();
        int c = shot.getColumn();

        //use to find cell at coordinate
        Cell cell = cells[r][c];
        return cell;
    }
    protected Cell getCell(Coordinate coordinate){
        int r = coordinate.getRow();
        int c = coordinate.getColumn();

        //use to find cell at coordinate
        Cell cell = cells[r][c];
        return cell;
    }

    protected CellState getCellState(Shot shot){
        

        //use to find cell at coordinate
        Cell cell = getCell(shot);

        //get state of cell, empyty or occupied
        CellState cs = cell.getState();
        return cs;

    }
    protected CellState getCellState(Coordinate coordinate){
        

        //use to find cell at coordinate
        Cell cell = getCell(coordinate);

        //get state of cell, empyty or occupied
        CellState cs = cell.getState();
        return cs;

    }

    protected void setCellState(Shot shot, CellState state){
        

        //use to find cell at coordinate
        Cell cell = getCell(shot);

        //get state of cell, empyty or occupied
        cell.setState(state);

    }

    protected void setCellState(Coordinate coordinate, CellState state){
        

        //use to find cell at coordinate
        Cell cell = getCell(coordinate);

        //get state of cell, empyty or occupied
        cell.setState(state);

    }

    

}
