package kalah.Game;

import kalah.Models.*;

import java.util.List;

import static kalah.Utility.GameConfig.NUMBER_OF_HOUSES;

/**
 * Rules.java is a singleton to decide the outcomes of a move and determine if the game is finished
 */
public class Rules {

    private static Rules _rules = new Rules();

    private Rules() { }

    public static Rules getInstance() {
        return _rules;
    }

    /**
     * Check if the capture is successful (Player move ends on own house which has no seeds and opponents opposite house has at least one seed)
     * If successful - move all opponents opposite house's seeds to player's store then next player's turn?
     * If unsuccessful - next players turn
     */
    public void capture(Pit movingPlayerPit, Pit otherPlayerPit, int endingHouse) {

        if (movingPlayerPit.getHouses().get(endingHouse).seedsInHouse() == 0) {
            House h = otherPlayerPit.getHouses().get(endingHouse);

            if (h.seedsInHouse() > 0 ) {
                movingPlayerPit.getStore().capture(h.sowHouse()); // Empty opponents house and add to players store
            }
        }
        return;
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

    /**
     * This method handles the player's move and adjusts the board accordingly
     * @param board
     * @param chosenHouse
     * @return
     */
    public void playerMove(Board board, int chosenHouse) {
        chosenHouse--;
        int playerId = board.getCurrentPlayerTurn();
        List<Player> players = board.getPlayers();
        Player movingPlayer = new Player();
        Player otherPlayer = new Player();

        for (Player p: players) {
            if (p.getId() == playerId) {
                movingPlayer = p;
            } else {
                otherPlayer = p;
            }
        }

        Pit movingPlayerPit = movingPlayer.getPit();
        Pit otherPlayerPit = otherPlayer.getPit();

        List<House> movingPlayerHouses = movingPlayerPit.getHouses();
        List<House> otherPlayerHouses = otherPlayerPit.getHouses();
        Store movingPlayerStore = movingPlayerPit.getStore();

        // Sow house - remove all seeds from chosen house
        int seeds = movingPlayerHouses.get(chosenHouse).sowHouse();

        // Add seeds to remainding houses
        for (int i = chosenHouse + 1; i < NUMBER_OF_HOUSES; i++) {
            movingPlayerHouses.get(i).incSeed();
            seeds--;

            if (!checkIfAnySeedsRemaining(seeds)) { // If move ends here, check for capture
                capture(movingPlayerPit, otherPlayerPit, i);
                board.nextPlayerTurn();
                return;
            }

        }

        while (checkIfAnySeedsRemaining(seeds)) {
            // Add to store. If move ends, player gets another turn
            movingPlayerStore.addSeedToStore();
            seeds--;

            if (!checkIfAnySeedsRemaining(seeds)) {
                return;
            }

            // Add to other player houses. If ends here other player's turn
            for (int i = 0; i < NUMBER_OF_HOUSES; i++) {
                otherPlayerHouses.get(i).incSeed();
                seeds--;

                if (!checkIfAnySeedsRemaining(seeds)) {
                    board.nextPlayerTurn();
                    return;
                }
            }

            // Add seeds to current player's houses
            for (int i = 0; i < NUMBER_OF_HOUSES; i++) {
                otherPlayerHouses.get(i).incSeed();
                seeds--;

                if (!checkIfAnySeedsRemaining(seeds)) {
                    capture(movingPlayerPit, otherPlayerPit, i);
                    board.nextPlayerTurn();
                    return;
                }
            }
        }

        return;
    }

    public boolean checkIfAnySeedsRemaining(int seeds) {
        if (seeds == 0) {
            return false;
        }
        return true;
    }

}