package optas.lt.battleship.domain.dtos;

import optas.lt.battleship.domain.enums.CellState;
import optas.lt.battleship.domain.enums.GameState;

public record MoveResult(GameState gameState, CellState state, int remainingShots) {
}

