package team.battleshipGUI.Model;

import java.util.ArrayList;

public class Game implements IShotDelegate {
    private Player humanPlayer;
    private BOTPlayer compPlayer;
    private ArrayList<IStatusListener> listeners = new ArrayList<IStatusListener>();

    public Game(){

        humanPlayer = new Player("Human");
        compPlayer = new BOTPlayer("JohnBot Infinity");
        humanPlayer.setDelegate(this);


    }

    public void setup() {
        humanPlayer.placeShips();
        compPlayer.placeShips();
    }

    public TargetGrid getHumanTargetGrid(){
        return humanPlayer.getTargetGrid();
    }
    public OceanGrid getHumanOceanGrid(){
        return humanPlayer.getOceanGrid();
    }
    public void addListener(IStatusListener object){
        listeners.add(object);
    }

    protected void notifyStatus(String message){
        for (IStatusListener listener : listeners) {
            listener.statusMessage(message);
        }
    }

    public void playHuman(Shot shot){

        notifyStatus("\nYou fire at " + shot.getHumanReadable() + " ");
        ShotResult result = compPlayer.receiveShot(shot);
        if (result == ShotResult.SUNK) {
            notifyStatus("\nHIT!! YOU SUNK THEIR " + compPlayer.getRecentSunkShip() + "!!");
        } else {
            notifyStatus("\nYOU GOT A " + result);
        }
        humanPlayer.receiveShotResult(shot, result);

        if (compPlayer.allShipsAreSunk() == true) {
            endGame(humanPlayer);
        }
        playComputerTurn();

    }

    public void endGame(IPlayer winner) {
        notifyStatus("\n" + winner.getName() + " IS THE WINNER!! ");
    }

    private void playComputerTurn(){
        Shot shot = compPlayer.takeshot();
        notifyStatus("\nComputer fires at " + shot.getHumanReadable() + " ");
        ShotResult result = humanPlayer.receiveShot(shot);
        if (result == ShotResult.SUNK) {
            notifyStatus("\nHIT!! YOU SUNK THEIR " + compPlayer.getRecentSunkShip() + "!!");
        } else {
            notifyStatus("\nCOMPUTER GOT A " + result);
        }
        compPlayer.receiveShotResult(shot, result);

        if (humanPlayer.allShipsAreSunk() == true) {
            endGame(compPlayer);
        }
    }

    @Override
    public void handleShot(Shot shot) {
        playHuman(shot);
        
    }


}

