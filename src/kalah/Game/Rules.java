package kalah.Game;

import com.qualitascorpus.testsupport.IO;
import kalah.Models.*;
import kalah.Utility.Printer;

import java.util.List;

import static kalah.Utility.GameConfig.NUMBER_OF_HOUSES;

/**
 * Rules.java is a class to decide the outcomes of a move and determine if the game is finished
 */
public class Rules {
    private Printer printer;

    public Rules(Printer printer) {
        this.printer = printer;
    }

    /**
     * Check if the capture is successful (Player move ends on own house which has no seeds and opponents opposite house has at least one seed)
     * If successful - move all opponents opposite house's seeds to player's store then next player's turn?
     * If unsuccessful - next players turn 1 3 3 6 1 4 2 5 2 2 4 6 2 5 1 1 3 3 6 4 5 2 3 4 6 2 4 1
     */
    public void capture(Pit movingPlayerPit, Pit otherPlayerPit, int endingHouse) {
        int oppositeHouse = getOppositeHouse(endingHouse);
        House lastHouse = movingPlayerPit.getHouses().get(endingHouse);
        if (lastHouse.seedsInHouse() == 1) {
            House h = otherPlayerPit.getHouses().get(oppositeHouse);

            if (h.seedsInHouse() > 0 ) {
                lastHouse.sowHouse();
                movingPlayerPit.getStore().capture(h.sowHouse() + 1); // Empty opponents house and add to players store
            }
        }
        return;
    }

    /**
     * check if next player moving has any moves (at least one seed in any of their houses)
     * @param board
     * @return true - no moves, game has ended. false - moves exist, game has not ended.
     */
    public boolean gameEnded(Board board) {
        Player player = board.getPlayers().get(board.getCurrentPlayerTurn() - 1);

        // Check if next player moving has any seeds in any house
        boolean allHousesEmpty = true;
        for (House h: player.getPit().getHouses()) {
            if (h.seedsInHouse() != 0) {
                allHousesEmpty = false;
                break;
            }
        }

        if (allHousesEmpty == true) {
            printer.printRound(board);
            printer.printGameOver();
            printer.printRound(board);
            scoreFullGame(board);
            printer.printFullGame(board);
            return true;
            }
        return false;
    }

    /**
     * This method handles the player's move and adjusts the board accordingly
     * @param board
     * @param chosenHouse
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

        if (seeds == 0) {
            printer.printEmptyHouse();
        } else {
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
                    movingPlayerHouses.get(i).incSeed();
                    seeds--;

                    if (!checkIfAnySeedsRemaining(seeds)) {
                        capture(movingPlayerPit, otherPlayerPit, i);
                        board.nextPlayerTurn();
                        return;
                    }
                }
            }
        }
        return;
    }

    /**
     * Check if the seeds remaining
     * @param seeds
     * @return true - there are seeds remaining. false - no seeds remaining
     */
    public boolean checkIfAnySeedsRemaining(int seeds) {
        if (seeds == 0) {
            return false;
        }
        return true;
    }

    /**
     * Get the opposite house number of input house
     * @param house
     * @return opposite house number
     */
    public int getOppositeHouse(int house) {
        switch (house) {
            case 0:
                return 5;
            case 1:
                return 4;
            case 2:
                return 3;
            case 3:
                return 2;
            case 4:
                return 1;
            case 5:
                return 0;
            default:
                return -1;
        }
    }

    /**
     * After the game has finished, add all of each player's remaining seeds in their houses to their store
     * @param board
     */
    public void scoreFullGame(Board board) {
        List<Player> players = board.getPlayers();
        for (Player p: players) {
            int seeds = 0;
            for (House h:p.getPit().getHouses()) {
                seeds += h.sowHouse();
            }
            p.getPit().getStore().capture(seeds);
        }
        return;
    }

}