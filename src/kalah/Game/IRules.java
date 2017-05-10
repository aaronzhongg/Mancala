package kalah.Game;

import kalah.Models.Board;
import kalah.Models.Pit;

/**
 * Created by Aaron on 9/05/17.
 */
public interface IRules {
    void capture(Pit movingPlayerPit, Pit otherPlayerPit, int endingHouse);
    boolean gameEnded(Board board);
    boolean playerMove(Board board, int chosenHouse);
    void scoreFullGame(Board board);
}
