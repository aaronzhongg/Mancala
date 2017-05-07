package kalah.Models;

/**
 * House.java represents a house object which has a number of seeds
 */
public class House{

    private int _seed;

    public House(int seed) {
        _seed = seed;
    }

    public int seedsInHouse() {
        return _seed;
    }
}