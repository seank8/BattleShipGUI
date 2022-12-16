package team.battleshipGUI.Model;

import java.util.Random;

public enum ShipOrientation {
    HORIZONTAL,
    VERTICAL;

    private static Random rand = new Random();
    
    public static ShipOrientation randomOrientation(){
        return ShipOrientation.values()[rand.nextInt(2)];

    }
}
