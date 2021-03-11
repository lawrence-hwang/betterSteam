package bejeweled;

import tmge.Cell;
import tmge.Grid;
import tmge.TileFactory;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Bejeweled extends tmge.Game {
    ArrayList<Cell> toDelete; // List of locations to delete
    private Grid grid;
    private TileFactory tileFactory;
    private int score;
    public static int MAX_ROWS = 8;
    public static int MAX_COLS = 8;

    public Bejeweled() {
        this.tileFactory = new TileFactory();
        this.initGrid();
        this.toDelete = new ArrayList<Cell>();
    }

    @Override
    public void startGame() {
        do {
            displayGrid();
            handleInput();
            match();
        } while (checkGameover());
        quit();
//        displayGrid();
    }

    @Override
    public void initGrid() {
        this.grid = new Grid(MAX_ROWS, MAX_COLS);
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_COLS; j++) {
                grid.setCell(i, j, tileFactory.getNewTile(1, 8));
            }
        }
    }

    @Override
    public void handleInput() {
        int rowNumber;
        int colNumber;
        int dirNumber;
        do {
            System.out.println("Select jewels to swap");
            rowNumber = getIntInput("Enter row number: ", 0, MAX_COLS);
            colNumber = getIntInput("Enter column number: ", 0, MAX_COLS);
            System.out.println("Select direction of jewel to swap");
            System.out.println("1) Up\n2) Down\n3) Left\n4) Right");
            dirNumber = getIntInput("Enter direction: ", 1, 4);
        } while (!validMove(rowNumber, colNumber, dirNumber));
        // DO THE SWAP
        swap(rowNumber, colNumber, dirNumber);
    }

    private void swap(int row, int col, int dir) {
        switch (dir) {
            case 1: {
                // SWAP UP
                int tempFirst = grid.getGrid()[row][col];
                int tempSecond = grid.getGrid()[row-1][col];
                grid.setCell(row-1, col, tempFirst);
                grid.setCell(row, col, tempSecond);
                break;
            }
            case 2: {
                // SWAP DOWN
                int tempFirst = grid.getGrid()[row][col];
                int tempSecond = grid.getGrid()[row+1][col];
                grid.setCell(row+1, col, tempFirst);
                grid.setCell(row, col, tempSecond);
                break;
            }
            case 3: {
                // SWAP LEFT
                int tempFirst = grid.getGrid()[row][col];
                int tempSecond = grid.getGrid()[row][col-1];
                grid.setCell(row, col-1, tempFirst);
                grid.setCell(row, col, tempSecond);
                break;
            }
            case 4: {
                // SWAP RIGHT
                int tempFirst = grid.getGrid()[row][col];
                int tempSecond = grid.getGrid()[row][col+1];
                grid.setCell(row, col+1, tempFirst);
                grid.setCell(row, col, tempSecond);
                break;
            }

        }
    }

    private boolean validMove(int row, int col, int dir) {
        boolean result = true;
        if (row == 0 && dir == 1) { result = false; }
        if (row == MAX_ROWS-1 && dir == 2) { result = false; }
        if (col == 0 && dir == 3) { result = false; }
        if (col == MAX_COLS-1 && dir == 4) { result = false; }

        if (!result) {
            System.out.print("Invalid direction!\nRe-");
        }
        return result;
    }

    @Override
    public boolean checkGameover() {

        return false;
    }

    @Override
    public void displayGrid() {
        System.out.println(grid);
    }

    @Override
    public void match() {
        // HORIZONTAL SEARCH
        for (int i = 0; i < grid.getGrid().length; i++) {
            horizontalMatch(i);
        }
        for (int i = 0; i < grid.getGrid()[0].length; i++) {
            verticalMatch(i);
        }

        for (int i = 0; i < toDelete.size(); i++) {
            System.out.print(toDelete.get(i).getRow());
            System.out.println(toDelete.get(i).getCol());
        }

    }

    private void horizontalMatch(int row) {
        ArrayList<Cell> tempToDelete = new ArrayList<Cell>();
        int current = -1;

        for (int i = 0; i < grid.getGrid()[row].length; i++) {
            if (grid.getGrid()[row][i] != current) {
                if (tempToDelete.size() >= 3) {
                    toDelete.addAll(tempToDelete);
                }
                tempToDelete.clear();
                tempToDelete.add(new Cell(row, i));

                current = grid.getGrid()[row][i];

            } else {
                tempToDelete.add(new Cell(row, i));
            }
        }
    }

    private void verticalMatch(int col) {
        ArrayList<Cell> tempToDelete = new ArrayList<Cell>();
        int current = -1;

        for (int i = 0; i < grid.getGrid().length; i++) {
            if (grid.getGrid()[i][col] != current) {
                if (tempToDelete.size() >= 3) {
                    toDelete.addAll(tempToDelete);
                }
                tempToDelete.clear();
                tempToDelete.add(new Cell(i, col));

                current = grid.getGrid()[i][col];

            } else {
                tempToDelete.add(new Cell(i, col));
            }
        }
    }

    @Override
    public void save() {

    }

    @Override
    public void quit() {

    }
    private int getIntInput(String prompt, int lowerBound, int higherBound) {
        Scanner in = new Scanner(System.in);
        int getInput = -1;
        do {
            System.out.println(prompt);
            try {
                getInput = in.nextInt();
            } catch(InputMismatchException e) {
                getInput = -1;
            }
        } while (getInput > higherBound || getInput < lowerBound);

        System.out.println();
        return getInput;
    }

    // SETTERS AND GETTERS
    public Grid getGrid() { return grid; }
    public void setGrid(Grid grid) { this.grid = grid; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}
