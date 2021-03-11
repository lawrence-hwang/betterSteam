package bejeweled;

import tmge.Grid;
import tmge.TileFactory;

public class Bejeweled extends tmge.Game {
    private Grid grid;
    private TileFactory tileFactory;
    private int score;
    public static int MAX_ROWS = 8;
    public static int MAX_COLS = 8;

    public Bejeweled() {
        this.tileFactory = new TileFactory();
        this.initGrid();
    }

    @Override
    public void initGrid() {
        this.grid = new Grid(MAX_ROWS, MAX_COLS);
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_COLS; j++) {
                grid.setCell(i, j, tileFactory.getNewTile(1, 7));
            }
        }
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void checkGameover() {

    }

    @Override
    public void displayGrid() {

    }

    @Override
    public void save() {

    }

    @Override
    public void quit() {

    }

    // SETTERS AND GETTERS
    public Grid getGrid() { return grid; }
    public void setGrid(Grid grid) { this.grid = grid; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}
