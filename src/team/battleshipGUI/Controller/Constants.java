package team.battleshipGUI.Controller;

public class Constants {


    public static class Keys{
        public final static String WindowPositionX = "WindowPositionX";
        public final static String WindowPositionY = "WindowPositionY";

        public final static String WindowHeight = "WindowHeight";
        public final static String WindowWidth = "WindowWidth";
        


    }

    public static class Dimensions{
        public final static int CELL_SIZE = 36;
        public final static int GRID_SIZE = 361;

        public final static int WINDOW_FOOTER_HEIGHT = 24;
        public final static int WINDOW_HEADER_HEIGHT = 38;
        
        public final static int STATUS_AREA_HEIGHT = 100;
        public final static int SHIP_AREA_WIDTH = 100;

        public final static int WINDOW_WIDTH = GRID_SIZE + SHIP_AREA_WIDTH + 1;
        public final static int WINDOW_HEIGHT = 2 * GRID_SIZE + STATUS_AREA_HEIGHT + 1;

        public final static int PEG_DIAMETER = 10;


    }

    private Constants(){}

    
}
