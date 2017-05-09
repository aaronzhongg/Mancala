package kalah.Utility;

import kalah.Models.Board;
import kalah.Models.Player;

/**
 * Created by Aaron on 9/05/17.
 */
public interface IPrinter {
    void printRound(Board board);
    void printTopBottom();
    String playerMove(int playerId);
    void printPlayer1(Player player1, Player player2);
    void printPlayer2(Player player2, Player player1);
    void printQuit(Board board);
    void printGameOver();
    void printEmptyHouse();
    String formatSeedForPrinting(int seed);
    void printFullGame(Board board);
}
