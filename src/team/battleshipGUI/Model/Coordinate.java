package team.battleshipGUI.Model;

import java.io.Serializable;

public class Coordinate implements Serializable{
    private int row;
    private int column;

    public Coordinate(int row, int column){
        this.column = column;
        this.row = row;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        if(o == null){
            return false;
        }
        if(!(o instanceof Coordinate)){
            return false;
        }
        Coordinate coordinate = (Coordinate) o;

        int column = coordinate.getColumn();
        int row = coordinate.getRow();
        if(this.getColumn() == column && this.getRow() == row){
            return true;
        } else{
            return false;
        }

    }
    
}

