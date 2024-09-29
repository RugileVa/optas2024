package optas.lt.battleship.domain;

public class Battleship {
    private int size;
    private int hitCount;

    public Battleship(int size) {
        this.size = size;
        this.hitCount = 0;
    }

    // Method to receive damage and determine if the ship is destroyed
    public boolean receiveDamage() {
        hitCount++;
        return hitCount >= size; // Returns true if the ship is destroyed
    }
}
