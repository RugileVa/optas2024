package optas.lt.battleship.domain;

import optas.lt.battleship.domain.enums.CellState;

import java.util.Random;

public class MapGrid {
    private int size;
    private Cell[][] grid;

    public MapGrid(int size) {
        this.size = size;
        grid = new Cell[size][size];
        initialiseEmptyGrid();
    }
    private void initialiseEmptyGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell(CellState.EMPTY);
            }
        }
    }

    /* Initialize the map with a pre-defined 2D array of Cells*/
    public void initialise(Cell[][] map) {
        copy2dArray(grid, map);
    }

    private void copy2dArray(Cell[][] output, Cell[][] toBeCopied) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                output[i][j] = new Cell(toBeCopied[i][j]);
            }
        }
    }

    public boolean placeShip(int row, int col, int length, boolean horizontal) {
        if (canPlaceShip(row, col, length, horizontal)) {
            Battleship ship = new Battleship(length);
            if (horizontal) {
                for (int i = 0; i < length; i++) {
                    grid[row][col + i].putShip(ship);
                }
            } else {
                for (int i = 0; i < length; i++) {
                    grid[row + i][col].putShip(ship);
                }
            }
            return true;
        }
        return false;
    }

    // Check if a ship can be placed at the specified coordinates
    public boolean canPlaceShip(int row, int col, int length, boolean horizontal) {
        // Check if the ship fits within the grid
        if (horizontal) {
            if (col + length > size) return false;
            for (int i = col - 1; i <= col + length; i++) {
                for (int j = row - 1; j <= row + 1; j++) {
                    if (isWithinBounds(j, i) && grid[j][i].getStatus() != CellState.EMPTY) {
                        return false;
                    }
                }
            }
        } else {
            if (row + length > size) return false;
            for (int i = row - 1; i <= row + length; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (isWithinBounds(i, j) && grid[i][j].getStatus() != CellState.EMPTY) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Helper method to check if coordinates are within bounds
    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    // Attack a specific cell
//    public boolean attack(int row, int col) {
//        return grid[row][col].receiveAttack();
//    }

    // Get the status of the grid for display or logic processing
    public Cell[][] getGridStatus() {
        return grid;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public boolean areAllShipsSunk() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j].getStatus() == CellState.SHIP) {
                    return false;
                }
            }
        }
        return true;
    }
}
