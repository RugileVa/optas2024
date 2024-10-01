package optas.lt.battleship.domain;

import optas.lt.battleship.domain.enums.CellState;

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

//    public void initialise(Cell[][] map) {
//        copy2dArray(grid, map);
//    }
//
//    private void copy2dArray(Cell[][] output, Cell[][] toBeCopied) {
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                output[i][j] = new Cell(toBeCopied[i][j]);
//            }
//        }
//    }

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

    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

//    public boolean attackCell(int row, int col) {
//        return grid[row][col].receiveAttack();
//    }
//
//    public Cell[][] getGridStatus() {
//        return grid;
//    }

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
