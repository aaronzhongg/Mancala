package kalah.Models;

public class Store {
    private int _seeds;
    
    public Store() {
        _seeds = 0;
    }

    public int seedsInStore() {
        return _seeds;
    }

    public void addSeedToStore() {
        _seeds += 1;
    }

    public void capture(int numOfSeeds) {
        _seeds += numOfSeeds;
    }
}