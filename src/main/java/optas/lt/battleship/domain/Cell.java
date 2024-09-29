package optas.lt.battleship.domain;

import optas.lt.battleship.domain.enums.CellState;

public class Cell {
    private CellState status;
    private Battleship ship;

    public Cell() {
        this.status = CellState.HIDDEN;
    }

    // Copy constructor to deep copy Cell objects
    public Cell(Cell cell) {
        this.status = cell.status;
        this.ship = cell.ship;
    }

    public CellState getStatus() {
        return status;
    }

    public void putShip(Battleship ship) {
        this.ship = ship;
        this.status = CellState.SHIP;
    }

    public boolean receiveAttack() {
        if (status == CellState.SHIP) {
            status = CellState.SHIPHIT;
            if (ship != null && ship.receiveDamage()) {
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

