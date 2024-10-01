package optas.lt.battleship.services;

import lombok.Getter;
import optas.lt.battleship.domain.Cell;
import optas.lt.battleship.domain.MapGrid;
import optas.lt.battleship.domain.dtos.MoveResult;
import optas.lt.battleship.domain.enums.CellState;
import optas.lt.battleship.domain.enums.GameState;

import java.util.Random;

public class Game {
    private final String gameId;
    private final int BOARD_SIZE = 10;
    private final int MAX_SHOTS = 25;

    @Getter
    private int remainingShots;
    private boolean isGameOver;

    @Getter
    private MapGrid computerGrid;

    public Game(String gameId) {
        this.gameId = gameId;
        this.remainingShots = MAX_SHOTS;
        this.isGameOver = false;
        this.computerGrid = new MapGrid(BOARD_SIZE); // actual board

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

    public MoveResult processMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return new MoveResult(GameState.PLAYING, null, remainingShots);
        }

        Cell targetCell = computerGrid.getCell(row, col);
        if (targetCell.getStatus() == CellState.SHIPHIT || targetCell.getStatus() == CellState.EMPTYHIT ||  targetCell.getStatus() == CellState.DESTROYED) {
            remainingShots--;
            return new MoveResult(GameState.PLAYING, targetCell.getStatus(), remainingShots);
        }

        boolean hit = targetCell.receiveAttack();
        if (hit) {
            if (targetCell.getStatus() == CellState.DESTROYED) {
                if (checkAllShipsSunk()) {
                    isGameOver = true;
                    return new MoveResult(GameState.WIN, CellState.DESTROYED, remainingShots); // won
                }
                return new MoveResult(GameState.PLAYING, CellState.DESTROYED, remainingShots); // hit and sunk
            }
            return new MoveResult(GameState.PLAYING, CellState.SHIPHIT, remainingShots); // hit
        } else {
            remainingShots--;
            if (remainingShots == 0) {
                isGameOver = true;
                return new MoveResult(GameState.LOSE, CellState.EMPTYHIT, remainingShots); // empty, lose
            }
            return new MoveResult(GameState.PLAYING, CellState.EMPTYHIT, remainingShots); // empty
        }
    }

    private boolean checkAllShipsSunk() {
        return computerGrid.areAllShipsSunk();
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
