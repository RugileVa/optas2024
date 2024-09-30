package optas.lt.battleship.domain;

import lombok.Getter;
import optas.lt.battleship.domain.enums.CellState;

public class Cell {

    @Getter
    private CellState status;

    @Getter
    private Battleship ship;

    public Cell(CellState state) {
        this.status = state;
    }

    public Cell(Cell cell) {
        this.status = cell.status;
        this.ship = cell.ship;
    }

    public void putShip(Battleship ship) {
        this.ship = ship;
        this.status = CellState.SHIP;
    }

    public boolean receiveAttack() {
        if (status == CellState.SHIP) {
            status = CellState.SHIPHIT;
            ship.receiveDamage();
            if (ship != null && ship.checkIsDamaged()) {
                status = CellState.DESTROYED;
            }
            return true; // Hit
        } else if (status == CellState.EMPTY) {
            status = CellState.EMPTYHIT;
            return false; // Miss
        }
        return false; // Cell already attacked
    }
}

