package tfe;

import tmge.Cell;
import tmge.Grid;
import tmge.TileFactory;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class tfe extends tmge.Game{
    Random rand;
    ArrayList<Cell> toDelete;
    private Grid grid;
    private Grid tempGrid;
    private TileFactory tileFactory;
    private int score;
    public static int MAX_ROWS = 4;
    public static int MAX_COLS = 4;

    private int goal = 2048;
    private boolean GAME_ACTIVE = true;

    public tfe() {
        score = 0;
        this.tileFactory = new TileFactory();
        this.initGrid();
        this.toDelete = new ArrayList<Cell>();
    }

    @Override
    public void startGame() {
        do{
            displayGrid();
            break;
        } while(GAME_ACTIVE);
        quit();
    }

    @Override
    public void initGrid() {

        this.grid = new Grid(MAX_ROWS, MAX_COLS);
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_COLS; j++) {
                grid.setCell(i, j, 0);
            }
        }
        fillTwo();
        fillTwo();
        tempGrid = grid;
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void checkGameover() {

    }

    @Override
    public void displayGrid() {
        System.out.print(grid);
        System.out.println(String.format("Current Highest Block: %d",score));
        System.out.println(String.format("Goal: %d", goal));
    }

    @Override
    public void matchCheck() {

    }

    @Override
    public void save() {

    }

    @Override
    public int quit() {
        return 0;
    }

    public boolean fillTwo(){
        // Set two cells to value 2 to get beginning board
        // 90% fill 2, 10% fill 4
        ArrayList<Cell> emptyCells = new ArrayList<Cell>();
        for(int i = 0; i < MAX_ROWS; i++){
            for(int j = 0; j < MAX_COLS; j++){
                if(grid.getCell(i, j) == 0){
                    emptyCells.add(new Cell(i, j));
                }
            }
        }
        if(emptyCells.size() == 0){
            return false;
        }

        int index = tileFactory.getNewTile(0, emptyCells.size()- 1);
        Cell tempCell = emptyCells.get(index);
        grid.setCell(tempCell.getRow(), tempCell.getCol(), 2);

        return true;
    }
}