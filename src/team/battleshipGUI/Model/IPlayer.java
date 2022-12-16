package team.battleshipGUI.Model;

public abstract class IPlayer {

    
    public String getName(){
        return null;
    }

    public void placeShips(){}

    public Shot takeshot(){
        return null;
    }

    public ShotResult receiveShot(Shot shot){
        return null;
    }
    public void receiveShotResult(Shot shot, ShotResult result){}

    public boolean allShipsAreSunk(){
        return true;
    }

    public void printOceanGrid(){
        
    }
    
    public void printTargetGrid(){}

    public String getRecentSunkShip(){
        return null;
    }

    public TargetGrid getTargetGrid(){
        return null;
    }
}
