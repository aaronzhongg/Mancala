package kalah.Utility;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.Models.Board;
import kalah.Models.House;
import kalah.Models.Player;

import java.util.List;

import static kalah.Utility.GameConfig.NUMBER_OF_HOUSES;

/**
 * Printer.java is a singleton to print the game out to console
 */
public class Printer {
    private IO io;

    public Printer(IO io) {
        this.io = io;
    }

    public void printRound(Board board) {
        printTopBottom();

        printPlayer2(board.getPlayer2(), board.getPlayer1());
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        printPlayer1(board.getPlayer1(), board.getPlayer2());

        printTopBottom();
        return;
    }

    private void printTopBottom() {
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        return;
    }

    public String playerMove(int playerId) {
        return io.readFromKeyboard("Player P" + playerId + "'s turn - Specify house number or 'q' to quit: ");
    }

    private void printPlayer1(Player player1, Player player2) {
        List<House> houses = player1.getPit().getHouses();


        io.print("| " + formatSeedForPrinting(player2.getPit().getStore().seedsInStore()) + " ");

        for (int i = 1; i < NUMBER_OF_HOUSES + 1; i++) {
            io.print("| " + i + "[" + formatSeedForPrinting(houses.get(i - 1).seedsInHouse()) + "] ");
        }

        io.println("| P1 |");
        return;
    }

    private void printPlayer2(Player player2, Player player1) {
        List<House> houses = player2.getPit().getHouses();

        io.print("| P2 ");

        for (int i = NUMBER_OF_HOUSES; i > 0; i--) {
            io.print("| " + i + "[" + formatSeedForPrinting(houses.get(i - 1).seedsInHouse()) + "] ");
        }

        io.println("| " + formatSeedForPrinting(player1.getPit().getStore().seedsInStore()) + " |");
        return;
    }

    public void printQuit(Board board) {
        printGameOver();
        printRound(board);
        return;
    }

    public void printGameOver() {
        io.println("Game over");
        return;
    }

    public void printEmptyHouse(){
        io.println("House is empty. Move again.");
        return;
    }

    public String formatSeedForPrinting(int seed) {
        if (seed > 9) {
            return String.valueOf(seed);
        } else {
            return " " + seed;
        }
    }

    public void printFullGame(Board board) {
        int player1Score = board.getPlayer1().getPit().getStore().seedsInStore();
        int player2Score = board.getPlayer2().getPit().getStore().seedsInStore();
        io.println("\tplayer 1:" + player1Score);
        io.println("\tplayer 2:" + player2Score);

        if (player1Score > player2Score) {
            io.println("Player 1 wins!");
        } else if (player2Score > player1Score) {
            io.println("Player 2 wins!");
        } else {
            io.println("A tie!");
        }
    }
}