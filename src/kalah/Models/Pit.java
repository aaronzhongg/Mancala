package kalah.Models;

import java.util.ArrayList;
import java.util.List;

import static kalah.Utility.GameConfig.NUMBER_OF_HOUSES;
import static kalah.Utility.GameConfig.SEEDS_PER_HOUSES;

/**
 * Pit.java represents a player's houses and store
 */

public class Pit {

    private List<House> _houses;
    private Store _store;

    public Pit() {
        _houses = new ArrayList<House>();
        for (int i = 0; i < NUMBER_OF_HOUSES; i++) {
            _houses.add(new House(SEEDS_PER_HOUSES));
        }
        _store = new Store();
    }

    public Store getStore(){
        return _store;
    }

    public List<House> getHouses() {
        return _houses;
    }

}