package optas.lt.battleship.domain;

import lombok.Getter;
import lombok.Setter;

public class GameStatusResponse {
    private boolean isGameOver;

    @Getter @Setter
    private int remainingShots;

    @Setter @Getter
    private String[][] playerBoard;

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}
