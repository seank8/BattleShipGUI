package team.battleshipGUI.Model;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.util.Map.Entry;


public class Player extends IPlayer implements IShotDelegate{
    private String name;
    private Shot shot;
    protected OceanGrid oceanGrid = new OceanGrid();
    protected TargetGrid targetGrid = new TargetGrid();
    private IShotDelegate delegate;
    
    
    protected ShipBuilder builder;

    public Player(String name) {
        this.name = name;
        this.targetGrid.setDelegate(this);
    }
    public void setDelegate(IShotDelegate object){
        delegate = object;
    }
    @Override
    public void printOceanGrid(){
        oceanGrid.printGrid();
    }
    @Override
    public void printTargetGrid(){
        targetGrid.printGrid();
    }

    public void setOceanGrid(OceanGrid grid){
        this.oceanGrid = grid;
    }
    public OceanGrid getOceanGrid(){
        return oceanGrid;
    }
    public TargetGrid getTargetGrid(){
        return targetGrid;
    }
    public String getName(){
        return name;
    }
    
   
    // row and column Position bool, random do and while return

   
    @Override
    public void placeShips(){
        builder = new ShipBuilder();
        for (Ship ship : builder.getShips()) {
            oceanGrid.setShipCells(ship);
        }

    }


    
    @Override
    public Shot takeshot() {
        while (true){
           String input = ConsoleHelper.getInput("Take a Shot> ");
            try {
                
                shot  = new Shot(input);
            } catch (Exception e){
                System.out.println("Invalid Shot");
               // explain to user of an invalid shot
               //continue while loop 
               continue;
            }
            
            if (targetGrid.isShotValid(shot)){
                return shot;
                
            }else {
            System.out.printf("You have already taken the Shot at %s%n", input);
            
            }
        }
    }
    @Override
    public ShotResult receiveShot(Shot shot) {
        return oceanGrid.receiveShot(shot);
    }
    @Override
    public void receiveShotResult(Shot shot, ShotResult result) {
        targetGrid.receiveShotResult(result, shot);
        
    }
    @Override
    public boolean allShipsAreSunk() {
        if(oceanGrid.getSunkShips().size() >= 5){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getRecentSunkShip(){
        List<String> sunkShips = oceanGrid.getSunkShips();

        
        String mostRecentSunkShip = sunkShips.get(sunkShips.size()-1);
        return mostRecentSunkShip;

    }
    @Override
    public void handleShot(Shot shot) {
       delegate.handleShot(shot);
        
    }
}
                