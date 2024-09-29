package optas.lt.battleship.services;

import optas.lt.battleship.domain.Cell;
import optas.lt.battleship.domain.MapGrid;
import optas.lt.battleship.domain.enums.CellState;

import java.util.Random;

public class Game {
    private final String gameId;
    private final int BOARD_SIZE = 10;
    private final int MAX_SHOTS = 25;
    private int remainingShots;
    private boolean isGameOver;
    private MapGrid playerGrid;
    private MapGrid computerGrid;

    public Game(String gameId) {
        this.gameId = gameId;
        this.remainingShots = MAX_SHOTS;
        this.isGameOver = false;
        this.playerGrid = new MapGrid(BOARD_SIZE);
        this.computerGrid = new MapGrid(BOARD_SIZE);

        placeShips(computerGrid);
    }

    private void placeShips(MapGrid grid) {
        Random rand = new Random();
        int[] shipSizes = {5, 4, 3, 3, 2, 2, 1, 1, 1};

        for (int shipSize : shipSizes) {
            boolean placed = false;
            while (!placed) {
                int row = rand.nextInt(BOARD_SIZE);
                int col = rand.nextInt(BOARD_SIZE);
                boolean horizontal = rand.nextBoolean();
                if (grid.canPlaceShip(row, col, shipSize, horizontal)) {
                    grid.placeShip(row, col, shipSize, horizontal);
                    placed = true;
                }
            }
        }
    }

    // Process player's move and return the result
    public String processMove(int row, int col) {
        if (isGameOver) {
            return "Game is already over!";
        }

        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return "Invalid coordinates!";
        }

        Cell targetCell = computerGrid.getCell(row, col);
        if (targetCell.getStatus() != CellState.HIDDEN) {
            return "You already attacked this cell!";
        }

        boolean hit = targetCell.receiveAttack();
        if (hit) {
            if (targetCell.getStatus() == CellState.DESTROYED) {
                if (checkAllShipsSunk()) {
                    isGameOver = true;
                    return "You sunk all ships! You won!";
                }
                return "Hit and sunk!";
            }
            return "Hit!";
        } else {
            remainingShots--;
            if (remainingShots == 0) {
                isGameOver = true;
                return "Miss! No shots remaining, you lost!";
            }
            return "Miss!";
        }
    }

    private boolean checkAllShipsSunk() {
        return computerGrid.areAllShipsSunk();
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public int getRemainingShots() {
        return remainingShots;
    }

    public MapGrid getPlayerGrid() {
        return playerGrid;
    }

    public MapGrid getComputerGrid() {
        return computerGrid;
    }
}
