package tmge;

public abstract class Game {
//    public abstract void update();
    public abstract void startGame();
    public abstract void initGrid();
    public abstract void handleInput();
    public abstract boolean checkGameover();
    public abstract void displayGrid();
    public abstract void match();
    public abstract void save();
    public abstract void quit();
}
