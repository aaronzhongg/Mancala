package kalah.Game;

import kalah.Models.Board;
import kalah.Models.House;
import kalah.Models.Player;

import java.util.List;

/**
 * Rules.java is a singleton to decide the outcomes of a move and determine if the game is finished
 */
public class Rules {

    private static Rules _rules = new Rules();

    private Rules() { }

    public static Rules getInstance() {
        return _rules;
    }

    public void nextPlayerTurn() {

    }

    public void samePlayerTurn() {

    }

    public void capture() {

    }

    public boolean gameEnded(Board board) {
        List<Player> players = board.getPlayers();

        // Check if any players do not have any more seeds in their houses
        for (Player p: players) {

            boolean allHousesEmpty = true;
            for (House h: p.getPit().getHouses()) {
                if (h.seedsInHouse() != 0) {
                    allHousesEmpty = false;
                    break;
                }
            }

            if (allHousesEmpty == true) {
                return true;
            }
        }

        return false;
    }

}