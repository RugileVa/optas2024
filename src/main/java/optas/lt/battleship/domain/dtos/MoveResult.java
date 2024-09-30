package optas.lt.battleship.domain.dtos;

import optas.lt.battleship.domain.Cell;
import optas.lt.battleship.domain.enums.CellState;

public class MoveResult {
    private String message;
    private boolean isGameOver;
    private CellState state;
    private int remainingShots;

    // Constructor
    public MoveResult(String message, boolean isGameOver, CellState state, int remainingShots) {
        this.message = message;
        this.isGameOver = isGameOver;
        this.state = state;
        this.remainingShots = remainingShots;
    }
    public String getMessage() {
        return message;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public int getRemainingShots() {
        return remainingShots;
    }
}
