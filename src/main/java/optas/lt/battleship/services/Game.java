package optas.lt.battleship.services;

import lombok.Getter;
import optas.lt.battleship.domain.Cell;
import optas.lt.battleship.domain.MapGrid;
import optas.lt.battleship.domain.dtos.MoveResult;
import optas.lt.battleship.domain.enums.CellState;

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

//    public MoveResult processMove(int row, int col) {
////        if (isGameOver) {
////            return new MoveResult("Game is already over!", true, this.getPlayerGrid(), remainingShots);
////        }
//
////        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
////            return new MoveResult("Invalid coordinates!", false, playerGrid.getBoard(), remainingShots);
////        }
//
//        Cell targetCell = computerGrid.getCell(row, col);
////        if (targetCell.getStatus() != CellState.HIDDEN) {
////            return new MoveResult("You already attacked this cell!", false, playerGrid.getBoard(), remainingShots);
////        }
//
//        boolean hit = targetCell.receiveAttack();
//        if (hit) {
//            if (targetCell.getStatus() == CellState.DESTROYED) {
//                if (checkAllShipsSunk()) {
//                    isGameOver = true;
//                    return new MoveResult("You sunk all ships! You won!", true, playerGrid.getBoard(), remainingShots);
//                }
//                return new MoveResult("Hit and sunk!", false, playerGrid.getBoard(), remainingShots);
//            }
//            return new MoveResult("Hit!", false, playerGrid.getBoard(), remainingShots);
//        } else {
//            remainingShots--;
//            if (remainingShots == 0) {
//                isGameOver = true;
//                return new MoveResult("Miss! No shots remaining, you lost!", true, playerGrid.getBoard(), remainingShots);
//            }
//            return new MoveResult("Miss!", false, playerGrid.getBoard(), remainingShots);
//        }
//    }

    private boolean checkAllShipsSunk() {
        return computerGrid.areAllShipsSunk();
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
