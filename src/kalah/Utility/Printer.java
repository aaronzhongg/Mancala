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

//    private static Printer _printer = new Printer();
//    private IO io = new MockIO();
    private IO io;

    public Printer(IO io) {
        this.io = io;
    }

//    public static Printer getInstance() {
//        return _printer;
//    }

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


        io.print("|  " + String.valueOf(player2.getPit().getStore().seedsInStore()) + " ");

        for (int i = 1; i < NUMBER_OF_HOUSES + 1; i++) {
            io.print("| " + i + "[ " + houses.get(i - 1).seedsInHouse() + "] ");
        }

        io.println("| P1 |");
        return;
    }

    private void printPlayer2(Player player2, Player player1) {
        List<House> houses = player2.getPit().getHouses();

        io.print("| P2 ");

        for (int i = NUMBER_OF_HOUSES; i > 0; i--) {
            io.print("| " + i + "[ " + houses.get(i - 1).seedsInHouse() + "] ");
        }

        io.println("|  " + String.valueOf(player1.getPit().getStore().seedsInStore()) + " |");
        return;
    }

    public void printGameOver(Board board) {
        io.println("Game over");
        printRound(board);
        return;
    }
}