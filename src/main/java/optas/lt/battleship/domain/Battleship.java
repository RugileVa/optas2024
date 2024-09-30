package optas.lt.battleship.domain;

public class Battleship {
    private final int size;
    private int hitCount;

    public Battleship(int size) {
        this.size = size;
        this.hitCount = 0;
    }

    public void receiveDamage() {
        hitCount++;
    }

    public boolean checkIsDamaged() {
        return hitCount >= size; // true if the ship is destroyed
    }
}
