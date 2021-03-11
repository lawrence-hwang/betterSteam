package tmge;

public abstract class Game {
//    public abstract void update();
    public abstract void initGrid();
    public abstract void handleInput();
    public abstract void checkGameover();
    public abstract void displayGrid();
    public abstract void save();
    public abstract void quit();
}
