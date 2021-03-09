package tmge;

public class Game {
    private Grid grid;
    private Events event;
    private int Score;
    // private for GUI if we get to it

    public Game() {
        this.grid = new Grid(5, 5);
    }

    public Grid getGrid() {
        return grid;
    }

//    public static void main(String args[]) {
//    }


}
