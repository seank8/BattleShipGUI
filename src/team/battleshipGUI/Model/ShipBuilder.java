package team.battleshipGUI.Model;

import java.util.ArrayList;
import java.util.Random;

public class ShipBuilder{
    private ArrayList<Ship> ships = new ArrayList<Ship>();
    
    private Random rand = new Random();
    private Coordinate origin;
    private ArrayList<Coordinate> usedCoords = new ArrayList<Coordinate>();
   

    public ShipBuilder(){
        //repeat making of ship 5 times(1 for each ship)
        for (ShipType name : ShipType.values()){
            while(true){
                ShipOrientation orientation = ShipOrientation.randomOrientation();
                int length = name.getLength();
                if (orientation == ShipOrientation.HORIZONTAL){
                    origin = new Coordinate(rand.nextInt(10), rand.nextInt(11 - length));
                } else{
                    origin = new Coordinate(rand.nextInt(11 - length), rand.nextInt(10));
                }
                ArrayList<Coordinate> coordinates = addCoords(name.getLength(),orientation, origin);
                if(verifyCoordsMap(coordinates) == false){
                    continue;
                }
                if (verifyCoordsShips(coordinates, usedCoords) == false){
                    continue;
                }else{
                    Ship ship = new Ship(name, name.getLength(), coordinates);
                    
                    usedCoords.addAll(coordinates);
                    ships.add(ship);
                    break;
                }
            }
        }
    }

    public ArrayList<Coordinate> addCoords(int length, ShipOrientation orientation, Coordinate origin){
        ArrayList<Coordinate> shipCoords = new ArrayList<Coordinate>();
        shipCoords.add(origin);
        int nextColumn = origin.getColumn();
        int nextRow = origin.getRow();
        
        for (int i = 1; i < length; i++){
            if (orientation == ShipOrientation.HORIZONTAL){
                nextColumn++;
                shipCoords.add(new Coordinate(nextRow, nextColumn));
                
            }else{
                nextRow++;
                shipCoords.add(new Coordinate(nextRow, nextColumn));
                
            }

        }

        return shipCoords;

        
    }

  
    public boolean verifyCoordsShips(ArrayList<Coordinate> newCoords, ArrayList<Coordinate> usedCoords){
        for(Coordinate newC : newCoords){
            for(Coordinate used : usedCoords){
                if (newC.equals(used)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean verifyCoordsMap(ArrayList<Coordinate> coordinates){
        for(Coordinate i : coordinates){
            int column = i.getColumn();
            int row = i.getRow();
            if(column > 10 || column < 1 || row > 10 || row < 1){
                return false;
            }
        }
        return true;

    }

    public ArrayList<Ship> getShips(){
        return ships;
    }

    
}